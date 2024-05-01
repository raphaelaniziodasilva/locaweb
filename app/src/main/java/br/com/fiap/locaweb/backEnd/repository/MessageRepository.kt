package br.com.fiap.locaweb.backEnd.repository

import android.content.Context
import br.com.fiap.locaweb.backEnd.database.AppDatabase
import br.com.fiap.locaweb.backEnd.model.Message
import java.util.Calendar

class MessageRepository(context: Context) {
    private val db = AppDatabase.getDatabase(context).messageDao()

    fun create(message: Message): Message {
        return db.create(message)
    }

    fun getAllMessage(): List<Message> {
        return db.getAllMessage()
    }

    fun getMessageById(id: Long): Message {
        return db.getMessageById(id)
    }

    fun update(id: Long, newMessage: Message): Message {
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
            receiptDate = newMessage.receiptDate,
            read = newMessage.read,
            important = newMessage.important,
            favorite = newMessage.favorite,
            archived = newMessage.archived,
            updatedAt = Calendar.getInstance().timeInMillis
        )

        db.update(updatedMessage)
        return updatedMessage
    }

    fun delete(id: Long): Int {
        return db.delete(id)
    }
}