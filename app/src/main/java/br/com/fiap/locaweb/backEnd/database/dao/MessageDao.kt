package br.com.fiap.locaweb.backEnd.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.locaweb.backEnd.model.Message

@Dao
interface MessageDao {
    @Insert
    fun create(message: Message): Long

    @Query("SELECT * FROM mensagem ORDER BY created_at")
    fun getAllMessage(): List<Message>

    @Query("SELECT * FROM mensagem WHERE id = :id")
    fun getMessageById(id: Long): Message

    @Query("SELECT mensagem.*, contato.nome as senderName, contato.email as senderEmail FROM mensagem INNER JOIN contato ON mensagem.remetente = contato.email WHERE destinatario = :recipientEmail AND favorito = 0 AND importante = 0 And lixeira = 0 AND spam = 0 ORDER BY mensagem.created_at")
    fun getMessagesWithSenderInfoForRecipient(recipientEmail: String): List<Message>

    @Query("SELECT * FROM mensagem WHERE favorito = 1 AND importante = 0 AND lixeira = 0 AND destinatario = :recipientEmail ORDER BY created_at")
    fun getFavoriteMessages(recipientEmail: String): List<Message>

    @Query("SELECT * FROM mensagem WHERE importante = 1 AND favorito = 0 AND lixeira = 0 AND destinatario = :recipientEmail ORDER BY created_at")
    fun getImportantMessages(recipientEmail: String): List<Message>

    @Query("SELECT * FROM mensagem WHERE spam = 1 AND importante = 0 AND favorito = 0 AND lixeira = 0 AND destinatario = :recipientEmail ORDER BY created_at")
    fun getSpam(recipientEmail: String): List<Message>

    @Query("SELECT COUNT(*) FROM mensagem WHERE remetente = :senderEmail AND created_at > :startTime")
    fun countMessagesSentByUserAfter(senderEmail: String, startTime: String): Int

    @Query("SELECT * FROM mensagem WHERE lixeira = 1 AND favorito = 0 AND importante = 0 AND destinatario = :recipientEmail ORDER BY created_at")
    fun getTrashMessages(recipientEmail: String): List<Message>

    @Query("SELECT * FROM mensagem WHERE remetente = :senderEmail ORDER BY created_at")
    fun getSentMessages(senderEmail: String): List<Message>

    @Update
    fun update(message: Message): Int

    @Query("DELETE FROM mensagem WHERE id = :id")
    fun delete(id: Long): Int
}
