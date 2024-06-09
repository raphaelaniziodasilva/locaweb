
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {
                    navController.navigate("inboxScreen")
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar"
                )
            }
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
        }

        Spacer(modifier = Modifier.height(20.dp))

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

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                navController.navigate("respondScreen/$messageId")

            }
        ) {
            Text(
                text = "Responder",
                modifier = Modifier.padding(end = 8.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Responder"
            )
        }
    }

}


