package br.com.fiap.locaweb.backEnd.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.fiap.locaweb.backEnd.database.dao.AttachmentDao
import br.com.fiap.locaweb.backEnd.database.dao.InboxDao
import br.com.fiap.locaweb.backEnd.database.dao.MessageDao
import br.com.fiap.locaweb.backEnd.database.dao.UserDao
import br.com.fiap.locaweb.backEnd.model.Attachment
import br.com.fiap.locaweb.backEnd.model.Inbox
import br.com.fiap.locaweb.backEnd.model.Message
import br.com.fiap.locaweb.backEnd.model.User

@Database(
    entities = [
        User::class,
        Message::class,
        Attachment::class,
        Inbox::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun messageDao(): MessageDao
    abstract fun attachmentDao(): AttachmentDao
    abstract fun inboxDao(): InboxDao

    companion object {
        private lateinit var instance: AppDatabase

        fun getDatabase(context: Context): AppDatabase {
            if (!Companion::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "locaweb"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}
