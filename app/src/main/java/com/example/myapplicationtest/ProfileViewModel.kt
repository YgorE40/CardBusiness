package com.example.myapplicationtest

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileViewModel : ViewModel() {
    // Guarda o estado atual da tela (escondido da View)
    private val _uiState = MutableStateFlow(UserProfile())

    // Expõe o estado para a View de forma segura (somente leitura)
    val uiState: StateFlow<UserProfile> = _uiState.asStateFlow()

    // Regra de negócio: inverte o status de seguir
    fun toggleFollow() {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(seguindo = !currentState.seguindo)
    }
}