package br.com.fiap.locaweb.frontEnd.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateAccountScreenViewModel : ViewModel() {
    private val _name = MutableLiveData<String>()
    val nameState: LiveData<String> = _name

    private val _surname = MutableLiveData<String>()
    val surnameState: LiveData<String> = _surname

    private val _dateOfBirth = MutableLiveData<String>()
    val dateOfBirthState: LiveData<String> = _dateOfBirth

    private val _email = MutableLiveData<String>()
    val emailState: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val passwordState: LiveData<String> = _password

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumberState: LiveData<String> = _phoneNumber

    fun onNameChanged(newName: String) {
        _name.value = newName
    }
    fun onSurnameChanged(newSurname: String) {
        _surname.value = newSurname
    }
    fun onDateOfBirthStateChanged(newDateOfBirthState: String) {
        _dateOfBirth.value = newDateOfBirthState
    }
    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
    }
    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }
    fun onPhoneNumberChanged(newPhoneNumber: String) {
        _phoneNumber.value = newPhoneNumber
    }
}