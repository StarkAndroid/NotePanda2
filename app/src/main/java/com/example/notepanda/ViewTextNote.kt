package com.example.notepanda


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.notepanda.DataPersistance.AppDatabase
import kotlinx.android.synthetic.main.fragment_view_text_note.*
import org.jetbrains.anko.doAsync


class ViewTextNote : Fragment() {

    lateinit var listDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listDatabase = AppDatabase.getInstance(context!!)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_text_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        doAsync {
            val args: ViewTextNoteArgs by navArgs()
            var ID = args.NoteID
            val entity = listDatabase.listDao().queryByID(ID)
            titleTV.text = entity.listName
            descriptionTV.text = entity.listDescription
        }
    }
}
