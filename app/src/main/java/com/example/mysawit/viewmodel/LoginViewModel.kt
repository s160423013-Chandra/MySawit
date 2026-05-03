package com.example.mysawit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val loginSuccessLD = MutableLiveData<Boolean>()

    fun login(username: String, password: String) {
        if (username == "student" && password == "123") {
            loginSuccessLD.value = true
        } else {
            loginSuccessLD.value = false
        }
    }
}