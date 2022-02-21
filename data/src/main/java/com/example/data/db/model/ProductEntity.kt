package com.example.data.db.model

import androidx.room.Entity

@Entity
data class ProductEntity(
    val row: String,
    val tn: String,
    val cn: String,
    val sku: String,
    val brand: String,
    val category: String,
    val color: String,
    val size: String,
    val genre: String,
    val Weight: String,
    val packageSize: String,
    val description: String,
    val storeName: String,
    val supplierName: String,
    val price: String,
    val promoPrice: String,
    val minStock: String,
    val stock: String,
    val profit: String,
    val cost: String,
    val lastSale: String,
    val totalSales: String,
    val lastSupplier: String,
    val lastDate: String,
    val deprecated: String,
)