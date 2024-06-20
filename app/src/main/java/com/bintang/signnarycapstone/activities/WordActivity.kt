package com.bintang.signnarycapstone.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bintang.signnarycapstone.R
import com.bintang.signnarycapstone.fragments.WordFragment

class WordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, WordFragment())
                .commit()
        }
    }
}
