package com.bintang.signnarycapstone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bintang.signnarycapstone.R

data class Item(val type: Int, val text: String)

class SearchAdapter(private var itemList: List<Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var onItemLongClickListener: ((Item) -> Unit)? = null

    companion object {
        const val TYPE_ABJAD = 0
        const val TYPE_NUMBER = 1
        const val TYPE_WORD = 2
        const val TYPE_SENTENCE = 3
    }

    override fun getItemViewType(position: Int): Int {
        return itemList[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ABJAD -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_layout_abjad, parent, false)
                AbjadViewHolder(view)
            }
            TYPE_NUMBER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_layout_number, parent, false)
                NumberViewHolder(view)
            }
            TYPE_WORD -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_layout_word, parent, false)
                WordViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = itemList[position]
        when (holder) {
            is AbjadViewHolder -> holder.bind(item)
            is NumberViewHolder -> holder.bind(item)
            is WordViewHolder -> holder.bind(item)
        }

        holder.itemView.setOnLongClickListener {
            onItemLongClickListener?.invoke(item)
            true
        }
    }

    override fun getItemCount() = itemList.size

    fun updateList(newList: List<Item>) {
        itemList = newList
        notifyDataSetChanged()
    }

    fun setOnItemLongClickListener(listener: (Item) -> Unit) {
        onItemLongClickListener = listener
    }

    class AbjadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.abjad_text)
        fun bind(item: Item) {
            textView.text = item.text
        }
    }

    class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.number_text)
        fun bind(item: Item) {
            textView.text = item.text
        }
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.word_text)
        fun bind(item: Item) {
            textView.text = item.text
        }
    }
}
