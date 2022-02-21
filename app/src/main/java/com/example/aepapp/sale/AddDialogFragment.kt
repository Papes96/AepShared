package com.example.aepapp.sale

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.aepapp.barcodeScanner.ScannerActivity
import com.example.aepapp.databinding.DialogFragmentAddBinding
import com.example.aepapp.sale.adapters.StringListAdapter
import com.example.data.api.model.InventoryDTO
import java.util.*

//TODO [Improve] DialogFragment isn't the best choise
class AddDialogFragment : DialogFragment() {

    lateinit var binding: DialogFragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFragmentAddBinding.inflate(inflater, container, false)
        Objects.requireNonNull(dialog)?.window!!.requestFeature(Window.FEATURE_NO_TITLE) //TODO [Improve] remove !!
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        Objects.requireNonNull(dialog)?.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        ) //TODO [Improve] remove !!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(requireActivity()).get(SaleViewModel::class.java)

        val simpleContractRegistration =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultStr ->
                if (resultStr == null)
                    Toast.makeText(
                        activity,
                        "Nothing selected / User Cancelled",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                else
                    viewModel.search.postValue(resultStr.data?.getStringExtra("result"))
            }

        val adapter = activity?.let { StringListAdapter(it) }
        binding.acSearch.setAdapter(adapter)

        binding.acSearch

        binding.ibAdd.setOnClickListener() {
            it.performHapticFeedback(1)
            binding.etQuantity.apply {
                setText((this.text.toString().toInt() + 1).toString())
            }
        }

        binding.ibRemove.setOnClickListener() {
            it.performHapticFeedback(1)
            binding.etQuantity.apply {
                setText((this.text.toString().toInt() - 1).toString())
            }
        }

        binding.etQuantity.addTextChangedListener() {
            if (it.contentEquals("0") || it.isNullOrEmpty()) binding.etQuantity.setText("1")
            binding.mbSubmit.text = "AGREGAR ${binding.etQuantity.text} POR $500"
        }

        binding.ibScan.setOnClickListener {
            val intent = Intent(activity, ScannerActivity::class.java)
            simpleContractRegistration.launch(intent)
        }

        viewModel.search.observe(this, {
            binding.tiSearch.editText?.setText(it)
        })


        // TODO [Improve] Do less searchs
        // TODO [Feature] Select value when a code is scanned
        // TODO [Improve] Do not display results when there is less than 3 chars
        // TODO [Improve] remove keyboard when scrolling
        binding.tiSearch.editText?.addTextChangedListener(
            afterTextChanged = { //TODO [Ask] How to wait until the user has stop writing for a second
                if (it != null) {
                    if (it.length >= 3) {
                        //viewModel.getInventory(it.toString())
                    }
                    if (it.length < 3 && viewModel.results.value != emptyList<InventoryDTO>()){
                        viewModel.results.value = emptyList<InventoryDTO>()
                        viewModel.resultsNames.value = emptyList<String>()

                    }
                }
            }
        )

        viewModel.resultsNames.observe(this, {
            adapter?.updateList(it)
        })
    }

}
