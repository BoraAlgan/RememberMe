package com.example.rememberme.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.rememberme.data.remote.FirebaseAuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val firebaseAuthManager: FirebaseAuthManager) : ViewModel() {

    fun signOut() {
        firebaseAuthManager.signOut()
    }

}