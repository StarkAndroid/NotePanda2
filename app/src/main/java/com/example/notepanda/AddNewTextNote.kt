package com.example.notepanda


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.notepanda.DataPersistance.AppDatabase
import com.example.notepanda.DataPersistance.ListEntity
import com.example.notepanda.Utils.Utils
import kotlinx.android.synthetic.main.fragment_add_new_text_note.*
import org.jetbrains.anko.doAsync

class AddNewTextNote : Fragment() {

    lateinit var listDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var inflatedView = inflater.inflate(R.layout.fragment_add_new_text_note, container, false)
        // Inflate the layout for this fragment
        listDatabase = AppDatabase.getInstance(context!!)
        return inflatedView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newNoteFloatingActionButton.setOnClickListener {
            insertNoteToDatabase()
        }
    }

    private fun insertNoteToDatabase(){
        val noteTitle = titleET.text.toString()
        val noteDescription = descriptionET.text.toString()
        val noteDate = Utils.getCurrentDate()
        doAsync {
            val noteEntry = ListEntity(0, noteTitle, noteDescription, noteDate)
            listDatabase.listDao().insertList(noteEntry)
        }
        Utils.hideSoftKeyBoard(context, view)
        Navigation.findNavController(view!!).navigate(R.id.action_addNewTextNote_to_mainFragment)
    }
}
