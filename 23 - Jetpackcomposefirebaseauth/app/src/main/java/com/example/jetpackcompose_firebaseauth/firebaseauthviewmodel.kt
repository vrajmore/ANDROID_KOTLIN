package com.example.jetpackcompose_firebaseauth

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class firebaseauthviewmodel : ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authstate = MutableLiveData<AuthState?>()
    val authstate: MutableLiveData<AuthState?> = _authstate

    init {
        checkauthstatus()
    }

    fun checkauthstatus() {
        if (auth.currentUser == null) {
            _authstate.value = AuthState.unauthenticated
        } else {
            _authstate.value = AuthState.authenticated
        }
    }


    fun login(email: String, password: String, context: Context) {
        if (email.isEmpty() || password.isEmpty()) {
            _authstate.value = AuthState.error("email and password can not be empty")
            Toast.makeText(context, "email and password can not be empty", Toast.LENGTH_SHORT).show()
        } else {
            _authstate.value = AuthState.loading
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authstate.value = AuthState.authenticated

                    } else {
                        _authstate.value = AuthState.error(task.exception?.message ?: "Something went wrong")
                        Toast.makeText(context, "${task.exception?.message ?: "Something went wrong"}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    fun signup(email: String, password: String, context: Context) {
        if (email.isEmpty() || password.isEmpty()) {
            _authstate.value = AuthState.error("email and password can not be empty")
            Toast.makeText(context, "email and password can not be empty", Toast.LENGTH_SHORT).show()
        } else {
            _authstate.value = AuthState.loading
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authstate.value = AuthState.authenticated
                    } else {
                        _authstate.value = AuthState.error(task.exception?.message ?: "Something went wrong")
                        Toast.makeText(context, "${task.exception?.message ?: "Something went wrong"}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    fun signout() {
        auth.signOut()
        _authstate.value = AuthState.unauthenticated
    }

}

sealed class AuthState {
    object authenticated : AuthState()
    object unauthenticated : AuthState()
    object loading : AuthState()
    data class error(var message: String) : AuthState()

}