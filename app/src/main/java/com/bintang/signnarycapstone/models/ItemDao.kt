package com.bintang.signnarycapstone.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bintang.signnarycapstone.models.Item

@Dao
interface ItemDao {
    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): List<Item>

    @Insert
    fun insertFavorite(item: Item)
}