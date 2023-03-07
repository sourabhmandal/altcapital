package com.digixcapital.altcapital.presentation.register_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digixcapital.altcapital.data.AuthRepository
import com.digixcapital.altcapital.presentation.login_screen.SignInState
import com.digixcapital.altcapital.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {
    private val _registerState = Channel<SignInState>()
    val registerState = _registerState.receiveAsFlow()

    fun registerUser(email:String, password:String) = viewModelScope.launch {
        repository.loginUser(email, password).collect{result ->
            when(result) {
                is Resource.Success -> {
                    _registerState.send(SignInState(isSuccess = result.data))
                }
                is Resource.Loading -> {
                    _registerState.send(SignInState(isLoading = true))
                }
                is Resource.Error -> {
                    _registerState.send(SignInState(isError = result.message))
                }
            }

        }
    }

}