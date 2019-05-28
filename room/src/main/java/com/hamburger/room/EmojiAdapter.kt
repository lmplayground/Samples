package com.hamburger.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hamburger.room.data.Emoji

class EmojiAdapter(val inflater: LayoutInflater,var data: List<Emoji>): RecyclerView.Adapter<EmojiAdapter.EmojiHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiHolder {
        inflater.inflate(android.R.layout.simple_gallery_item,parent,false).run {
            return EmojiHolder(this)
        }

    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: EmojiHolder, position: Int) {
        holder.setIEmoji(data[position])
    }

    class EmojiHolder(view: View):RecyclerView.ViewHolder(view){
        fun setIEmoji(emoji: Emoji){
            (this.itemView as TextView).text = emoji.emoji?.trim()
        }
    }
}

fun Int.toEmojiString() = String(Character.toChars(this))