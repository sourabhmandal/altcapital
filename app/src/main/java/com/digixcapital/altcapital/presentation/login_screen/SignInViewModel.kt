package com.digixcapital.altcapital.presentation.login_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digixcapital.altcapital.data.AuthRepository
import com.digixcapital.altcapital.utils.Resource
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {
    private val _signInState = Channel<SignInState>()
    val signInState = _signInState.receiveAsFlow()

    private val _googleSignInState = mutableStateOf(SignInState())
    val googleSignInState: State<SignInState> = _googleSignInState

    fun loginUser(email:String, password:String) = viewModelScope.launch {
        repository.loginUser(email, password).collect{result ->
            when(result) {
                is Resource.Success -> {
                    _signInState.send(SignInState(isSuccess = result.data))
                }
                is Resource.Loading -> {
                    _signInState.send(SignInState(isLoading = true))
                }
                is Resource.Error -> {
                    _signInState.send(SignInState(isError = result.message))
                }
            }

        }
    }
    fun loginGoogleUser(credential: AuthCredential) = viewModelScope.launch {
        repository.googleLoginUser(credential).collect{result ->
            when(result) {
                is Resource.Success -> {
                    _signInState.send(SignInState(isSuccess = result.data))
                }
                is Resource.Loading -> {
                    _signInState.send(SignInState(isLoading = true))
                }
                is Resource.Error -> {
                    _signInState.send(SignInState(isError = result.message))
                }
            }

        }
    }

}