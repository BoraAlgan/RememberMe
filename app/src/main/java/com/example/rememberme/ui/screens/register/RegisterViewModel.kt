package com.example.rememberme.ui.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rememberme.data.remote.FirebaseAuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val firebaseAuthManager: FirebaseAuthManager) :
    ViewModel() {

    fun signUp(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            val result = firebaseAuthManager.signUp(email, password)
            if (result != null) {
                onSuccess()
            } else {
                onError("Register failed")
            }
        }
    }
}