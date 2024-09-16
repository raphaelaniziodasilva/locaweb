package br.com.fiap.locaweb.backEnd.repository

import android.content.Context
import android.content.SharedPreferences
import br.com.fiap.locaweb.backEnd.database.AppDatabase
import br.com.fiap.locaweb.backEnd.model.User
import java.util.Calendar

class UserRepository(private val context: Context) {
    private val db = AppDatabase.getDatabase(context).userDao()
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

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
            throw IllegalArgumentException("O usuário com e-mail $email não existe")
        }

        val updatedUser = existingUser.copy(
            name = newUser.name,
            surname = newUser.surname,
            dateOfBirth = newUser.dateOfBirth,
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

    fun loginUser(email: String, password: String): Pair<Boolean, String> {
        if (email.isEmpty() || password.isEmpty()) {
            val errorMessage = if (email.isEmpty()) "E-mail obrigatório" else "Senha obrigatória"
            return Pair(false, errorMessage)
        }

        val user = db.getUserByEmail(email)
        return if (user != null && user.password == password) {
            sharedPreferences.edit().putString("logged_in_user_email", email).apply()
            Pair(true, "")
        } else {
            Pair(false, "E-mail ou senha inválidos")
        }
    }

    fun getLoggedInUser(): User? {
        val email = sharedPreferences.getString("logged_in_user_email", null)
        return if (email != null) {
            db.getUserByEmail(email)
        } else {
            null
        }
    }

    fun logoutUser() {
        sharedPreferences.edit().clear().apply()
    }
}
