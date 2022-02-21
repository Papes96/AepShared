package com.example.aepapp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aepapp.base.BaseViewModel
import com.example.aepapp.inventory.InventoryViewModel
import com.example.aepapp.sales.MainViewModel
import com.example.data.repository.Repository

class MyViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass) {
            MainViewModel::class.java -> MainViewModel(Repository(context)) as T
            InventoryViewModel::class.java -> InventoryViewModel(Repository(context)) as T
            BaseViewModel::class.java -> BaseViewModel(Repository(context)) as T
            else -> throw IllegalArgumentException("ViewModel Not Found")}
    } // Obviously wrong. Just wanted to try it. Maybe can be extended changing the results of each to individual functions, but i would still need to sent multiple useless parameters


//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            MainViewModel(Repository(context)) as T
//        } else {
//            throw IllegalArgumentException("ViewModel Not Found")
//        }
//    }
}
