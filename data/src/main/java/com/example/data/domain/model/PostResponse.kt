package com.example.data.domain.model

data class PostResponse(
    val spreadsheetId: String,
    val updatedCells: Int,
    val updatedColumns: Int,
    val updatedRange: String,
    val updatedRows: Int
)
