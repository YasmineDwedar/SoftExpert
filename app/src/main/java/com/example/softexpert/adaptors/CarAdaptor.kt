package com.example.softexpert.adaptors

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.softexpert.R
import com.example.softexpert.databinding.CarItemBinding
import com.example.softexpert.models.CarModel
import com.example.softexpert.models.Data
import java.util.ArrayList

class CarAdaptor(var context: Context, var arrName: List<Data>)  : RecyclerView.Adapter<CarAdaptor.ViewHolder>() {


    inner class ViewHolder(val binding: CarItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(CarItemBinding.inflate(LayoutInflater.from(context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.apply {

            Glide.with(context).load(arrName[position].imageUrl).fitCenter().override(500, 500).into(carImg)
            compJobTitle.text=arrName[position].brand
            if (arrName[position].isUsed==false){

                compName.text="new"

            }else{
                compName.text="used"
            }

        }
    }

    override fun getItemCount(): Int {
   return  arrName.size
    }
}