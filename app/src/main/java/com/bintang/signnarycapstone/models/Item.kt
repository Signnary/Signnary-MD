package com.bintang.signnarycapstone.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String,
    val text: String
)