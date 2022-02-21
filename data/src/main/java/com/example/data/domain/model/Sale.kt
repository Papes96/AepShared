package com.example.data.domain.model

data class Sale(
    val row: String,
    val date: String, //Turn to local date again
    val method: String,
    val products: MutableList<CartItem>,
    var price: String,
    var payment: Int,
    val notes: String,
    val cancelled: Boolean,
    var log: String
)

data class CartItem(
    var units: Int,
    val sku: String,
    val description: String,
    var ogValue: Int,
    var newValue: Int,
    var detail: String
)
