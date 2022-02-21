package com.example.aepapp.sale.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.example.aepapp.R

class StringListAdapter(context: Context): ArrayAdapter<String>(context, R.layout.custom_select_dialog_item) {

    private var list = listOf<String>()

    fun updateList(new: List<String>){
        list = new
        addAll(list)
        notifyDataSetChanged()
    }

    override fun addAll(vararg items: String?) {
        super.addAll(*items)
    }

}