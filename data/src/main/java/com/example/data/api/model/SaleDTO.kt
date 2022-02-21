package com.example.data.api.model

data class SaleDTO(
    val row: String,
    val date: String,
    val method: String,
    val products: MutableList<CartItemDTO>,
    var price: PriceDTO,
    var payment: Int,
    val notes: String,
    val cancelled: Boolean,
    var log: String
)

data class CartItemDTO(
    var row: String,
    var units: Int,
    val sku: String,
    val description: String,
    var ogValue: Int,
    var newValue: Int,
    var detail: String
)

data class PriceDTO(
    var subtotal: Int,
    var total: Int,
    var discounts: MutableMap<String, Int>
)
