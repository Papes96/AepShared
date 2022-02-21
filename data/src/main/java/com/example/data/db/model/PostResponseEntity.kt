package com.example.data.db.model

import androidx.room.Entity

@Entity
data class PostResponseEntity(
    val spreadsheetId: String,
    val updatedCells: Int,
    val updatedColumns: Int,
    val updatedRange: String,
    val updatedRows: Int
)
