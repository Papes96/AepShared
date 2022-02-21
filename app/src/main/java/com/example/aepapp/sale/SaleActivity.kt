package com.example.aepapp.sale

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aepapp.databinding.ActivitySaleBinding

class SaleActivity : AppCompatActivity()  {

    lateinit var viewModel: SaleViewModel
    lateinit var binding: ActivitySaleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dialog = AddDialogFragment().apply { this.show(supportFragmentManager, "customDialog")}


        binding.ibAdd.setOnClickListener {
            dialog.show(supportFragmentManager, "customDialog")
        }



    }
}