package com.example.lista4zpam

class Product(
    val name: String,
    val price: Double,
    val description: String,
    var amount: Int,
    val seller: Seller
){

    override fun toString(): String {
        return "$name, price: $price zl, amount: $amount, seller: ${seller.login}" +
                "\n $description "
    }
}
