package com.example.aepapp.sales.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aepapp.R
import com.example.aepapp.databinding.ItemDateBinding
import com.example.data.domain.model.Sale

class DateAdapter(): RecyclerView.Adapter<DateAdapter.DateViewHolder>(){

    //TODO [Improve] Set RecyclerView with null verification
    var sales = mapOf<String,List<Sale>>()
    var map = sales
    var dateList = sales.keys.toTypedArray()
    private lateinit var context: Context
    private fun getContext() = context

    inner class DateViewHolder(val binding:ItemDateBinding):RecyclerView.ViewHolder(binding.root)

    fun setData(new: Map<String, List<Sale>>){
        sales = new
        map = sales
        dateList = sales.keys.toTypedArray()
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int): DateViewHolder {
        val binding = ItemDateBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position:Int){
        with(holder){
            with(dateList[position]){
                val totals = getTotals(map, this)
                binding.date.text = dateList[position]
                binding.totalBlack.text = context.getString(R.string.price, totals[0])
                binding.totalWhite.text = context.getString(R.string.price, totals[1])
                binding.sales.layoutManager = LinearLayoutManager(getContext())
                binding.sales.adapter = map[this]?.let { SaleAdapter(it) }
            }
        }
    }

    private fun getTotals(sales: Map<String, List<Sale>>, key: String): Array<Int> {
        val totals = arrayOf( 0, 0)
        for(sale in sales[key]!!){
            if(sale.method == "Efectivo") totals[0] += sale.payment else totals[1]+= sale.payment
        }
        return totals
    }

    override fun getItemCount():Int{return dateList.size}
}

