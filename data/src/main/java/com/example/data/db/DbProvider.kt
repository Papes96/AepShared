package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.db.model.CartItemEntity
import com.example.data.db.model.InventoryEntity
import com.example.data.db.model.SaleEntity

@Database(entities = [SaleEntity::class, CartItemEntity::class, InventoryEntity::class], version = 2)
abstract class DbProvider : RoomDatabase() {

    abstract fun SaleDao(): SaleDao
    abstract fun CartDao(): CartDao
    abstract fun InventoryDao(): InventoryDao

    companion object {
        @Volatile
        private var instance: DbProvider? = null

        fun getDatabase(context: Context): DbProvider =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, DbProvider::class.java, "db")
                .fallbackToDestructiveMigration()
                .build()
    }
}