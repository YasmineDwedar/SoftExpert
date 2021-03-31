package com.example.softexpert.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.softexpert.R
import com.example.softexpert.adaptors.CarAdaptor
import com.example.softexpert.databinding.ActivityMainBinding
import com.example.softexpert.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private var adaptor: CarAdaptor? = null
    private var viewModel: MainViewModel? = null
    var isLoading = false
    var isLastPage = false
    var isScrolling = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding?.progressBar?.visibility = View.VISIBLE
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun setListeners() {

        viewModel?.carsLiveData?.observe(this, Observer {

            binding?.progressBar?.visibility = View.INVISIBLE
            adaptor = CarAdaptor(this, it)
            buildRV()
        })

        viewModel?.networkState?.observe(this, Observer {
            binding?.progressBar?.visibility = View.INVISIBLE
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }


    fun buildRV() {
        binding?.apply {
            carRV.adapter = adaptor

            carRV.addOnScrollListener(this@MainActivity.scrollListner)
        }
    }

    val scrollListner = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutMAnager = recyclerView.layoutManager as LinearLayoutManager
            val lastVisibleItemPosition = layoutMAnager.findLastVisibleItemPosition() //19
            val listSize = viewModel?.carsLiveData?.value?.size
            if (lastVisibleItemPosition >= listSize?.minus(6)!!) {
                showProgressBar()
                viewModel?.getCars()
                isScrolling = false
            }


        }

        private fun showProgressBar() {
            binding?.progressBar?.visibility = View.VISIBLE

            isLoading = true
        }

        private fun hideProgressBar() {
            binding?.progressBar?.visibility = View.INVISIBLE
            isLoading = false
        }
    }
}