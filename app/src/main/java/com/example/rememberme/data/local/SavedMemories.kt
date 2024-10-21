package com.example.rememberme.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memories")
data class SavedMemories(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "memory_id")
    var id: Int = 0,

    @ColumnInfo(name = "saved_memory_name")
    var savedMemories: String,
)