package br.com.fiap.locaweb.backEnd.repository

import android.content.Context
import br.com.fiap.locaweb.backEnd.database.AppDatabase
import br.com.fiap.locaweb.backEnd.model.Inbox
import java.util.Calendar

class InboxRepository(context: Context) {
    private val db = AppDatabase.getDatabase(context).inboxDao()

    fun create(inbox: Inbox): Long {
        return create(inbox)
    }

    fun getAllInbox(): List<Inbox> {
        return  db.getAllInbox()
    }

    fun getInboxById(id: Long): Inbox {
        return db.getInboxById(id)
    }

    fun update(id: Long, newInbox: Inbox): Int {
        val existingInbox = db.getInboxById(id)
        if(existingInbox == null){
            throw IllegalArgumentException(" Caixa entrada não existe")
        }

        val updatedInbox = existingInbox.copy(
            messages = newInbox.messages,
            updatedAt = Calendar.getInstance().timeInMillis
        )

        return db.update(updatedInbox)
    }

    fun delete(inbox: Inbox): Int {
        return db.delete(inbox)
    }

}