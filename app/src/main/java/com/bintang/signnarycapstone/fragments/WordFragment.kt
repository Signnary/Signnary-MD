package com.bintang.signnarycapstone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bintang.signnarycapstone.R
import com.bintang.signnarycapstone.adapters.WordAdapter
import com.bintang.signnarycapstone.adapters.WordItem

class WordFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var wordAdapter: WordAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_word, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerView)

        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager

        val wordList = getWordList()
        wordAdapter = WordAdapter(wordList)
        recyclerView.adapter = wordAdapter

        return rootView
    }

    private fun getWordList(): List<WordItem> {
        return listOf(
            WordItem(R.drawable.alis, "Alis"),
            WordItem(R.drawable.bibir, "Bibir"),
            WordItem(R.drawable.dada, "Dada"),
            WordItem(R.drawable.dahi, "Dahi"),
            WordItem(R.drawable.geraham, "Geraham"),
            WordItem(R.drawable.ginjal, "Ginjal"),
            WordItem(R.drawable.hidung, "Hidung"),
            WordItem(R.drawable.jantung, "Jantung"),
            WordItem(R.drawable.kepala, "Kepala"),
            WordItem(R.drawable.lambung, "Lambung"),
            WordItem(R.drawable.mata, "Mata"),
            WordItem(R.drawable.mulut, "Mulut"),
            WordItem(R.drawable.pipi, "Pipi"),
            WordItem(R.drawable.telinga, "Telinga"),
            WordItem(R.drawable.wajah, "Wajah")
        )
    }
}
