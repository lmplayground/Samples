package com.hamburger.room

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hamburger.room.data.AppDatabase
import com.hamburger.room.data.Emoji
import com.pantos27.boringlauncher.utils.AppExecutors
import com.pantos27.boringlauncher.utils.runOnIoThread

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    lateinit var recyclerView : RecyclerView
    var adapter: EmojiAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.main_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this,8)

            AppDatabase.getInstance(this).emojiDao().streamAllItems().observe(this, Observer {list->
                val emojiAdapter = EmojiAdapter(this.layoutInflater, list)
                if (adapter != null) {
                    recyclerView.swapAdapter(emojiAdapter,true)
                }else
                    recyclerView.adapter = emojiAdapter
                adapter = emojiAdapter
                recyclerView.post { adapter?.notifyDataSetChanged() }
            })


            fab.setOnClickListener {
                val editText = EditText(this)
                AlertDialog.Builder(this)
                    .setTitle("Search Emojis")
                    .setView(editText)
                    .setPositiveButton("Search") { dialog, which ->
                        val text = editText.text.toString()
                        runOnIoThread {
                            val list =
                                AppDatabase.getInstance(this).emojiDao().getItemsByNameSearch("%$text%")

                            val emojiAdapter = EmojiAdapter(this.layoutInflater, list)
                            adapter = emojiAdapter

                            recyclerView.post { recyclerView.swapAdapter(adapter,true) }
                        }
                    }.show()
            }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
