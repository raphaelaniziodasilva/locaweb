package br.com.fiap.locaweb.frontEnd.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.frontEnd.components.InputField

@Composable
fun LoginScreen(loginScreenViewModel: LoginScreenViewModel) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(16.dp),
    ) {
        val email by loginScreenViewModel.emailState.observeAsState(initial = "")
        val password by loginScreenViewModel.passwordState.observeAsState(initial = "")

        var emptyEmail by remember {
            mutableStateOf(false)
        }
        var emptyPassword by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(width = 240.dp, height = 180.dp)
            )

            //Spacer(modifier = Modifier.height(16.dp))

            Card(modifier = Modifier
                .fillMaxWidth(),
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xD569BEEB))
                    .padding(top = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    InputField(
                        label = "Digite seu email",
                        placeholder = "teste@mail.com",
                        value = email,
                        keyboardType = KeyboardType.Email,
                        modifier = Modifier
                            .padding(top = 16.dp),
                        updateValue = {
                            loginScreenViewModel.onEmailChanged(it)
                        },
                        emptyError = emptyEmail,
                    )

                    if(emptyEmail){
                        Text(
                            text = "Email obrigatório",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 14.sp,
                            color = Color.Red,
                            textAlign = TextAlign.Center
                        )
                    }
                    
                    InputField(
                        label = "Digite a sua senha",
                        placeholder = "senha",
                        value = password,
                        keyboardType = KeyboardType.Password,
                        modifier = Modifier
                            .padding(top = 16.dp),
                        updateValue = {
                            loginScreenViewModel.onPasswordChanged(it)
                        },
                        emptyError = emptyPassword
                    )

                    if(emptyPassword){
                        Text(
                            text = "Senha obrigatória",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 14.sp,
                            color = Color.Red,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                    
                    Button(
                        onClick = {
                            if(email.isEmpty()) emptyEmail = true else emptyEmail = false
                            if(password.isEmpty()) emptyPassword = true else emptyPassword = false
                        },
                        modifier = Modifier
                            .width(150.dp)
                            .height(50.dp)
                            .padding(bottom = 10.dp)
                    ){
                        Text(
                            text = "Entrar",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )
                    }
                }

            }
        }
    }
}