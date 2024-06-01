package br.com.fiap.locaweb.frontEnd.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.locaweb.backEnd.model.Message
import br.com.fiap.locaweb.backEnd.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inbox(
    user: User,
    message: Message,
    onItemClick: (Message) -> Unit // Adicione o parâmetro onItemClick
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onItemClick(message) } // Chame onItemClick ao clicar na mensagem
    ) {
        val context = LocalContext.current

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier
                .padding(8.dp)
                .weight(2f)) {
                Text(
                    text = user.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = message.subject,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun MessageList(
    users: MutableState<User>,
    messages: MutableState<List<Message>>,
    onItemClick: (Message) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        for (message in messages.value) {
            // Acessando o usuário diretamente, já que users.value contém o usuário único
            Inbox(users.value, message) {
                onItemClick(it)
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
