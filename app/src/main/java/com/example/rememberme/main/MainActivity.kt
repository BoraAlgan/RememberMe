package com.example.rememberme.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rememberme.ui.screens.home.Home
import com.example.rememberme.ui.screens.login.Login
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {

            val controller = rememberNavController()

            val navBackStackEntry = controller.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.destination?.route


            NavHost(navController = controller, startDestination = "login") {
                composable("login") {
                    Login(navController = controller, viewModel = hiltViewModel())
                }
                composable("home") {
                    Home(viewModel = hiltViewModel())
                }
            }

        }
    }

    override fun onStart() {
        super.onStart()

        FirebaseApp.initializeApp(this)
    }
}

