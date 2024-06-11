package br.com.fiap.locaweb.frontEnd.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.locaweb.backEnd.model.Message

@Composable
fun SentMessageList(messages: List<Message>, onMessageClick: (Message) -> Unit) {
    Column {
        messages.forEach { message ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onMessageClick(message) },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Para: ${message.recipients}")
                    Text(text = "Assunto: ${message.subject}")
                }
            }
        }
    }
}
