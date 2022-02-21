package com.example.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.data.api.ServiceProvider
import com.example.data.db.DbProvider
import com.example.data.db.model.InventoryEntity
import com.example.data.db.model.SaleEntity
import com.example.data.db.model.SaleWithCart
import com.example.data.domain.model.Inventory
import com.example.data.domain.model.Sale
import com.example.data.mappers.asDataBaseModel
import com.example.data.mappers.asDomainModel
import com.example.data.mappers.extractCartItemEntity
import kotlinx.coroutines.*
import java.util.*


open class Repository(context: Context) {

    private val service = ServiceProvider.getRetrofit()
    private val inventoryDao = DbProvider.getDatabase(context).InventoryDao()
    private val cartDao = DbProvider.getDatabase(context).CartDao()
    private val saleDao = DbProvider.getDatabase(context).SaleDao()
    var prefs: SharedPreferences =
        context.getSharedPreferences("com.example.data", Context.MODE_PRIVATE)
    private val today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

    val inventory = MutableLiveData<List<Inventory>>()
    fun searchInventory(text: String){
        inventory.postValue(inventoryDao.searchInventory(text).asDomainModel())
    }

//    val inventory = Transformations.map(inventoryDao.getAll()) {
//        it.asDomainModel()
//    }

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
    }

    var sale = MutableLiveData(mapOf<String, List<Sale>>())

    fun querySales(date: Long){
        sale.postValue(saleDao.getGivenDate(date, (date - 15 * 24 * 60 * 60 * 1000)).asDomainModel().groupBy { it.date })
    }

    suspend fun inventoryCheck(): Boolean {
        return if (inventoryDao.getAll() == emptyList<InventoryEntity>() || today != prefs.getInt("lastInventoryUpdate", 0)){
            getInventory()
        } else true
    }

    suspend fun saleCheck(): Boolean {
        return if (inventoryDao.getAll() == emptyList<InventoryEntity>() || today != prefs.getInt("lastSaleUpdate", 0)
        ) {
            getSale()
        } else true
    }

    suspend fun getInventory(): Boolean {
        val response = service.getInventory()
        return if (response.isSuccessful) {
            response.body()?.let { inventoryDao.replaceAll(it.asDataBaseModel()) }
            prefs.edit().putInt("lastInventoryUpdate", today).apply()
            true
        } else false
    }

    suspend fun getSale(): Boolean {
        val response = service.getSale()
        return if (response.isSuccessful) {
            response.body()?.let { saleDao.replaceAll(it.asDataBaseModel()) }
            response.body()?.let { cartDao.replaceAll(it.extractCartItemEntity()) }
            prefs.edit().putInt("lastSaleUpdate", today).apply()
            true
        } else false
    }
}