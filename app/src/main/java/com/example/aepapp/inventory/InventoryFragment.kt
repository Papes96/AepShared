package com.example.aepapp.inventory

import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aepapp.MyViewModelFactory
import com.example.aepapp.databinding.FragmentInventoryBinding
import com.example.aepapp.inventory.adapter.InventoryAdapter

class InventoryFragment : Fragment() {

    lateinit var viewModel: InventoryViewModel
    lateinit var binding: FragmentInventoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentInventoryBinding.inflate(inflater, container, false)

        val adapter = InventoryAdapter()
        binding.rvInventory.adapter = adapter
        binding.rvInventory.layoutManager = LinearLayoutManager(requireActivity().application)

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(requireActivity().application)
        ).get(InventoryViewModel::class.java)
        viewModel.checkOnStart()

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.pbCenter.visibility = View.VISIBLE
            } else {
                binding.pbCenter.visibility = View.GONE
            }
        }

        viewModel.outDateData.observe(viewLifecycleOwner) {
            if (it) {
                binding.layoutOutdated.layoutOutdated.visibility = View.VISIBLE
            } else {
                binding.layoutOutdated.layoutOutdated.visibility = View.GONE
            }
        }

        viewModel.inventory.observe(viewLifecycleOwner) {
            if(it.isEmpty()) binding.layoutEmpty.layout.visibility = View.VISIBLE
            else {
                adapter.setData(it)
                binding.layoutEmpty.layout.visibility = View.GONE
            }
        }

        binding.tiRenew.setOnClickListener{
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            viewModel.getInventory()
        }

        binding.tiSearch.setOnClickListener {
            it.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            binding.search.text.toString().let {
                viewModel.searchInventory(it)
                binding.layoutSearch.layout.visibility = View.GONE
            }
        }

        return binding.root
    }
}