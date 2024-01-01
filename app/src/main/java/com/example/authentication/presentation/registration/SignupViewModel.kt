package com.example.authentication.presentation.registration

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
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val validations: Validations
): ViewModel() {

    private val _signState = mutableStateOf(SignupState())
    val signState = _signState

//    private val _userState = mutableStateOf<UserState>(UserState.Loading)
//    val userState = _userState

    fun onEvent(event: SignupEvents) {
        when(event) {
            is SignupEvents.EmailChanged -> {
                _signState.value = signState.value.copy(email = event.email)
            }
            is SignupEvents.PasswordChanged -> {
                _signState.value = signState.value.copy(password = event.password)
            }
//            SignupEvents.Submit -> TODO()
            is SignupEvents.UsernameChanged -> {
                _signState.value = signState.value.copy(userName = event.userName)
            }
        }
    }

    fun signUp(
        context: Context,
        userName: String,
        userEmail: String,
        userPassword: String
    ){
        viewModelScope.launch {
            try {
                SupabaseClient.client.auth.signUpWith(Email) { //signing up using email and password saved to supabase
                    email = userEmail
                    password = userPassword
                    data = buildJsonObject {
                        put("userName", userName)
                    }
                }
                saveToken(context)//the token produced is then saved in a sharedpreference to be used whenever needed

//                _userState.value = UserState.Success("Registered user successfully")

                _signState.value = signState.value.copy(userState = UserState.Success("Registered user successfully"))
            } catch (e: Exception) {

//                _userState.value = UserState.Error("Error: ${e.message}")

                _signState.value = signState.value.copy(
                    userState = UserState.Error("Error: ${e.message}")
                )
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

}