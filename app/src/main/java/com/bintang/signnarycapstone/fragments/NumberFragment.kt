import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bintang.signnarycapstone.R
import com.bintang.signnarycapstone.adapters.NumberAdapter
import com.bintang.signnarycapstone.adapters.NumberItem

class NumberFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var numberAdapter: NumberAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_number, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerView)

        // Set GridLayoutManager with 2 columns
        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager

        val numberList = getNumberList()
        numberAdapter = NumberAdapter(numberList)
        recyclerView.adapter = numberAdapter

        return rootView
    }

    private fun getNumberList(): List<NumberItem> {
        return listOf(
            NumberItem(R.drawable.satu, "1"),
            NumberItem(R.drawable.dua, "2"),
            NumberItem(R.drawable.tiga, "3"),
            NumberItem(R.drawable.empat, "4"),
            NumberItem(R.drawable.lima, "5"),
            NumberItem(R.drawable.enam, "6"),
            NumberItem(R.drawable.tujuh, "7"),
            NumberItem(R.drawable.delapan, "8"),
            NumberItem(R.drawable.sembilan, "9"),
            NumberItem(R.drawable.sepuluh, "10")
        )
    }
}
