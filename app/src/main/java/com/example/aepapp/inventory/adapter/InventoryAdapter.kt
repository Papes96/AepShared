package com.example.aepapp.inventory.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aepapp.databinding.ItemInventoryBinding
import com.example.data.domain.model.Inventory

class InventoryAdapter() : RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>() {

    inner class InventoryViewHolder(val binding: ItemInventoryBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var context: Context
    private fun getContext() = context
    private var list: List<Inventory> = emptyList()

    fun setData(new: List<Inventory>) {
        list = new
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val binding =
            ItemInventoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InventoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.tvName.text = this.name
                binding.tvPrice.text = this.price
                binding.tvStock.text = this.stock
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}