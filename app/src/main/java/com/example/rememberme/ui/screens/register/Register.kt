package com.example.rememberme.ui.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Register(navController: NavController, viewModel: RegisterViewModel) {

    var regEmail by remember { mutableStateOf("") }
    var regPassword by remember { mutableStateOf("") }
    var regPasswordConfirm by remember { mutableStateOf("") }
    var isRegPasswordConfirmTouched by remember { mutableStateOf(false) }
    var regIsLoading by remember { mutableStateOf(false) }
    var regErrorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = regEmail,
            onValueChange = { regEmail = it },
            label = { Text("Email") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = regPassword,
            visualTransformation =  PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = { regPassword = it },
            label = { Text("Şifre") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = regPasswordConfirm,
            visualTransformation =  PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {
                regPasswordConfirm = it
                isRegPasswordConfirmTouched = true
            },

            label = { Text("Tekrar şifre girin") },
            modifier = Modifier.onFocusChanged {
                if (it.isFocused) {
                    isRegPasswordConfirmTouched = true
                }
            },
            isError = isRegPasswordConfirmTouched && regPassword != regPasswordConfirm,
            supportingText = {
                if (isRegPasswordConfirmTouched && regPassword != regPasswordConfirm) {
                    Text("Şifreler eşleşmiyor")
                }
            }

        )

        Spacer(modifier = Modifier.height(8.dp))


        Spacer(modifier = Modifier.height(16.dp))

        if (regIsLoading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = {
                    regIsLoading = true
                    viewModel.signUp(regEmail, regPassword, onSuccess = {
                        regIsLoading = false
                        navController.navigate("login")
                    }, onError = { error ->
                        regIsLoading = false
                        regErrorMessage = error
                    })
                },
                enabled = regPassword == regPasswordConfirm && isRegPasswordConfirmTouched,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (regPassword == regPasswordConfirm && isRegPasswordConfirmTouched) Color.Unspecified else Color.Gray
                )
            ) {
                Text("Kayıt Ol")
            }
        }
    }
}
