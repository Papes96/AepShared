package com.example.aepapp.sales

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.domain.model.Sale
import com.example.data.mappers.convertDateToLong
import com.example.data.repository.Repository
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

//construir el repository en el constructor del viewModel
class MainViewModel(private val repository: Repository) : ViewModel() {

    val loading = MutableLiveData<Boolean>()
    val outDateData = MutableLiveData<Boolean>(false)
    val errorMessage = MutableLiveData<String>()

    var c = Calendar.getInstance()
    var date = MutableLiveData(
        cToString(
            c.get(Calendar.DAY_OF_MONTH),
            c.get(Calendar.MONTH)+1,
            c.get(Calendar.YEAR)
        )
    )

    fun setDate(string: String) {
        date.postValue(string)
    }

    private val _sales = repository.sale
    val sales: LiveData<Map<String, List<Sale>>>
        get() = _sales

    fun checkOnStart() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                repository.saleCheck()
            }.onSuccess {
                loading.postValue(false)
                outDateData.postValue(false)
            }.onFailure {
                loading.postValue(false)
                outDateData.postValue(true)
            }
        }
    }

    fun querySales(){
    loading.value = true
    viewModelScope.launch(Dispatchers.IO) {
        runCatching {
            date.value?.let { repository.querySales(convertDateToLong(it)) }
        }.onSuccess {
            loading.postValue(false)
        }.onFailure {
            loading.postValue(false)
            outDateData.postValue(true)
        }
    }
}
    fun getSales() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                repository.getSale()
            }.onSuccess {
                loading.postValue(false)
            }.onFailure {
                loading.postValue(false)
            }
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
        loading.postValue(false)
    }

    fun cToString(day: Int, month: Int, year: Int): String {
        val dS = if (day < 10) "0$day" else day.toString()
        val mS = if (month < 9) "0${month}" else (month).toString()
        val yS = year.toString().takeLast(2)

        return "$dS/$mS/$yS"
    }

    fun convertDateToLong(date: String): Long {
        val df = SimpleDateFormat("dd/MM/yy")
        return df.parse(date).time
    }
}
