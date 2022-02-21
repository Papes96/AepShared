package com.example.aepapp.sales

import android.content.Intent
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aepapp.MyViewModelFactory
import com.example.aepapp.databinding.FragmentSalesBinding
import com.example.aepapp.sales.adapter.DateAdapter
import com.example.aepapp.dialogs.CalendarDialog
import com.example.aepapp.sale.SaleActivity

class SalesFragment : Fragment() {

    lateinit var viewModel: MainViewModel
    lateinit var binding: FragmentSalesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // https://guides.codepath.com/android/Handling-Configuration-Changes#saving-and-restoring-fragment-state
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        super.onCreate(savedInstanceState)
        binding = FragmentSalesBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(requireActivity().application) //Por que no puedo usar context aqui
        ).get(MainViewModel::class.java)

        val adapter = DateAdapter()
        binding.rvDates.adapter = adapter
        binding.rvDates.layoutManager = LinearLayoutManager(context)

        viewModel.checkOnStart()

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.pbCenter.visibility = View.VISIBLE
            } else {
                binding.pbCenter.visibility = View.GONE
            }
        }

        viewModel.sales.observe(viewLifecycleOwner) {
            if(it.isEmpty()) binding.layoutEmpty.layout.visibility = View.VISIBLE
            else {
                adapter.setData(it)
                binding.layoutEmpty.layout.visibility = View.GONE
            }
        }

        viewModel.outDateData.observe(viewLifecycleOwner) {
            if (it) {
                binding.layoutOutdated.layoutOutdated.visibility = View.VISIBLE
            } else {
                binding.layoutOutdated.layoutOutdated.visibility = View.GONE
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        binding.fabAdd.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            startActivity(Intent(context, SaleActivity::class.java))
        }

        val datePickerDialog = CalendarDialog(requireContext()) { _, y, m, d ->
            viewModel.setDate(viewModel.cToString(d, m+1, y))
        }

        binding.ibCalendar.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            datePickerDialog.show()
        }

        viewModel.date.observe(viewLifecycleOwner) {
            viewModel.querySales()
        }

        binding.tiRenew.setOnClickListener{
            viewModel.getSales()
        }

        return binding.root
    }
}