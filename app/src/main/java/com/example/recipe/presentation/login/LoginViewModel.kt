package com.example.recipe.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe.data.model.AuthRequest
import com.example.recipe.domain.repository.AuthRepository
import com.example.recipe.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState(isLoading = true)
            when (val result = authRepository.login(AuthRequest(email, password))) {
                is Result.Success -> {
                    // TODO: Save the token in a secure way
                    println("Access Token: ${result.data?.accessToken}")
                    _loginState.value = LoginState(isLoginSuccess = true)
                }
                is Result.Error -> {
                    _loginState.value = LoginState(error = result.message)
                }
                else -> {}
            }
        }
    }

    fun dismissError() {
        _loginState.value = LoginState()
    }
}

data class LoginState(
    val isLoading: Boolean = false,
    val isLoginSuccess: Boolean = false,
    val error: String? = null
)