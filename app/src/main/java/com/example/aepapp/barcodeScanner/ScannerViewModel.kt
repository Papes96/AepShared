package com.example.aepapp.barcodeScanner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScannerViewModel: ViewModel() {

    var barcode : MutableLiveData<String> =  MutableLiveData()
}