package com.example.rememberme.ui.screens.login

import android.util.Patterns
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.rememberme.ui.components.EmailInputFilter
import com.example.rememberme.ui.components.PasswordInputFilter


@Composable
fun Login(navController: NavController, viewModel: LoginViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scrollState = rememberScrollState()
    var triggerValidationMail by remember { mutableStateOf(false) }
    var triggerValidationPassword by remember { mutableStateOf(false) }
    var errorTextMail by remember { mutableStateOf("E-posta alanı boş bırakılamaz.") }
    var errorTextPassword by remember { mutableStateOf("Şifre alanı boş bırakılamaz.") }
    var showPassword by remember { mutableStateOf(false) }


    LaunchedEffect(email, password) {
        scrollState.scrollTo(scrollState.maxValue)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        EmailInputFilter(
            modifier = Modifier
                .fillMaxWidth(),
            value = email,
            onValueChange = {
                email = it
                errorMessage = null
                errorTextMail = when {
                    email.isEmpty() -> "E-posta alanı boş bırakılamaz."
                    (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) -> "Geçersiz e-posta adresi."
                    else -> ""
                }
                triggerValidationMail = false
            },
            label = "Email",
            showError = triggerValidationMail,
            errorText = errorTextMail,

            )

//        Spacer(modifier = Modifier.height(16.dp))

        PasswordInputFilter(
            modifier = Modifier
                .fillMaxWidth(),
            value = password,
            onValueChange = {
                password = it
                errorMessage = null
                errorTextPassword = when {
                    password.isEmpty() -> "Şifre alanı boş bırakılamaz."
                    else -> ""
                }
                triggerValidationPassword = false
            },
            label = "Şifre",
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                if (showPassword) {
                    IconButton(onClick = { showPassword = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "Hide Password"
                        )
                    }

                } else {
                    IconButton(onClick = { showPassword = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "Show Password"
                        )
                    }
                }
            },
            showPassword = showPassword,
            showError = triggerValidationPassword,
            errorText = errorTextPassword,
            isError = triggerValidationPassword && errorTextPassword.isNotEmpty()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    triggerValidationMail = true
                    triggerValidationPassword = true

                    if (email.isNotEmpty() && password.isNotEmpty() &&
                        (errorMessage == null || errorMessage == "E-posta veya şifre alanları hatalı.")
                    ) {
                        isLoading = true
                        viewModel.signIn(
                            email, password,
                            onSuccess = {
                                isLoading = false
                                navController.navigate("home")
                            },
                            onError = { error ->
                                isLoading = false
                                errorMessage = error
                            },
                        )
                    }
//                    else {
//                        isLoading = false
//                        if (!triggerValidationMail || !triggerValidationPassword) {
//                            errorMessage = "E-posta veya şifre alanları hatalı."
//                        }
//                    }
                },
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text("Giriş Yap")
                }
            }

            Button(onClick = {
                isLoading = true
                navController.navigate("register")
            }) {
                Text("Kayıt Ol")
            }
        }

        AnimatedVisibility(
            visible = !isLoading,
            enter = expandVertically(animationSpec = tween(durationMillis = 600)),
        ) {
            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}





