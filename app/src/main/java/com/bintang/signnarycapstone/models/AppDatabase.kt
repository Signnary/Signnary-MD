package com.bintang.signnarycapstone.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bintang.signnarycapstone.models.Item

@Database(entities = [Item::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao
}