package br.com.fiap.locaweb.backEnd.database

import androidx.room.TypeConverter
import br.com.fiap.locaweb.backEnd.model.Message
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun fromMessagesList(messages: List<Message>): String {
        return Gson().toJson(messages)
    }

    @TypeConverter
    fun toMessagesList(messagesString: String): List<Message> {
        val listType = object : TypeToken<List<Message>>() {}.type
        return Gson().fromJson(messagesString, listType)
    }
}