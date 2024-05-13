package br.com.fiap.locaweb.frontEnd.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.locaweb.backEnd.model.User

class LoginScreenViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    val emailState: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val passwordState: LiveData<String> = _password

    var loggedInUser: User? = null

    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }
    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

}
