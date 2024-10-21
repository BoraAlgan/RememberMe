package com.example.rememberme.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SavedMemories::class], version = 1)
    abstract class MemoriesDatabase : RoomDatabase() {
        abstract fun savedMemoriesDao(): MemoriesDAO
    }