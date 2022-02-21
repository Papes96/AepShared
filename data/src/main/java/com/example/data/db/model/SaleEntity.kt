package com.example.data.db.model

import androidx.room.*


@Entity(indices = [Index("row", unique = true)])
data class SaleEntity(
    @PrimaryKey
    val row: String,
    val date: Long,
    val method: String,
    var price: String,
    var payment: Int,
    val notes: String,
    val cancelled: Boolean,
    var log: String
)


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = SaleEntity::class,
            childColumns = ["saleRow"],
            parentColumns = ["row"]
        )
    ],
    indices = [androidx.room.Index("id", unique = true)]
)
data class CartItemEntity(
    var saleRow: String,
    var units: Int,
    val sku: String,
    val description: String,
    @ColumnInfo(name = "og_value")
    var ogValue: Int,
    @ColumnInfo(name = "new_value")
    var newValue: Int,
    var detail: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

}

data class SaleWithCart(
    @Embedded var SaleEntity: SaleEntity,
    @Relation(
        parentColumn = "row",
        entityColumn = "saleRow",
    )
    var cart: List<CartItemEntity>? = null
)