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

    @Update
    fun update(message: Message): Int

    @Query("DELETE FROM mensagem WHERE id = :id")
    fun delete(id: Long): Int
}
