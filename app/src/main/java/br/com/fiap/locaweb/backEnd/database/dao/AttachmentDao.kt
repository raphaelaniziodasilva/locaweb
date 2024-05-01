package br.com.fiap.locaweb.backEnd.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.locaweb.backEnd.model.Attachment

@Dao
interface AttachmentDao {
    @Insert
    fun create(attachment: Attachment): Attachment

    @Query("SELECT * FROM anexo ORDER BY nome_arquivo")
    fun getAllAttachment(): List<Attachment>

    @Query("SELECT * FROM anexo WHERE id = :id")
    fun getAttachmentByID(id: Long): Attachment

    @Update
    fun update(attachment: Attachment): Attachment

    @Delete
    fun delete(attachment: Attachment): Int
}
