package com.itproger.note.ui.fragment.notifications.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.itproger.note.data.Car
import com.itproger.note.databinding.ItemTaskBinding
import com.itproger.note.model.Task

class CarAdapter : Adapter<CarAdapter.CarViewHolder>() {

    private val list = arrayListOf<Car>()
    private val adapter = CarAdapter()

    fun setCar(car: Car) {
        list.add(0, car)
        notifyDataSetChanged()
    }

    fun setCars(cars: List<Car>) {
        list.clear()
        list.addAll(cars)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CarViewHolder(val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(car: Car) = with(binding) {
            binding.itemTvTitle.text = car.label
            binding.itemTvDescription.text = car.model
        }
    }
}

