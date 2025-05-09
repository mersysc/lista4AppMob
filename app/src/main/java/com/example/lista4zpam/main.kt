package com.example.lista4zpam

fun main() {
    val products = mutableListOf<Product>()
    var loggedUser: User? = null

    while (true) {
        println("\n Welcome to our store! Choose an option to start:")
        println("1: register")
        println("2: log in")
        println("3: exit")

        when (readln()) {
            "1" -> {
                println("Enter login (longer than 3 chars):")
                val login = readln()
                println("Enter email (remember @):")
                val email = readln()
                println("Enter date of register:")
                val dateRegister = readln()
                println("Enter password (longer than 5 chars):")
                val password = readln()
                println("Enter role (seller - choose 's', buyer - choose 'b'):")
                val role = readln()

                User.register(login, email, dateRegister, password, role)
            }

            "2" -> {
                println("Enter login (longer than 3 chars):")
                val login = readln()
                println("Enter password (longer than 5 chars):")
                val password = readln()

                loggedUser = User.log(login, password)

                if (loggedUser != null) {
                    when (loggedUser) {
                        is Seller -> sellerMenu(loggedUser, products)
                        is Buyer -> buyerMenu(loggedUser, products)
                    }
                }
            }

            "3" -> {
                println("Goodbye, thanks for a visit!")
                return
            }

            else -> println("Wrong option, try again.")
        }
    }
}

fun sellerMenu(seller: Seller, products: MutableList<Product>) {
    while (true) {
        println("\nWelcome to Seller menu! Your options:")
        println("1: add new product")
        println("2: remove a product")
        println("3: see your products")
        println("4: log out")

        when (readln()) {
            "1" -> {
                seller.newProduct()
                products.clear()
                products.addAll(User.UsersBase.users.filterIsInstance<Seller>().flatMap { it.allProducts })
            }

            "2" -> {
                println("Name of the product to remove:")
                val name = readln()
                seller.removeProducts(name)
                products.clear()
                products.addAll(User.UsersBase.users.filterIsInstance<Seller>().flatMap { it.allProducts })
            }

            "3" -> {
                if (seller.allProducts.isEmpty()) {
                    println("No products availible. You can add a new one with option '1'.")
                } else {
                    seller.allProducts.forEach { println(it) }
                }
            }

            "4" -> {
                println("You're logged out. Goodbye ${seller.login}!")
                return
            }

            else -> println("Wrong option. Try again.")
        }
    }
}

fun buyerMenu(buyer: Buyer, products: MutableList<Product>) {
    while (true) {
        println("\nWelcome to Buyer menu! Your options:")
        println("1: see all products")
        println("2: find a product")
        println("3: buy a product")
        println("4: leave an opinion")
        println("5: see all sellers")
        println("6: log out")

        when (readln()) {
            "1" -> buyer.seeAllProducts(products)

            "2" -> {
                println("Name the product to find:")
                val name = readln()
                buyer.searchProduct(products, name)
            }

            "3" -> {
                println("Name the product to buy:")
                val name = readln()
                buyer.buyProduct(products, name)
            }

            "4" -> {
                println("Name the product, you want to leave an opinion:")
                val name = readln()
                val product = products.find { it.name.equals(name, ignoreCase = true) }
                if (product != null) {
                    buyer.addOpinion(product)
                } else {
                    println("No product found")
                }
            }

            "5" -> {
                buyer.seeAllSellers(User.UsersBase.users)
            }

            "6" -> {
                println("You're logged out. Goodbye ${buyer.login}!")
                return
            }

            else -> println("Wrong choice, try again.")
        }
    }
}
