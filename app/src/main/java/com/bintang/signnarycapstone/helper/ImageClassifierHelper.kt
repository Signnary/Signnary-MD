package com.bintang.signnarycapstone.helper

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.bintang.signnarycapstone.models.PredictionResponse
import com.bintang.signnarycapstone.networking.RetrofitClient
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ImageClassifierHelper(
    private val context: Context,
    private val classifierListener: ClassifierListener?
) {
    private val apiService = RetrofitClient.predictservice

    fun classifyImage(imageUri: Uri) {
        val filePath = getPathFromUri(context, imageUri)
        if (filePath != null) {
            val file = File(filePath)
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
            sendImageToServer(body)
        } else {
            classifierListener?.error("File path is null")
        }
    }

    private fun sendImageToServer(body: MultipartBody.Part) {
        apiService.predictImage(body).enqueue(object : Callback<PredictionResponse> {
            override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                classifierListener?.error("Failed to connect to the server")
                Log.e("ImageClassifierHelper", "Error: ${t.message}")
            }

            override fun onResponse(call: Call<PredictionResponse>, response: Response<PredictionResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d("ImageClassifierHelper", "Response: $responseBody")
                    classifierListener?.result(responseBody?.predicted_letter, responseBody?.confidence)
                } else {
                    classifierListener?.error("Failed to get a response from the server")
                    Log.e("ImageClassifierHelper", "Server returned error: ${response.code()}")
                }
            }
        })
    }

    interface ClassifierListener {
        fun error(error: String)
        fun result(predictedLetter: String?, confidence: Float?)
    }
}

fun getPathFromUri(context: Context, uri: Uri): String? {
    var path: String? = null
    val projection = arrayOf(MediaStore.Images.Media.DATA)
    val cursor = context.contentResolver.query(uri, projection, null, null, null)
    cursor?.use {
        if (it.moveToFirst()) {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            path = it.getString(columnIndex)
        }
    }
    return path
}
