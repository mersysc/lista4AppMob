package com.example.lista4zpam

fun main(){
    var users = mutableListOf(null)
    var products = mutableListOf(null)

    val seller = Seller(
        id = "S1",
        login = "seller123",
        email = "seller@example.com",
        dateRegister = "2024-01-01",
        password = "pass123"
    )

    val buyer = Buyer(
        id = "S2",
        login = "buyer456",
        email = "buyer@example.com",
        dateRegister = "2024-01-05",
        password = "mypassword"
    )


}