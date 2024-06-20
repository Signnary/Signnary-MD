package com.bintang.signnarycapstone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bintang.signnarycapstone.R

data class NumberItem(val imageResId: Int, val text: String)

class NumberAdapter(private val numberList: List<NumberItem>) : RecyclerView.Adapter<NumberAdapter.NumberViewHolder>(){

    class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val numberImage: ImageView = itemView.findViewById(R.id.number_image)
        val numberText: TextView = itemView.findViewById(R.id.number_text)
        val numberTextBackground: ImageView = itemView.findViewById(R.id.number_text_background)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_number, parent, false)
        return NumberViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val currentItem = numberList[position]
        holder.numberImage.setImageResource(currentItem.imageResId)
        holder.numberText.text = currentItem.text
    }

    override fun getItemCount() = numberList.size
}
