package br.com.fiap.locaweb.backEnd.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.fiap.locaweb.backEnd.database.dao.MessageDao
import br.com.fiap.locaweb.backEnd.database.dao.UserDao
import br.com.fiap.locaweb.backEnd.model.Message
import br.com.fiap.locaweb.backEnd.model.User

@Database(
    entities = [
        User::class,
        Message::class,
    ],
    version = 6
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun messageDao(): MessageDao

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
