package com.hamburger.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emoji_items")
data class Emoji(val emoji: String? = null,
                 val hexcode: String? = null,
                 val order: Int? = null,
                 val name: String? = null){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}