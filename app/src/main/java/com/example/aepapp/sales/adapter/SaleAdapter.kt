package com.example.aepapp.sales.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aepapp.R
import com.example.aepapp.databinding.ItemSaleBinding
import com.example.data.domain.model.Sale

class SaleAdapter(private val itemList: List<Sale>) : RecyclerView.Adapter<SaleAdapter.SaleViewHolder>() {

    private lateinit var context: Context
    private fun getContext() = context
    inner class SaleViewHolder(val binding: ItemSaleBinding): RecyclerView.ViewHolder(binding.root)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleViewHolder {
        val binding = ItemSaleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SaleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaleViewHolder, position: Int) {
        with(holder){
            with(itemList[position]){
                binding.header.text = method
                if(this.notes != "") binding.header.text = context.getString(R.string.sale_notes, method, notes)

                binding.tvTotal.text = context.getString(R.string.price, payment)

                if(this.products.size == 1)  binding.tvProduct.text = context.getString(R.string.quantity_name, products[0].units, products[0].description)
                else binding.tvProduct.text = context.getString(R.string.products, products.size)
            }
        }
    }

    override fun getItemCount(): Int { return itemList.size }
}