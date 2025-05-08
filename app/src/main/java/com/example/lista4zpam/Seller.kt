package com.example.lista4zpam

class Seller(
    id: String, login: String,
    email: String, dateRegister: String, password: String
) : User(id, login, email, dateRegister, password) {

    val allProducts = mutableListOf<Product>()

    // dodawanie nowego produktu
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