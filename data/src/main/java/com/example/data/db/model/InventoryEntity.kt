package com.example.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InventoryEntity(
    @PrimaryKey
    val row: String,
    val name: String,
    val price: String,
    val stock: String
)
