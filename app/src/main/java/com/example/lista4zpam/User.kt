package com.example.lista4zpam

abstract class User(
    var id: String,
    var login: String,
    var email: String,
    var dateRegister: String,
    var password: String,
) {

    /**
     * zdefiniowanie id i listy wszystkich uzytkownikow
     */
    companion object UsersBase {
        private val users = mutableListOf<User>()
        private var sellerId = 1
        private var buyerId = 1

        fun addUser(user: User) {
            when (user) {
                is Seller -> user.id = "S${sellerId++}"
                is Buyer -> user.id = "B${buyerId++}"
            }
            users.add(user)
        }

        fun log(login: String, password: String, role: String): User? {
            val check = users.find { it.login == login && it.password == password }
            if (check != null) {
                val isCorrectRole = when (role.lowercase()) {
                    "seller" -> check is Seller
                    "buyer" -> check is Buyer
                    else -> false
                }

                return if (isCorrectRole) {
                    println("Zalogowano jako ${check.login} (${check::class.simpleName})")
                    check
                } else {
                    println("Ten użytkownik nie jest typu $role.")
                    null
                }
            } else {
                println(" Błędny login lub hasło.")
                return null
            }
        }

        fun register(
            login: String,
            email: String,
            dateRegister: String,
            password: String,
            role: String,
        ) {

            if (login.length <= 3) {
                println("Your login should be longer than 3 chars! Try again.")
                return
            }
            if (!email.contains("@")) {
                println("Your email should have @ inside! Wrong format.")
                return
            }
            if (password.length < 5) {
                println("Your password is too short! It must contain at least 5 chars.")
                return
            }

            when (role.lowercase()) {
                "seller" -> addUser(Seller("", login, email, dateRegister, password))
                "buyer" -> addUser(Buyer("", login, email, dateRegister, password))
                else -> println("Wrong role: choose Buyer or Seller")
            }
        }
    }
}
