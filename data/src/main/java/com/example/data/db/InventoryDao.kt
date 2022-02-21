package com.example.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.db.model.InventoryEntity

@Dao
interface InventoryDao {

    @Query("SELECT * from InventoryEntity")
    fun getAll(): LiveData<List<InventoryEntity>>

    @Query("SELECT * from InventoryEntity where name like ('%'||:text ||'%')")
    fun searchInventory(text: String): List<InventoryEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun replaceAll(list: List<InventoryEntity>)
}