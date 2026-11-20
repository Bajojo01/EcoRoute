package com.example.ecoroute

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// row we insert into user_tbl (no password, auth handles it)
@Serializable
data class NewUser(
    @SerialName("auth_id") val authId: String,
    val username: String,
    val email: String,
    @SerialName("phone_number") val phoneNumber: String
)
// FOR LOGIN
class AuthViewModel : ViewModel() {
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun clearError() {
        errorMessage = null
    }
    fun logIn(
        identifier: String,
        userPassword: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                // no "@" means it's a username, so look up the email first
                val loginEmail = if (identifier.contains("@")) {
                    identifier
                } else {
                    supabase.postgrest.rpc(
                        "get_email_by_username",
                        buildJsonObject { put("uname", identifier) }
                    ).decodeAsOrNull<String>()
                        ?: throw Exception("Invalid login credentials")
                }

                supabase.auth.signInWith(Email) {
                    email = loginEmail
                    password = userPassword
                }
                onSuccess()
            } catch (e: Exception) {
                errorMessage = e.message ?: "Log in failed"
            }
            isLoading = false
        }
    }
//    FOR SIGNUP
    fun signUp(
        username: String,
        userEmail: String,
        userPassword: String,
        phoneNumber: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                // 1. create the auth account (password gets hashed by supabase)
                supabase.auth.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                }

                // 2. get the new account's id
                val authId = supabase.auth.currentUserOrNull()?.id
                    ?: throw Exception("Sign up failed, please try again")

                // 3. save the profile row
                supabase.from("user_tbl").insert(
                    NewUser(authId, username, userEmail, phoneNumber)
                )

                onSuccess()
            } catch (e: Exception) {
                errorMessage = e.message ?: "Sign up failed"
            }
            isLoading = false
        }
    }
}