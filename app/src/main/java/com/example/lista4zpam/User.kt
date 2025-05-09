package com.example.lista4zpam

/**
 * Klasa User pozwala na tworzenie uzytkownikow
 * oraz realizacje przez nich funkcji logowania
 * i rejestracji
 *
 */
abstract class User(
    var id: String,
    var login: String,
    var email: String,
    var dateRegister: String,
    var password: String,
) {

    /**
     * Utworzenie bazy uzytkownikow do rejestracji i logowania
     */
    companion object UsersBase {
        val users = mutableListOf<User>()
        private var sellerId = 1
        private var buyerId = 1

        /**
         * Funkcja dodaje uzytkownika do bazy i tworzy id
         *
         * @param user [User] - uzytkownik marketplace typu User
         */
        fun addUser(user: User) {
            when (user) {
                is Seller -> user.id = "S${sellerId++}"
                is Buyer -> user.id = "B${buyerId++}"
            }
            users.add(user)
        }


        /**
         * Funkcja loguje uzytkownika zarejestrowanego, jezeli
         * haslo i login odpowiadaja jednemu uzytkownikowi i sa
         * zgodne z baza uzytkownikow
         *
         * @param login [String] - nazwa uzytkownika
         * @param password [String] - haslo uzytkownika
         * @return uzytkownik typu User, jezeli uda sie zalogowac
         * lub null, jezeli uzytkownik nie istnieje lub jest blad
         * w danych wejsciowych
         */
        fun log(login: String, password: String): User? {
            val check = users.find { it.login == login && it.password == password }

            if (check != null) {
                val role = if (check is Seller) "Seller" else "Buyer"
                println("Logged as ($role). Welcome ${check.login}!")
                return check
            } else {
                println("Wrong login or password. If you don't have account, register first!")
                return null
            }
        }

        /**
         * Funkcja rejestracji tworzy odpowiednio
         * uzytkownika, ktory bedzie kupcem lub sprzedawca
         *
         * @param login [String] - nazwa uzytkownika dluzsza od 3 znakow
         * @param email [String] - adres e-mail uzytkownika (musi zawierac @)
         * @param dateRegister [String] - data rejestracji
         * @param password [String] - haslo uzytkownika dluzsze od 5 znakow
         * @param role [String] - rola kupca (b) lub sprzedawcy (s)
         */
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
                "s" -> addUser(Seller("", login, email, dateRegister, password))
                "b" -> addUser(Buyer("", login, email, dateRegister, password))
                else -> println("Wrong role: choose Buyer (b) or Seller (s)")
            }
        }
    }
}
