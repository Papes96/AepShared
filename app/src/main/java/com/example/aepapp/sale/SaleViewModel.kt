package com.example.aepapp.sale

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.api.model.InventoryDTO
import com.example.data.domain.model.Sale
import kotlinx.coroutines.*

class SaleViewModel : ViewModel()  {

    var job: Job? = null
    val errorMessage = MutableLiveData<String>()

    var sale = MutableLiveData<Sale>()
    var results = MutableLiveData<List<InventoryDTO>>()
    var resultsNames = MutableLiveData<List<String>>()
    var calculatedSale = MutableLiveData<Sale>()
    var search = MutableLiveData<String>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

//    fun getInventory(search: String) {
//        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
//            val response = service.getInventory(search) //TODO [Improve] remove !!
//            withContext(Dispatchers.Main) {
//                if (response.isSuccessful) {
//                    results.postValue(response.body())
//                    resultsNames.postValue(namesOnly(response.body()))
//                } else {
//                    onError("Error: ${response.message()}")
//                }
//            }
//        }
//    }

    private fun namesOnly(results: List<InventoryDTO>?): MutableList<String> {
        val names = mutableListOf<String>()
        results?.forEach {
            names.add(it.name)
        }
        return names
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}