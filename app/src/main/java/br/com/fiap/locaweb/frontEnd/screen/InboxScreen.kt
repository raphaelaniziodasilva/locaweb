package br.com.fiap.locaweb.frontEnd.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locaweb.backEnd.model.Message
import br.com.fiap.locaweb.backEnd.repository.MessageRepository
import br.com.fiap.locaweb.backEnd.repository.UserRepository
import br.com.fiap.locaweb.frontEnd.components.MessageList

@Composable
fun InboxScreen(navController: NavController) {
    val context = LocalContext.current
    val userRepository = UserRepository(context)
    val messageRepository = MessageRepository(context)
    val user = userRepository.getLoggedInUser()

    var email by remember {
        mutableStateOf("")
    }
    var messageList by remember {
        mutableStateOf(emptyList<Message>())
    }
    var isMenuExpanded by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(user) {
        user?.let {
            email = it.email
            messageList = messageRepository.getMessagesWithSenderInfoForRecipient(email)
        }
    }

    Box() {
        Column {
            IconButton(
                onClick = {
                    isMenuExpanded = !isMenuExpanded
                }
            ) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "menu"
                )
            }

            if (isMenuExpanded) {
                Text(
                    text = "Caixa de entrada",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .clickable {
                            navController.navigate("inboxScreen")
                        }
                )
                Text(
                    text = "Importante",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .clickable {
                            navController.navigate("importantScreen")
                        }
                )
                Text(
                    text = "Lixeira",
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .clickable {
                            navController.navigate("trashScreen")
                        }
                )
            }

            MessageList(
                messages = messageList
            ) { message ->
                navController.navigate("message_item_screen/${message.id}")
            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Row(modifier = Modifier.padding(16.dp)) {
                IconButton(
                    onClick = {
                        navController.navigate("messageScreen")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "criar",
                        modifier = Modifier.size(40.dp),
                    )
                }
            }
        }
    }
}
