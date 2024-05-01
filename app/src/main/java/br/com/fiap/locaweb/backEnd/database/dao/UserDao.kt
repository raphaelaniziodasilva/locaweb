package br.com.fiap.locaweb.backEnd.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.locaweb.backEnd.model.User

@Dao
interface UserDao {
    @Insert
    fun create(user: User): User

    @Query("SELECT * FROM contato ORDER BY created_at")
    fun getAllUser(): List<User>

    @Query("SELECT * FROM contato WHERE email = :email")
    fun getUserByEmail(email: String): User

    @Update
    fun update(user: User): User

    @Delete
    fun delete(id: Long): Int
}
