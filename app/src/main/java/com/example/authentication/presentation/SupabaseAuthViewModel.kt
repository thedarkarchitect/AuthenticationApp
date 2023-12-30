package com.example.authentication.presentation

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication.data.model.UserState
import com.example.authentication.data.network.SupabaseClient.client
import com.example.authentication.utils.SharedPreferenceHelper
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class SupabaseAuthViewModel: ViewModel() {
    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState = _userState

    fun signUp(
        context: Context,
        userName: String,
        userEmail: String,
        userPassword: String
    ){
      viewModelScope.launch {
          try {
              client.auth.signUpWith(Email) { //signing up using email and password saved to supabase
                  email = userEmail
                  password = userPassword
                  data = buildJsonObject {
                      put("userName", userName)
                  }
              }
              saveToken(context)//the token produced is then saved in a sharedpreference to be used whenever needed
              _userState.value = UserState.Success("Registered user successfully")
          } catch (e: Exception) {
              _userState.value = UserState.Error("Error: ${e.message}")
          }
      }
    }

    fun login(
        context: Context,
        userEmail: String,
        userPassword: String
    ){
        viewModelScope.launch {
            try {
                client.auth.signInWith(Email) { //signing up using email and password saved to supabase
                    email = userEmail
                    password = userPassword
                }
                saveToken(context)//the token produced is then saved in a sharedpreference to be used whenever needed
                _userState.value = UserState.Success("Logged in successfully!")
            } catch (e: Exception) {
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                client.auth.signOut()
                _userState.value = UserState.Success("Logged out successfully!")
            } catch (e: Exception) {
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    fun isUserLoogedIn( // this checks for user that already has an account
        context: Context
    ) {
        viewModelScope.launch {
            try {
                val token = getToken(context)
                if(token.isNullOrEmpty()){
                    _userState.value = UserState.Error("User is not logged in!")
                } else {
                    client.auth.retrieveUser(token)//get the token
                    client.auth.refreshCurrentSession()
                    saveToken(context)
                    _userState.value = UserState.Success("User is already logged in")
                }
            } catch (e: Exception) {
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }

    private fun saveToken(context: Context) { // this is created whenever a call is made to supabase as security token
        viewModelScope.launch {
            val accessToken = client.auth.currentAccessTokenOrNull()
            val sharedPref = SharedPreferenceHelper(context)
            sharedPref.saveStringData("accessToken", accessToken)
        }
    }

    private fun getToken(context: Context): String? {
        val sharedPref = SharedPreferenceHelper(context)
        return sharedPref.getStringData("accessToken")
    }
}