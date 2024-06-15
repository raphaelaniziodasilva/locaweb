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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.locaweb.backEnd.model.Message
import br.com.fiap.locaweb.backEnd.repository.MessageRepository
import br.com.fiap.locaweb.backEnd.repository.UserRepository
import br.com.fiap.locaweb.frontEnd.components.SentMessageList

@Composable
fun SentScreen(navController: NavController) {
    val context = LocalContext.current
    val messageRepository = MessageRepository(context)
    val userRepository = UserRepository(context)
    val user = userRepository.getLoggedInUser()

    var email by remember {
        mutableStateOf("")
    }
    var sentMessages by remember {
        mutableStateOf(emptyList<Message>())
    }
    var isMenuExpanded by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(user) {
        user?.let {
            email = it.email
            sentMessages = messageRepository.getSentMessages(email)
        }
    }

    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        Column {
            IconButton(
                onClick = {
                    isMenuExpanded = !isMenuExpanded
                }
            ) {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "menu",
                    tint = Color.Gray,
                    modifier = Modifier.size(32.dp)
                )
            }

            if (isMenuExpanded) {
                Text(
                    text = "Caixa de entrada",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .clickable {
                            navController.navigate("inboxScreen")
                        }
                )
                Text(
                    text = "Importante",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .clickable {
                            navController.navigate("importantScreen")
                        }
                )
                Text(
                    text = "Enviados",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .clickable {
                            navController.navigate("sentScreen")
                        }
                )
                Text(
                    text = "Lixeira",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .clickable {
                            navController.navigate("trashScreen")
                        }
                )
            }

            SentMessageList(
                messages = sentMessages
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
                        tint = Color.Blue,
                        modifier = Modifier.size(40.dp),
                    )
                }
            }
        }
    }
}
