package br.com.fiap.locaweb.backEnd.repository

import android.content.Context
import br.com.fiap.locaweb.backEnd.database.AppDatabase
import br.com.fiap.locaweb.backEnd.model.User
import java.util.Calendar

class UserRepository(context: Context) {
    private val db = AppDatabase.getDatabase(context).userDao()

    fun create(user: User): Long {
        val existingUser = db.getUserByEmail(user.email)
        if(existingUser != null) {
            throw IllegalArgumentException("O usuário com e-mail ${user.email} já existe")
        }

        return db.create(user)
    }

    fun getAllUser(): List<User> {
        return db.getAllUser()
    }

    fun getUserByEmail(email: String): User {
        return db.getUserByEmail(email)
    }

    fun update(email: String, newUser: User): Int {
        val existingUser = db.getUserByEmail(email)
        if(existingUser == null) {
            throw IllegalArgumentException("O usuário com e-mail ${email} não existe")
        }

        val updatedUser = existingUser.copy(
            name = newUser.name,
            email = newUser.email,
            password = newUser.password,
            phoneNumber = newUser.phoneNumber,
            updatedAt = Calendar.getInstance().timeInMillis
        )

        return db.update(updatedUser)
    }

    fun delete(user: User): Int {
        return db.delete(user)
    }
}