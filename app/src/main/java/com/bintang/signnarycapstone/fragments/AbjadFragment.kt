package com.bintang.signnarycapstone.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bintang.signnarycapstone.R
import com.bintang.signnarycapstone.adapters.AbjadAdapter
import com.bintang.signnarycapstone.adapters.AbjadItem

class AbjadFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_abjad, container, false)

        val abjadList = listOf(
            AbjadItem(R.drawable.a, "A"),
            AbjadItem(R.drawable.b, "B"),
            AbjadItem(R.drawable.c, "C"),
            AbjadItem(R.drawable.d, "D"),
            AbjadItem(R.drawable.e, "E"),
            AbjadItem(R.drawable.f, "F"),
            AbjadItem(R.drawable.g, "G"),
            AbjadItem(R.drawable.h, "H"),
            AbjadItem(R.drawable.i, "I"),
            AbjadItem(R.drawable.j, "J"),
            AbjadItem(R.drawable.k, "K"),
            AbjadItem(R.drawable.l, "L"),
            AbjadItem(R.drawable.m, "M"),
            AbjadItem(R.drawable.n, "N"),
            AbjadItem(R.drawable.o, "O"),
            AbjadItem(R.drawable.p, "P"),
            AbjadItem(R.drawable.q, "Q"),
            AbjadItem(R.drawable.r, "R"),
            AbjadItem(R.drawable.s, "S"),
            AbjadItem(R.drawable.t, "T"),
            AbjadItem(R.drawable.u, "U"),
            AbjadItem(R.drawable.v, "V"),
            AbjadItem(R.drawable.w, "W"),
            AbjadItem(R.drawable.x, "X"),
            AbjadItem(R.drawable.y, "Y"),
            AbjadItem(R.drawable.z, "Z")
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val gridLayoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = gridLayoutManager

        recyclerView.adapter = AbjadAdapter(abjadList)

        return view
    }
}
