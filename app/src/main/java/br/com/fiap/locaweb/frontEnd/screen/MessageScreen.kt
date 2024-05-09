package br.com.fiap.locaweb.frontEnd.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.locaweb.R
import br.com.fiap.locaweb.backEnd.model.Message
import br.com.fiap.locaweb.backEnd.repository.MessageRepository
import br.com.fiap.locaweb.frontEnd.components.InputField

@Composable
fun MessageScreen(messageScreenViewModel: MessageScreenViewModel) {
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        val sender by messageScreenViewModel.senderState.observeAsState(initial = "")
        val recipients by messageScreenViewModel.recipientsState.observeAsState(initial = "")
        val subject by messageScreenViewModel.subjectState.observeAsState(initial = "")
        val body by messageScreenViewModel.bodyState.observeAsState(initial = "")

        var emptySender by remember {
            mutableStateOf(false)
        }
        var emptyRecipients by remember {
            mutableStateOf(false)
        }
        var emptySubject by remember {
            mutableStateOf(false)
        }
        var emptyBody by remember {
            mutableStateOf(false)
        }

        val context = LocalContext.current
        val messageRepository = MessageRepository(context)

        var messageList = remember {
            mutableStateOf(messageRepository.getAllMessage())
        }

        Column(
            modifier = Modifier.
                fillMaxWidth(),
        ) {
            Card(modifier = Modifier
                .fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(color = colorResource(id = R.color.white)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    IconButton(
                        onClick = {
                            if(sender.isEmpty() || recipients.isEmpty() || subject.isEmpty() || body.isEmpty()) {
                                emptySender = sender.isEmpty()
                                emptyRecipients = recipients.isEmpty()
                                emptySubject = subject.isEmpty()
                                emptyBody = body.isEmpty()
                            } else {
                                val message = Message(
                                    id = 0,
                                    sender = sender,
                                    recipients = recipients,
                                    subject = subject,
                                    body = body
                                )

                                messageRepository.create(message)
                            }
                        } ,
                        modifier = Modifier
                                .align(Alignment.End)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Enviar",
                        )
                    }

                    InputField(
                        label = "De",
                        placeholder = "teste@mail.com",
                        value = sender,
                        keyboardType = KeyboardType.Email,
                        modifier = Modifier
                            .fillMaxWidth(),
                        updateValue = {
                            messageScreenViewModel.onSenderChanged(it)
                        },
                        emptyError = emptySender,
                    )

                    if(emptySender){
                        Text(
                            text = "Email de quem esta enviando é obrigatório",
                            modifier = Modifier
                                .fillMaxWidth(),
                            fontSize = 14.sp,
                            color = Color.Red,
                            textAlign = TextAlign.Center
                        )
                    }

                    InputField(
                        label = "Para",
                        placeholder = "teste@mail.com",
                        value = recipients,
                        keyboardType = KeyboardType.Email,
                        modifier = Modifier
                            .fillMaxWidth(),
                        updateValue = {
                            messageScreenViewModel.onRecipientsChanged(it)
                        },
                        emptyError = emptyRecipients,
                    )

                    if(emptyRecipients){
                        Text(
                            text = "Email de quem vai receber é obrigatório",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 14.sp,
                            color = Color.Red,
                            textAlign = TextAlign.Center
                        )
                    }

                    InputField(
                        label = "Assunto",
                        placeholder = "Assunto",
                        value = subject,
                        keyboardType = KeyboardType.Text,
                        modifier = Modifier
                            .fillMaxWidth(),
                        updateValue = {
                            messageScreenViewModel.onSubjectChanged(it)
                        },
                        emptyError = emptySubject,
                    )

                    if(emptySubject){
                        Text(
                            text = "Assunto é obrigatório",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 14.sp,
                            color = Color.Red,
                            textAlign = TextAlign.Center
                        )
                    }

                    InputField(
                        label = "Corpo do e-mail",
                        placeholder = "Escreva o assunto completo",
                        value = body,
                        keyboardType = KeyboardType.Text,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(510.dp),
                        updateValue = {
                            messageScreenViewModel.onBodyChanged(it)
                        },
                        emptyError = emptyBody,
                    )

                    if(emptyBody){
                        Text(
                            text = "Corpo do e-mail é obrigatório",
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 14.sp,
                            color = Color.Red,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}