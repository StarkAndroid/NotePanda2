package com.example.notepanda.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notepanda.DataPersistance.ListEntity
import com.example.notepanda.R
import com.example.notepanda.Utils.Utils
import kotlinx.android.synthetic.main.single_list_item.view.*

class ListAdapter(val context: Context, val clickListener: (Int)-> Unit) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    var listsEntries : List<ListEntity>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_list_item, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listsEntries?.size ?: return 0
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val listEntity = listsEntries?.get(position)
        val listName = listEntity?.listName
        val listId = listEntity?.listID
        val listCreationDate = Utils.ConvertTimestampToDate(listEntity?.listCreationDate)
        holder.currentListTextView.text = listName
        holder.currentListDateCreationTextView.text = Utils.convertDateToNormalDate(listCreationDate)
        holder.bind(listId!!, clickListener)
    }

    fun loadLists(ListsEntries: List<ListEntity>){
        this.listsEntries = ListsEntries
        notifyDataSetChanged()
    }

    class ListViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val currentListTextView: TextView = itemView.single_list_name_TV
        val currentListDateCreationTextView: TextView = itemView.single_list_creation_date_TV
        fun bind(itemID:Int, clickListener: (Int) -> Unit) {
            itemView.setOnClickListener { clickListener(itemID) }
        }
    }
}