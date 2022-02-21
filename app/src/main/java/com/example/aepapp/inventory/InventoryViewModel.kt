package com.example.aepapp.inventory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.domain.model.Inventory
import com.example.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InventoryViewModel(private val repository: Repository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val outDateData = MutableLiveData<Boolean>(false)
    val errorMessage = MutableLiveData<String>()

    private val _inventory = repository.inventory
    val inventory: LiveData<List<Inventory>>
        get() = _inventory

    fun searchInventory(text: String) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                repository.searchInventory(text)
            }.onSuccess {
                loading.postValue(false)
            }.onFailure {
                loading.postValue(false)
                outDateData.postValue(true)
            }
        }
    }

    fun checkOnStart() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                repository.inventoryCheck()
            }.onSuccess {
                loading.postValue(false)
                outDateData.postValue(false)
            }.onFailure {
                loading.postValue(false)
                outDateData.postValue(true)
            }
        }
    }

    fun getInventory() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                repository.getInventory()
            }.onSuccess {
                loading.postValue(false)
                outDateData.postValue(false)
            }.onFailure {
                loading.postValue(false)
            }
        }
    }
}