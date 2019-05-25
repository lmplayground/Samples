package com.hamburger.room

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
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

        fab.setOnClickListener {
            val editText = EditText(this)
//            editText.inputType = InputType.TYPE_CLASS_TEXT
            AlertDialog.Builder(this)
                .setTitle("Search Emoji")
                .setView(editText)
                .setNegativeButton("Cancel") { _, _ ->}
                .setPositiveButton("Search") { _, _ ->
                    val input = editText.text.toString()
                    runOnIoThread {
                        val items = AppDatabase.getInstance(this).emojiDao().getItemsByNameSearch("%$input%")
                        Log.d(TAG, "search results $items")
                        adapter = EmojiAdapter(this.layoutInflater, items)
                        recyclerView.post { recyclerView.swapAdapter(adapter, true) }
                    }
                }
                .show()
        }

        recyclerView = findViewById(R.id.main_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this,8)
//        recyclerView.layoutManager = StaggeredGridLayoutManager(8,RecyclerView.VERTICAL)

        AppDatabase.getInstance(this).emojiDao().streamAllItems().observe(this, Observer {
            Log.d(TAG,"on items changed ${it.size}")
            if (adapter != null) {
                   adapter?.data = it
                adapter?.notifyDataSetChanged()
            }else {
                adapter = EmojiAdapter(this.layoutInflater, it)
                recyclerView.swapAdapter(adapter, true)
            }
        })
//        AppExecutors.networkIO.execute {
//            val allItems = AppDatabase.getInstance(this).emojiDao().getAllItems()
//            Log.d(TAG,"all items are ${allItems.size}")
//            val adapter = EmojiAdapter(this.layoutInflater,allItems)
//            recyclerView.post { recyclerView.adapter = adapter }
//        }



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
