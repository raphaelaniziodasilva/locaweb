package br.com.fiap.locaweb.frontEnd.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.backEnd.model.User
import br.com.fiap.locaweb.backEnd.repository.UserRepository

@Composable
fun AccountForm(
    name: String,
    surname: String,
    dateOfBirth: String,
    email: String,
    password: String,
    phoneNumber: String,

    onNameChange: (String) -> Unit,
    onSurnameChange: (String) -> Unit,
    onDateOfBirthChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    update: () -> Unit
    ) {

    var emptyName by remember {
        mutableStateOf(false)
    }
    var emptyDateOfBirth by remember {
        mutableStateOf(false)
    }
    var emptyEmail by remember {
        mutableStateOf(false)
    }
    var emptyPassword by remember {
        mutableStateOf(false)
    }

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val userRepository = UserRepository(context)

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cadastrar conta",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.blue)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { onNameChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nome")
            },
            placeholder = {
                Text(text = "João")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            ),
            isError = emptyName
        )

        if(emptyName){
            Text(
                text = "Nome é obrigatório",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                color = Color.Red,
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = surname,
            onValueChange = { onSurnameChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Sobrenome (Opcional)")
            },
            placeholder = {
                Text(text = "Oliveira da Silva")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = dateOfBirth,
            onValueChange = { onDateOfBirthChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Data de nascimento")
            },
            placeholder = {
                Text(text = "DD/MM/YYYY")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            ),
            isError = emptyDateOfBirth
        )

        if(emptyDateOfBirth){
            Text(
                text = "Data de nascimento e obrigatório",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                color = Color.Red,
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = email,
            onValueChange = { onEmailChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "E-mail")
            },
            placeholder = {
                Text(text = "teste@gmail.com")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            isError = emptyEmail
        )

        if(emptyEmail){
            Text(
                text = "E-mail é obrigatório",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                color = Color.Red,
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { onPasswordChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Senha")
            },
            placeholder = {
                Text(text = "Crie sua senha")
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
            ),
            isError = emptyPassword
        )

        if(emptyPassword){
            Text(
                text = "Senha obrigatória",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 14.sp,
                color = Color.Red,
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { onPhoneNumberChange(it) },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Telefone")
            },
            placeholder = {
                Text(text = "1191232349")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if(name.isEmpty() || dateOfBirth.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    emptyName = name.isEmpty()
                    emptyDateOfBirth = dateOfBirth.isEmpty()
                    emptyEmail = email.isEmpty()
                    emptyPassword = password.isEmpty()
                } else {
                    val user = User(
                        id = 0,
                        name = name,
                        surname = surname,
                        dateOfBirth = dateOfBirth,
                        email = email,
                        password = password,
                        phoneNumber = phoneNumber
                    )

                    userRepository.create(user)
                    update()

                    onNameChange("")
                    onSurnameChange("")
                    onDateOfBirthChange("")
                    onEmailChange("")
                    onPasswordChange("")
                    onPhoneNumberChange("")
                }
            },
            modifier = Modifier
                .width(150.dp)
                .height(60.dp)
                .padding(bottom = 20.dp)
        ) {
            Text(
                text = "Cadastrar",
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )
        }
    }
}
