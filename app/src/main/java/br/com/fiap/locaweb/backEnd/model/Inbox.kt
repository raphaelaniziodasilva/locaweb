package br.com.fiap.locaweb.backEnd.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

// caixa de entrada de um usuário, contendo uma lista de mensagens recebidas
@Entity(tableName = "caixa_entrada")
data class Inbox(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "mensagens")
    var messages: List<Message>,

    @ColumnInfo(name = "created_at")
    var createdAt: Long = Calendar.getInstance().timeInMillis,

    @ColumnInfo(name = "updated_at")
    var updatedAt: Long = Calendar.getInstance().timeInMillis
)
