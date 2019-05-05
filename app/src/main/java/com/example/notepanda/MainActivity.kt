package com.example.notepanda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notepanda.Adapter.ListAdapter
import com.example.notepanda.DataPersistance.AppDatabase
import com.example.notepanda.DataPersistance.ListEntity
import com.example.notepanda.Utils.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync


class MainActivity : AppCompatActivity() {
    lateinit var listDatabase:AppDatabase
    lateinit var listAdapter: ListAdapter
    val LOGGING_MASSAGE = "Logging Massage"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
