package com.example.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.db.model.CartItemEntity
import com.example.data.db.model.SaleEntity
import com.example.data.db.model.SaleWithCart

@Dao
interface SaleDao {

    @Query("SELECT * from SaleEntity")
    fun getAll(): LiveData<List<SaleEntity>>

    @Query("SELECT * from SaleEntity")
    @Transaction
    fun getAllSalesWithCart(): LiveData<List<SaleWithCart>>

    @Query("SELECT * FROM SaleEntity WHERE date BETWEEN :dateEnd AND :dateStart")
    @Transaction
    fun getGivenDate(dateStart: Long, dateEnd: Long): List<SaleWithCart>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cartItem: CartItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replaceAll(list: List<SaleEntity>)
}
