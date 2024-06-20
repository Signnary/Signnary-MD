package com.bintang.signnarycapstone.activities

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bintang.signnarycapstone.R
import com.bintang.signnarycapstone.helper.ImageClassifierHelper

class ResultActivity : AppCompatActivity(), ImageClassifierHelper.ClassifierListener {
    private lateinit var resultImage: ImageView
    private lateinit var resultText: TextView
    private lateinit var resultLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Inisialisasi ImageView dan TextView
        resultImage = findViewById(R.id.result_image)
        resultText = findViewById(R.id.result_text)
        resultLabel = findViewById(R.id.result_label)

        // Mendapatkan URI gambar dari intent
        val imageUri: Uri? = intent.getParcelableExtra(EXTRA_IMAGE_URI)

        // Jika URI gambar tidak null, set gambar dan lakukan klasifikasi
        imageUri?.let {
            resultImage.setImageURI(it)
            Log.d("ResultActivity", "Image URI: $it")
            classifyImage(it) // Panggil fungsi untuk melakukan klasifikasi gambar
        }

        // Mendapatkan teks hasil dari intent dan menampilkannya
        val result = intent.getStringExtra("result_text")
        resultText.text = result
    }

    // Fungsi untuk melakukan klasifikasi gambar
    private fun classifyImage(uri: Uri) {
        val classifierHelper = ImageClassifierHelper(this, this)
        classifierHelper.classifyImage(uri)
    }

    override fun error(error: String) {
        Log.d("ResultActivity", "Error: $error")
        runOnUiThread {
            resultLabel.text = "Error: $error"
        }
    }

    // Implementasi metode result dari ClassifierListener
    override fun result(predictedLetter: String?, confidence: Float?) {
        Log.d("ResultActivity", "Predicted letter: $predictedLetter, Confidence: $confidence")
        val displayText = "Predicted letter: $predictedLetter\nConfidence: $confidence"
        runOnUiThread {
            resultLabel.text = displayText
        }
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
    }
}
