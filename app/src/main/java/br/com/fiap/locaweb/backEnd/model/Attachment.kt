package br.com.fiap.locaweb.backEnd.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "anexo")
data class Attachment(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    // armazena o nome do arquivo do anexo
    @ColumnInfo(name = "nome_arquivo")
    @NonNull
    var fileName: String,

    // armazena o tamanho do anexo em bytes
    @ColumnInfo(name = "tamanho")
    @NonNull
    var size: Long,

    // armazena o tipo MIME do anexo, que é um identificador de formato de arquivo. Por exemplo, para um arquivo PDF, o tipo MIME seria "application/pdf"
    @ColumnInfo(name = "tipo_mime")
    @NonNull
    var mimeType: String,

    @ColumnInfo(name = "created_at")
    var createdAt: Long = Calendar.getInstance().timeInMillis,

    @ColumnInfo(name = "updated_at")
    var updatedAt: Long
)
