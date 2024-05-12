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

    // Remetente da mensagem. Indica quem enviou a mensagem.
    @ColumnInfo(name = "remetente")
    @NonNull
    var sender: String,

    // Lista de destinatários da mensagem. Pode conter um ou mais endereços de e-mail para os quais a mensagem foi enviada.
    @ColumnInfo(name = "destinatario")
    @NonNull
    var recipients: String,

    // Assunto da mensagem. Descreve o tema ou motivo da mensagem.
    @ColumnInfo(name = "assunto")
    @NonNull
    var subject: String,

    // Corpo da mensagem. Contém o conteúdo principal da mensagem.
    @ColumnInfo(name = "corpo")
    @NonNull
    var body: String,

    // Indica quando a mensagem foi enviada.
    @ColumnInfo(name = "data_envio")
    var sentDate: String = getCurrentDateTime(),

    // Indica quando a mensagem foi recebida.
    @ColumnInfo(name = "data_recebimento")
    var receiptDate: String = getCurrentDateTime(),

    // Indica se a mensagem foi lida. Pode ser usado para rastrear o status de leitura da mensagem.
    @ColumnInfo(name = "status_leitura")
    var read: Boolean = false,

    // Indica se a mensagem foi marcada como importante.
    @ColumnInfo(name = "importante")
    var important: Boolean = false,

    // Indica se a mensagem foi marcada como favorita.
    @ColumnInfo(name = "favorito")
    var favorite: Boolean = false,

    // Indica se a mensagem foi arquivada pelo usuário
    @ColumnInfo(name = "arquivada")
    var archived: Boolean = false,

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
