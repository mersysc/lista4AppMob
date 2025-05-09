package com.example.lista4zpam

/**
 * Klasa sprzedawca dziedziczaca po klasie User
 * odpowiada za dodawanie i usuwanie produktow do
 * sprzedazy
 */
class Seller(
    id: String, login: String,
    email: String, dateRegister: String, password: String
) : User(id, login, email, dateRegister, password) {

    val allProducts = mutableListOf<Product>()

    /**
     * Metoda dodaje produkt o okreslonej nazwie, cenie,
     * ilosci oraz opisie, a nastepnie trafia na liste
     * wszystkich produktow do sprzedazy
     */
    fun newProduct(){
        println("Name of new product:")
        val name = readln()
        println("Price of product")
        val price = readln().toDouble()
        println("Amount: ")
        val amount = readln().toInt()
        println("Description: ")
        val description = readln()

        val product = Product(name, price, description, amount, this)
        allProducts.add(product)
        println("New product: $product")
    }

    /**
     * Metoda usuwa produkt o okreslonej nazwie
     */
    fun removeProducts(name: String){
        if(allProducts.isEmpty()){
            println("There are no products to remove!")
        }

        val toRemove = allProducts.find{it.name.equals(name, ignoreCase = true)}
        if (toRemove!= null){
            allProducts.remove(toRemove)
            println("Product ${toRemove.name} was removed")
        }else{
            println("Product not found")
        }
    }

}