package com.example.lista4zpam

class Buyer(
    id: String, login: String,
    email: String, dateRegister: String, password: String
) : User(id, login, email, dateRegister, password), Payment, Opinion {

    fun seeAllProducts(products: List<Product>){
        if(products.isEmpty()){
            println("Nothing is being sold")
            return
        }
        println("List of products: ")
        products.forEach{ println("$it") }
    }

    fun seeAllSellers(users: List<User>){
        val sellers = users.filterIsInstance<Seller>()
        if(sellers.isEmpty()){
            println("There are none sellers")
            return
        }
        println("List of sellers: ")
        sellers.forEach{ println("${it.login}") }
    }

    fun searchProduct(products: List<Product>, name: String){
        val toFind = products.filter{it.name.contains(name, ignoreCase = true)}
        if(toFind.isEmpty()){
            println("No results for $name")
            return
        }else{
            println("Results: ")
            toFind.forEach{println("$it")}
        }
    }

    fun buyProduct(products: List<Product>, name: String){
        val toBuy = products.find{it.name.equals(name, ignoreCase = true) && it.amount > 0}
        if (toBuy != null){
            toBuy.amount--
            payForProduct(toBuy.price)
            println("You succesfully have bought a product ${toBuy.name}")
        }
    }

    override fun payForProduct(amount: Double) {
        println("You paid $amount zl for product")
    }

    override fun addOpinion(product: Product) {
        println("Enter your opinion about ${product.name} and ${product.seller}")
        readln()
    }
}