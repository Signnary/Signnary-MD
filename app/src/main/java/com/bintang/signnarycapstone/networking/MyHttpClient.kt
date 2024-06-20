package com.bintang.signnarycapstone.networking

import android.content.Context
import okhttp3.OkHttpClient

class MyHttpClient(private val context: Context) {

    fun createClient(): OkHttpClient {
        return OkHttpClient()
    }
}
