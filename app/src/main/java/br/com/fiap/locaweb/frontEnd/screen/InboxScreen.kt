package br.com.fiap.locaweb.frontEnd.screen

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locaweb.backEnd.repository.MessageRepository
import br.com.fiap.locaweb.backEnd.repository.UserRepository
import br.com.fiap.locaweb.frontEnd.components.MessageList

@Composable
fun InboxScreen(navController: NavController) {
    val context = LocalContext.current
    val userRepository = UserRepository(context)
    val messageRepository = MessageRepository(context)

    var contactsList = remember {
        mutableStateOf(userRepository.getAllUser())
    }
    var messageList = remember {
        mutableStateOf(messageRepository.getAllMessage())
    }
    
    Box() {
        Column {
            IconButton(
                onClick = {
                }
            ) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "menu"
                )
            }

            MessageList(
                users = contactsList,
                messages = messageList,
                onItemClick = { message ->
                    // Navegar para a tela de detalhes da mensagem ao clicar nela
                    navController.navigate("message_item_screen/${message.id}")
                }
            )
        }
        Column(
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Row( modifier = Modifier.padding(16.dp)) {
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