package br.com.fiap.locaweb.frontEnd.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
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

    Column() {
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
}