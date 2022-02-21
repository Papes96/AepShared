package com.example.aepapp.sale.adapters

/*
class CartAdapter(private val itemList: ArrayList<Sale>) : RecyclerView.Adapter<SaleAdapter.SaleViewHolder>() {

    inner class SaleViewHolder(val binding: ItemSaleBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleViewHolder {
        val binding = ItemSaleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SaleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaleViewHolder, position: Int) {
        with(holder){
            with(itemList[position]){
                binding.header.text = "${this.method} - ${this.notes}"
                binding.total.text = this.total

                if(this.products.size == 1)  binding.product.text = this.products[0].units + this.products[0].description
                else binding.product.text = "${this.products.size} productos"
            }
        }
    }

    override fun getItemCount(): Int { return itemList.size }
}*/