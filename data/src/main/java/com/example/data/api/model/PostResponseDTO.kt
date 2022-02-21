package com.example.data.api.model

data class PostResponseDTO(
    val spreadsheetId: String,
    val updatedCells: Int,
    val updatedColumns: Int,
    val updatedRange: String,
    val updatedRows: Int
)
