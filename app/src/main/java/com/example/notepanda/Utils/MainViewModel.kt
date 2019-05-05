package com.example.notepanda.Utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notepanda.DataPersistance.AppDatabase
import com.example.notepanda.DataPersistance.ListEntity

class MainViewModel (application: Application) : AndroidViewModel(application){
    init {
        loadListsLiveData()
    }
    private lateinit var listDatabase: AppDatabase
    private lateinit var listsLiveData: LiveData<List<ListEntity>>

    private fun loadListsLiveData(){
        listDatabase = AppDatabase.getInstance(getApplication())
        listsLiveData = listDatabase.listDao().loadListsOrderedByCreationDate()
    }

    fun getListsLiveData():LiveData<List<ListEntity>>{
        return listsLiveData
    }

}