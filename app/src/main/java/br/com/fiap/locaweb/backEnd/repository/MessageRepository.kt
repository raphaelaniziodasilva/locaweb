package br.com.fiap.locaweb.backEnd.repository

import android.content.Context
import br.com.fiap.locaweb.backEnd.database.AppDatabase
import br.com.fiap.locaweb.backEnd.model.Message
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MessageRepository(context: Context) {
    private val db = AppDatabase.getDatabase(context).messageDao()

    fun create(message: Message): Long {
        return db.create(message)
    }

    fun getAllMessage(): List<Message> {
        return db.getAllMessage()
    }

    fun getMessageById(id: Long): Message {
        return db.getMessageById(id)
    }

    fun getMessagesWithSenderInfoForRecipient(recipientEmail: String): List<Message> {
        return db.getMessagesWithSenderInfoForRecipient(recipientEmail)
    }

    fun getFavoriteMessages(recipientEmail: String): List<Message> {
        return db.getFavoriteMessages(recipientEmail)
    }

    fun getImportantMessages(recipientEmail: String): List<Message> {
        return db.getImportantMessages(recipientEmail)
    }

    fun getTrashMessages(recipientEmail: String): List<Message> {
        return db.getTrashMessages(recipientEmail)
    }

    fun update(id: Long, newMessage: Message): Int {
        val existingMessage = db.getMessageById(id)
        if(existingMessage == null) {
            throw IllegalArgumentException("Menssagem não encontrada")
        }

        val updatedMessage = existingMessage.copy(
            sender = newMessage.sender,
            recipients = newMessage.recipients,
            subject = newMessage.subject,
            body = newMessage.body,
            sentDate = newMessage.sentDate,
            important = newMessage.important,
            favorite = newMessage.favorite,
            archived = newMessage.archived,
            updatedAt = getCurrentDateTime()
        )

        return db.update(updatedMessage)
    }

    fun delete(id: Long): Int {
        return db.delete(id)
    }

    companion object {
        fun getCurrentDateTime(): String {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            val date = Calendar.getInstance().time
            return dateFormat.format(date)
        }
    }

    fun moveToTrash(id: Long, message: Message): Int {
        val existingMessage = db.getMessageById(id)
        if(existingMessage == null) {
            throw IllegalArgumentException("Mensagem não encontrada")
        }

        val updatedMessage = existingMessage.copy(
            lixeira = true,
            updatedAt = getCurrentDateTime()
        )

        return db.update(updatedMessage)
    }

    fun restoreFromTrash(id: Long): Int {
        val existingMessage = db.getMessageById(id)
        if (existingMessage == null) {
            throw IllegalArgumentException("Mensagem não encontrada")
        }

        val updatedMessage = existingMessage.copy(
            lixeira = false,
            updatedAt = getCurrentDateTime()
        )

        return db.update(updatedMessage)
    }

    fun getSentMessages(senderEmail: String): List<Message> {
        return db.getSentMessages(senderEmail)
    }
}