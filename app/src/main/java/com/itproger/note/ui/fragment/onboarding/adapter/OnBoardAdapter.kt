package com.itproger.note.ui.fragment.onboarding.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.itproger.note.R
import com.itproger.note.databinding.ItemBoardBinding

class OnBoardAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardAdapter.OnBoardViewHolder>() {
    private val imglist = listOf(R.raw.note_anim_1, R.raw.note_anim_2, R.raw.note_anim_3)
    private val titlelist = listOf("Совет 1", "Совет 2", "Совет 3")
    private val descriptionlist = listOf("Нужно Пахать!", "Быть здерженным!", "Иметь Связи!")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardViewHolder {
        return OnBoardViewHolder(
            ItemBoardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = imglist.size


    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class OnBoardViewHolder(private val binding: ItemBoardBinding) :
        ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.itemImgBoard.setAnimation(imglist[position])
            binding.itemTvTitle.text = titlelist[position]
            binding.itemTvDesc.text = descriptionlist[position]
            binding.itemBtn.setOnClickListener {
                onClick()
            }
            if (position == imglist.size - 1) {
                binding.itemBtn.visibility = View.VISIBLE
            } else {
                binding.itemBtn.visibility = View.INVISIBLE
            }
        }

    }
}