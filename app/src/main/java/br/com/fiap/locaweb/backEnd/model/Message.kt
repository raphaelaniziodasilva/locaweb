package br.com.fiap.locaweb.backEnd.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Entity(tableName = "mensagem")
data class Message(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "remetente")
    @NonNull
    var sender: String,

    @ColumnInfo(name = "destinatario")
    @NonNull
    var recipients: String,

    @ColumnInfo(name = "assunto")
    @NonNull
    var subject: String,

    @ColumnInfo(name = "corpo")
    @NonNull
    var body: String,

    @ColumnInfo(name = "data_envio")
    var sentDate: String = getCurrentDateTime(),

    @ColumnInfo(name = "sender_name")
    var senderName: String,

    @ColumnInfo(name = "sender_email")
    var senderEmail: String,

    @ColumnInfo(name = "importante")
    var important: Boolean = false,

    @ColumnInfo(name = "favorito")
    var favorite: Boolean = false,

    @ColumnInfo(name = "arquivada")
    var archived: Boolean = false,

    @ColumnInfo(name = "lixeira")
    var lixeira: Boolean = false,

    @ColumnInfo(name = "created_at")
    var createdAt: String = getCurrentDateTime(),

    @ColumnInfo(name = "updated_at")
    var updatedAt: String = getCurrentDateTime()
) {
    companion object {
        fun getCurrentDateTime(): String {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
            val date = Calendar.getInstance().time
            return dateFormat.format(date)
        }
    }
}
