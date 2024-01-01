package com.example.authentication.presentation.login

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication.data.model.UserState
import com.example.authentication.data.network.SupabaseClient
import com.example.authentication.domain.useCases.Validations
import com.example.authentication.utils.SharedPreferenceHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validations: Validations
): ViewModel() {
    private val _loginState = mutableStateOf(LoginState())
    val loginState = _loginState

    fun onEvent(event: LoginEvents){
        when(event) {
            is LoginEvents.EmailChanged -> {
                _loginState.value = loginState.value.copy(
                    email = event.email
                )
            }
            is LoginEvents.PasswordChanged -> {
                _loginState.value = loginState.value.copy(
                    password = event.password
                )
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
                SupabaseClient.client.auth.signInWith(Email) { //signing up using email and password saved to supabase
                    email = userEmail
                    password = userPassword
                }
                saveToken(context)//the token produced is then saved in a sharedpreference to be used whenever needed
//                _userState.value = UserState.Success("Logged in successfully!")
                _loginState.value = loginState.value.copy(userState = UserState.Success("Logged in successfully!"))
            } catch (e: Exception) {
//                _userState.value = UserState.Error("Error: ${e.message}")
                _loginState.value = loginState.value.copy(userState = UserState.Error("Error: ${e.message}"))
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                SupabaseClient.client.auth.signOut()
//                _userState.value = UserState.Success("Logged out successfully!")
                _loginState.value = loginState.value.copy(userState = UserState.Success("Logged out successfully!"))
            } catch (e: Exception) {
//                _userState.value = UserState.Error("Error: ${e.message}")
                _loginState.value = loginState.value.copy(userState = UserState.Error("Error: ${e.message}"))
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
                    _loginState.value = loginState.value.copy(userState = UserState.Success("User is not logged in!"))
//                    _userState.value = UserState.Error("User is not logged in!")
                } else {
                    SupabaseClient.client.auth.retrieveUser(token)//get the token
                    SupabaseClient.client.auth.refreshCurrentSession()
                    saveToken(context)
//                    _userState.value = UserState.Success("User is already logged in")
                    _loginState.value = loginState.value.copy(userState = UserState.Success("User is already logged in"))
                }
            } catch (e: Exception) {
//                _userState.value = UserState.Error("Error: ${e.message}")
                _loginState.value = loginState.value.copy(userState = UserState.Error("Error: ${e.message}"))
            }
        }
    }

    private fun saveToken(context: Context) { // this is created whenever a call is made to supabase as security token
        viewModelScope.launch {
            val accessToken = SupabaseClient.client.auth.currentAccessTokenOrNull()
            val sharedPref = SharedPreferenceHelper(context)
            sharedPref.saveStringData("accessToken", accessToken)
        }
    }

    private fun getToken(context: Context): String? {
        val sharedPref = SharedPreferenceHelper(context)
        return sharedPref.getStringData("accessToken")
    }

}