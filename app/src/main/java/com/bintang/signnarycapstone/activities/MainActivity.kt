package com.bintang.signnarycapstone.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bintang.signnarycapstone.R
import com.bintang.signnarycapstone.adapters.SearchAdapter
import com.bintang.signnarycapstone.data.AppDatabase
import com.bintang.signnarycapstone.adapters.Item as AdapterItem
import com.bintang.signnarycapstone.models.Item as ModelItem
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var itemList: List<AdapterItem>
    private lateinit var db: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}

        setSupportActionBar(findViewById(R.id.toolbar))

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

        itemList = listOf(
            AdapterItem(type = SearchAdapter.TYPE_ABJAD, text = "A"),
            AdapterItem(type = SearchAdapter.TYPE_NUMBER, text = "1"),
            AdapterItem(type = SearchAdapter.TYPE_WORD, text = "Kucing"),
            AdapterItem(type = SearchAdapter.TYPE_SENTENCE, text = "Saya suka makan")
        )

        searchAdapter = SearchAdapter(itemList).apply {
            setOnItemLongClickListener { item ->
                val model = ModelItem(type = item.type.toString(), text = item.text)
                addFavorite(model)
                Toast.makeText(this@MainActivity, "${item.text} added to favorites", Toast.LENGTH_SHORT).show()
            }
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = searchAdapter
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            var selectedActivity: Class<out Activity>? = null
            when (item.itemId) {
                R.id.dictionary -> selectedActivity = DictionaryActivity::class.java
                R.id.fab -> selectedActivity = CameraActivity::class.java
                R.id.gallery -> selectedActivity = GalleryActivity::class.java

            }
            selectedActivity?.let {
                startActivity(Intent(this, it))
            }
            true
        }
    }

    private fun addFavorite(item: ModelItem) {
        db.itemDao().insertFavorite(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = itemList.filter {
                    it.text.contains(newText ?: "", ignoreCase = true)
                }
                searchAdapter.updateList(filteredList)
                return true
            }
        })
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CameraActivity.REQUEST_CODE_PERMISSIONS) {
            if (CameraActivity.allPermissionsGranted(this)) {
                startActivity(Intent(this, CameraActivity::class.java) )

            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
