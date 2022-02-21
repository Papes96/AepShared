package com.example.aepapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import com.example.aepapp.R
import com.example.aepapp.databinding.ActivityBaseBinding
import com.example.aepapp.MyViewModelFactory

class BaseActivity : AppCompatActivity() {

    lateinit var viewModel: BaseViewModel
    lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(this)
        ).get(BaseViewModel::class.java)

        displayFragment(viewModel.inventoryFragment)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.sales -> displayFragment(viewModel.salesFragment)
                R.id.inventory -> displayFragment(viewModel.inventoryFragment)
            }
            true
        }
    }

    private fun displayFragment(fragment: Fragment) {
        val activeFrag = supportFragmentManager.fragments.getOrNull(0)

        if (fragment != activeFrag) {
            supportFragmentManager.commit {
                setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                if (supportFragmentManager.findFragmentById(fragment.id) == null) {
                    add(binding.fl.id, fragment)
                } else {
                    attach(fragment)
                }
                if (activeFrag != null) {
                    detach(activeFrag)
                }
            }
        }
    }
}
