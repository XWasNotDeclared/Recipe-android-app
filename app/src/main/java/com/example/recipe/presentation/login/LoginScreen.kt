package com.example.recipe.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController?, onLoginSuccess: () -> Unit) {
    val viewModel: LoginViewModel = hiltViewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginState by viewModel.loginState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.login(email, password) },
                enabled = !loginState.isLoading
            ) {
                Text("Login")
            }
        }

        if (loginState.isLoading) {
            CircularProgressIndicator()
        }

        if (loginState.isLoginSuccess) {
            LaunchedEffect(Unit) {
                onLoginSuccess()
            }
        }

        loginState.error?.let { error ->
             AlertDialog(
                onDismissRequest = { viewModel.dismissError() },
                title = { Text(text = "Login Failed") },
                text = { Text(text = error) },
                confirmButton = {
                    TextButton(onClick = { viewModel.dismissError() }) {
                        Text(text = "OK")
                    }
                }
            )
        }
    }
}
