package com.bintang.signnarycapstone.activities

import NumberFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bintang.signnarycapstone.R

class NumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NumberFragment())
                .commit()
        }
    }
}
