package br.com.fiap.locaweb.backEnd.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.locaweb.backEnd.model.Inbox

@Dao
interface InboxDao {
    @Insert
    fun create(inbox: Inbox): Long

    @Query("SELECT * FROM caixa_entrada ORDER BY created_at")
    fun getAllInbox(): List<Inbox>

    @Query("SELECT * FROM caixa_entrada WHERE id = :id")
    fun getInboxById(id: Long): Inbox

    @Update
    fun update(inbox: Inbox): Int

    @Delete
    fun delete(inbox: Inbox): Int
}
