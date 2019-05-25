package com.hamburger.room.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmojiDao {
    @Query("SELECT * FROM emoji_items")
    fun getAllItems() : List<Emoji>

    @Query("SELECT * FROM emoji_items")
    fun streamAllItems() : LiveData<List<Emoji>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Emoji)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: List<Emoji>)

    @Query("SELECT * FROM emoji_items WHERE name LIKE :search")
    fun getItemsByNameSearch(search: String) : List<Emoji>

}