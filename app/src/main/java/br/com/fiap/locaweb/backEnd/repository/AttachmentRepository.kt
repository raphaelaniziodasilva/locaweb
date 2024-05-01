package br.com.fiap.locaweb.backEnd.repository

import android.content.Context
import br.com.fiap.locaweb.backEnd.database.AppDatabase
import br.com.fiap.locaweb.backEnd.model.Attachment
import java.util.Calendar

class AttachmentRepository(context: Context) {
    private val db = AppDatabase.getDatabase(context).attachmentDao()

    fun create(attachment: Attachment): Attachment {
        return db.create(attachment)
    }

    fun getAllAttachment(): List<Attachment> {
        return db.getAllAttachment()
    }

    fun getAttachmentByID(id: Long): Attachment {
        return db.getAttachmentByID(id)
    }

    fun update(id: Long, newAttachment: Attachment): Attachment {
        val existingAttachment = db.getAttachmentByID(id)
        if(existingAttachment == null) {
            throw IllegalArgumentException("Arquivo não existe")
        }

        val updatedAttachment = existingAttachment.copy(
            fileName = newAttachment.fileName,
            size = newAttachment.size,
            mimeType = newAttachment.mimeType,
            updatedAt = Calendar.getInstance().timeInMillis
        )

        db.update(updatedAttachment)
        return updatedAttachment
    }

    fun delete(id: Long): Int {
        return db.delete(id)
    }

}