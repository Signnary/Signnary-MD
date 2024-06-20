package com.bintang.signnarycapstone.models

data class PredictionResponse(
    val predicted_letter: String,
    val confidence: Float
)
