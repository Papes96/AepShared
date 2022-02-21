package com.example.data.mappers

import com.example.data.api.model.InventoryDTO
import com.example.data.api.model.PriceDTO
import com.example.data.api.model.SaleDTO
import com.example.data.db.model.CartItemEntity
import com.example.data.db.model.InventoryEntity
import com.example.data.db.model.SaleEntity
import com.example.data.db.model.SaleWithCart
import com.example.data.domain.model.CartItem
import com.example.data.domain.model.Inventory
import com.example.data.domain.model.Sale
import java.text.SimpleDateFormat
import java.util.*

@JvmName("inventoryDTOAsDataBaseModel")
fun List<InventoryDTO>.asDataBaseModel(): List<InventoryEntity> {
    return map {
        InventoryEntity(
            row = it.row,
            name = it.name,
            price = it.price,
            stock = it.stock
        )
    }
}

@JvmName("inventoryEntityAsDomainModel")
fun List<InventoryEntity>.asDomainModel(): List<Inventory> {
    return map {
        Inventory(
            row = it.row,
            name = it.name,
            price = it.price,
            stock = it.stock
        )
    }
}

@JvmName("saleDTOAsDataBaseModel")
fun List<SaleDTO>.asDataBaseModel(): List<SaleEntity> {
    return map {
        SaleEntity(
            row = it.row,
            date = convertDateToLong(it.date),
            method = it.method,
            price = it.price.asDataBaseModel(),
            payment = it.payment,
            notes = it.notes,
            cancelled = it.cancelled,
            log = it.log
        )
    }
}

@JvmName("saleEntityAsDomainModel")
fun List<SaleEntity>.asDomainModel(): List<Sale> {
    return map {
        Sale(
            row = it.row,
            date = it.date.toString(),
            method = it.method,
            products = mutableListOf<CartItem>(),
            price = it.price,
            payment = it.payment,
            notes = it.notes,
            cancelled = it.cancelled,
            log = it.log
        )
    }
}

@JvmName("saleWithCartAsDomainModel")
fun List<SaleWithCart>.asDomainModel(): List<Sale> {
    return map {
        Sale(
            row = it.SaleEntity.row,
            date = convertLongToTime(it.SaleEntity.date),
            method = it.SaleEntity.method,
            products = it.cart?.asDomainModel() as MutableList<CartItem>,
            price = it.SaleEntity.price,
            payment = it.SaleEntity.payment,
            notes = it.SaleEntity.notes,
            cancelled = it.SaleEntity.cancelled,
            log = it.SaleEntity.log
        )
    }
}


@JvmName("saleDTOExtractCartItemEntity")
fun List<SaleDTO>.extractCartItemEntity(): List<CartItemEntity> {
    val cartList = mutableListOf<CartItemEntity>()
    this.forEach { sale ->
        cartList.addAll(sale.products.map {
            CartItemEntity(
                saleRow = sale.row,
                units = it.units,
                sku = it.sku,
                description = it.description,
                ogValue = it.ogValue,
                newValue = it.newValue,
                detail = it.detail
            )
        })
    }
    return cartList
}

@JvmName("cartItemEntityAsDomainModel")
fun List<CartItemEntity>.asDomainModel(): List<CartItem> {
    return map {
        CartItem(
            units = it.units,
            sku = it.sku,
            description = it.description,
            ogValue = it.ogValue,
            newValue = it.newValue,
            detail = it.detail
        )
    }
}

@JvmName("priceDTOAsDataBaseModel")
fun PriceDTO.asDataBaseModel(): String {
    discounts["Total"] = total
    discounts["Subtotal"] = subtotal
    return discounts.toString()
}

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("dd-MM-yyyy")
    return format.format(date)
}

fun convertDateToLong(date: String): Long {
    val df = SimpleDateFormat("yyyy-MM-dd")
    return df.parse(date).time
}
