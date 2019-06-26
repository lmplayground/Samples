package com.hamburger.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hamburger.room.data.Emoji

class EmojiAdapter(val inflater: LayoutInflater): ListAdapter<Emoji,EmojiAdapter.EmojiHolder>(DIFFUTIL_CALLBACK) {

    companion object{
        val DIFFUTIL_CALLBACK = object : DiffUtil.ItemCallback<Emoji>() {
            override fun areItemsTheSame(oldItem: Emoji, newItem: Emoji): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Emoji, newItem: Emoji): Boolean {
                return oldItem.emoji==newItem.emoji
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiHolder {
        inflater.inflate(android.R.layout.simple_gallery_item,parent,false).run {
            return EmojiHolder(this)
        }

    }


    override fun onBindViewHolder(holder: EmojiHolder, position: Int) {
        holder.setIEmoji(getItem(position))
    }

    class EmojiHolder(view: View):RecyclerView.ViewHolder(view){
        fun setIEmoji(emoji: Emoji){
            (this.itemView as TextView).text = emoji.emoji?.trim()
        }
    }
}

fun Int.toEmojiString() = String(Character.toChars(this))