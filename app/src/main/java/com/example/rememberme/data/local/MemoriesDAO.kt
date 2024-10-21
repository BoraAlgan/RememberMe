package com.example.rememberme.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MemoriesDAO {
    @Insert
    suspend fun insertMemory(memory: SavedMemories)

    @Delete
    suspend fun deleteMemory(memory: SavedMemories)

    @Query("SELECT * FROM memories")
    suspend fun getAllMemories(): List<SavedMemories>

}