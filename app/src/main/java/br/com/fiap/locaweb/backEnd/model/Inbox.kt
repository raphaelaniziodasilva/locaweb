package br.com.fiap.locaweb.backEnd.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// caixa de entrada de um usuário, contendo uma lista de mensagens recebidas
@Entity(tableName = "inbox")
data class Inbox(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "mensagens")
    var messages: List<Message>
)
