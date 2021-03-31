package com.example.softexpert.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.softexpert.R
import com.example.softexpert.adaptors.CarAdaptor
import com.example.softexpert.databinding.ActivityMainBinding
import com.example.softexpert.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private var adaptor: CarAdaptor? = null
    private var viewModel: MainViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding?.progressBar?.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun setListeners() {

        viewModel?.carLiveData?.observe(this, Observer {

            binding?.progressBar?.visibility = View.INVISIBLE
            adaptor = CarAdaptor(this, it.data)
            binding?.carRV?.adapter = adaptor
        })

        viewModel?.networkState?.observe(this, Observer {
            binding?.progressBar?.visibility=View.INVISIBLE
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }
}