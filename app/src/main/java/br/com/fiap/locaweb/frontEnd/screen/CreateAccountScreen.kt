package br.com.fiap.locaweb.frontEnd.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locaweb.backEnd.repository.UserRepository
import br.com.fiap.locaweb.frontEnd.components.AccountForm

@Composable
fun CreateAccountScreen(navController: NavController, createAccountScreenViewModel: CreateAccountScreenViewModel) {
    val name by createAccountScreenViewModel.nameState.observeAsState(initial = "")
    val surname by createAccountScreenViewModel.surnameState.observeAsState(initial = "")
    val dateOfBirth by createAccountScreenViewModel.dateOfBirthState.observeAsState(initial = "")
    val email by createAccountScreenViewModel.emailState.observeAsState(initial = "")
    val password by createAccountScreenViewModel.passwordState.observeAsState(initial = "")
    val phoneNumber by createAccountScreenViewModel.phoneNumberState.observeAsState(initial = "")

    val context = LocalContext.current
    val userRepository = UserRepository(context)

    var listaContatos = remember {
        mutableStateOf(userRepository.getAllUser())
    }

    Column(        modifier = Modifier.padding(16.dp)) {
        AccountForm(
            name = name,
            surname = surname,
            dateOfBirth = dateOfBirth,
            email = email,
            password = password,
            phoneNumber = phoneNumber,
            onNameChange = {
                createAccountScreenViewModel.onNameChanged(it)
            },
            onSurnameChange = {
                createAccountScreenViewModel.onSurnameChanged(it)
            },
            onDateOfBirthChange = {
                createAccountScreenViewModel.onDateOfBirthStateChanged(it)
            },
            onEmailChange = {
                createAccountScreenViewModel.onEmailChanged(it)
            },
            onPasswordChange = {
                createAccountScreenViewModel.onPasswordChanged(it)
            },
            onPhoneNumberChange = {
                createAccountScreenViewModel.onPhoneNumberChanged(it)
            },
            update = {
                listaContatos.value = userRepository.getAllUser()
            },
            onCadastroSuccess = {
                navController.navigate("login")
            }
        )
    }
}