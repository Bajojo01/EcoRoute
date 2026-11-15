package com.example.ecoroute

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoroute.ui.theme.AuthBackground
import com.example.ecoroute.ui.theme.AuthGreen
import com.example.ecoroute.ui.theme.LogoPlaceholder

// LOG IN
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,   // tumatakbo kapag tama ang login
    onSignUpClick: () -> Unit     // tumatakbo kapag pinindot ang "Sign up"
) {
    // hidden ang tinype ng user
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }   // kapag true = lalabas yung popup

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AuthBackground)
            .statusBarsPadding()
    ) {
        // logo, title, fields
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            // placeholder na bilog para sa logo
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(LogoPlaceholder, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Logo", fontSize = 10.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Welcome back!",
                color = AuthGreen,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(28.dp))

            AuthTextField(
                label = "Email",
                value = email,
                onValueChange = { email = it },
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(12.dp))
            AuthTextField(
                label = "Password",
                value = password,
                onValueChange = { password = it },
                isPassword = true
            )

            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Forgot password?",
                color = AuthGreen,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { /* TODO: forgot password page */ }
            )
        }

        // yubng linya sa pagitan
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(AuthGreen.copy(alpha = 0.5f))
        )

        // button at link
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GreenButton(text = "Log in") {
                // (PLACEHOLFER) basta may laman ang email at password, tama na.
                // Papalitan pa to ng totoong check (Firebase).
                if (email.isBlank() || password.isBlank()) {
                    showError = true
                } else {
                    onLoginSuccess()
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Sign up",
                color = AuthGreen,
                fontSize = 12.sp,
                modifier = Modifier.clickable { onSignUpClick() }
            )
        }
    }

    // lalabas yung popup kapag showError = true
    if (showError) {
        AuthErrorDialog(title = "Log in failed") { showError = false }
    }
}

//  SIGN UP
@Composable
fun SignUpScreen(
    onBackClick: () -> Unit,        // arrow sa taas
    onSignUpSuccess: () -> Unit,    // running kapag tama ang sign up
    onLogInClick: () -> Unit        // "Log in" sa baba
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AuthBackground)
            .statusBarsPadding()
    ) {
        // "Create account"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = AuthGreen,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackClick() }
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = "Create account",
                color = AuthGreen,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // Linya sa ilalim
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(AuthGreen.copy(alpha = 0.5f))
        )

        // mga fields
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            AuthTextField(label = "Full Name", value = fullName, onValueChange = { fullName = it })
            Spacer(modifier = Modifier.height(12.dp))
            AuthTextField(
                label = "Email",
                value = email,
                onValueChange = { email = it },
                keyboardType = KeyboardType.Email
            )
            Spacer(modifier = Modifier.height(12.dp))
            AuthTextField(
                label = "Password",
                value = password,
                onValueChange = { password = it },
                isPassword = true
            )
            Spacer(modifier = Modifier.height(12.dp))
            AuthTextField(
                label = "Confirm password",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                isPassword = true
            )
        }

        // Line sa taas ng buttons
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(AuthGreen.copy(alpha = 0.5f))
        )

        // button at link
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GreenButton(text = "Sign up") {
                // Mali kung may blangko o hindi pareho yung password
                val hasBlank = fullName.isBlank() || email.isBlank() ||
                        password.isBlank() || confirmPassword.isBlank()
                if (hasBlank || password != confirmPassword) {
                    showError = true
                } else {
                    onSignUpSuccess()
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Log in",
                color = AuthGreen,
                fontSize = 12.sp,
                modifier = Modifier.clickable { onLogInClick() }
            )
        }
    }

    if (showError) {
        AuthErrorDialog(title = "Sign up failed") { showError = false }
    }
}