package com.bintang.signnarycapstone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bintang.signnarycapstone.R

data class AbjadItem(val imageResId: Int, val text: String)

class AbjadAdapter(private val abjadList: List<AbjadItem>) : RecyclerView.Adapter<AbjadAdapter.AbjadViewHolder>() {

    class AbjadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val abjadImage: ImageView = itemView.findViewById(R.id.abjad_image)
        val abjadText: TextView = itemView.findViewById(R.id.abjad_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbjadViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_abjad, parent, false)
        return AbjadViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AbjadViewHolder, position: Int) {
        val currentItem = abjadList[position]
        holder.abjadImage.setImageResource(currentItem.imageResId)
        holder.abjadText.text = currentItem.text
    }

    override fun getItemCount() = abjadList.size
}
