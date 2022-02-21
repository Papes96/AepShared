package com.example.aepapp.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.aepapp.inventory.InventoryFragment
import com.example.aepapp.sales.SalesFragment
import com.example.data.repository.Repository

class BaseViewModel(private val repository: Repository) : ViewModel() {
    private val _inventoryFragment = InventoryFragment()
    val inventoryFragment: Fragment
        get() = _inventoryFragment

    private val _salesFragment = SalesFragment()
    val salesFragment: Fragment
        get() = _salesFragment
}