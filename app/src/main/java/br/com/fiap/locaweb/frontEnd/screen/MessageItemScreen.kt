
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locaweb.backEnd.repository.MessageRepository

@Composable
fun MessageItemScreen(messageId: Long, navController: NavController) {
    val context = LocalContext.current
    val messageRepository = MessageRepository(context)

    var message = remember {
        mutableStateOf(messageRepository.getMessageById(messageId)!!)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        IconButton(
            onClick = {
                messageRepository.delete(messageId)
                navController.popBackStack()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Excluir"
            )
        }

        Text(
            text = message.value.subject,
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "De ${message.value.sender}",
            modifier = Modifier.padding(bottom = 8.dp),
        )
        Text(
            text = "Para: ${message.value.recipients}",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Sent Date: ${message.value.sentDate}",
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Body: ${message.value.body}",
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}
