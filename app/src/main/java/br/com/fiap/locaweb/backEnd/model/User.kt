package br.com.fiap.locaweb.backEnd.model

import androidx.annotation.NonNull
import androidx.annotation.Size
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "contato", indices = [Index(value = ["email"], unique = true)])
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "nome")
    @NonNull
    @Size(min = 2, max = 150)
    var name: String,

    @NonNull
    @Size(min = 11, max = 150)
    var email: String,

    @ColumnInfo(name = "senha")
    @NonNull
    @Size(min = 4, max = 15)
    var password: String,

    @ColumnInfo(name = "telefone")
    @Size(min = 9, max = 13)
    var phoneNumber: String?,

    @ColumnInfo(name = "created_at")
    var createdAt: Long = Calendar.getInstance().timeInMillis,

    @ColumnInfo(name = "updated_at")
    var updatedAt: Long = Calendar.getInstance().timeInMillis
)
