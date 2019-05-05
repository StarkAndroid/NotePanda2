package com.example.notepanda


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notepanda.Adapter.ListAdapter
import com.example.notepanda.DataPersistance.AppDatabase
import com.example.notepanda.DataPersistance.ListEntity
import com.example.notepanda.Utils.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_fragment.*
import org.jetbrains.anko.doAsync

class MainFragment : Fragment() {

    lateinit var listDatabase: AppDatabase
    lateinit var listAdapter: ListAdapter
    val LOGGING_MASSAGE = "Logging Massage"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflatedView = inflater.inflate(R.layout.main_fragment, container, false)
        listDatabase = AppDatabase.getInstance(context!!)
        return inflatedView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpRecyclerViewAndAdapter()
        setRecyclerViewItemTouchListener(MainFragmentRecyclerView)
        setUpListLiveDataAndViewModel()
        setUpFloatingActionButton()
    }

    private fun setUpRecyclerViewAndAdapter() {
        MainFragmentRecyclerView.layoutManager = LinearLayoutManager(context!!)
        listAdapter = ListAdapter(context!!, {noteNumber -> showCurrentNote(noteNumber)})
        MainFragmentRecyclerView.adapter = listAdapter
    }

    private fun showCurrentNote(noteNumber : Int) {
        val action = MainFragmentDirections.actionMainFragmentToViewTextNote(noteNumber)
        Navigation.findNavController(view!!).navigate(action)
    }

    private fun setUpFloatingActionButton() {
        MainFragmentFloatingActionButton.setOnClickListener {v: View ->
            Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_addNewTextNote)
        }
    }

    private fun setRecyclerViewItemTouchListener(recyclerView: RecyclerView) {

        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, viewHolder1: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                doAsync {
                    val position = viewHolder.adapterPosition
                    val currentEntity = listAdapter.listsEntries?.get(position)
                    listDatabase.listDao().deleteList(currentEntity!!)
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun setUpListLiveDataAndViewModel() {
        val mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val listObserver = Observer<List<ListEntity>>{ reloadedLists ->
            listAdapter.loadLists(reloadedLists)
        }
        mainViewModel.getListsLiveData().observe(this, listObserver)
    }
}
