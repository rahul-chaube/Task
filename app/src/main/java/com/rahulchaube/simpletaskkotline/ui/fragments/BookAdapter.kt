package com.rahulchaube.simpletaskkotline.ui.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.rahulchaube.simpletaskkotline.R
import com.rahulchaube.simpletaskkotline.data.entity.Book

class BookAdapter internal constructor(context:Context?):
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Book>()
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val textViewBookName:TextView=itemView.findViewById(R.id.bookName)
        val textViewAutherName:TextView=itemView.findViewById(R.id.autherName)
        val textViewPrice:TextView=itemView.findViewById(R.id.price)
        val textViewDoi:TextView=itemView.findViewById(R.id.doi)
        val coverImage:ImageView=itemView.findViewById(R.id.image)
    }
    internal fun setData(data:List<Book>)
    {
        words=data;
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val itemView=inflater.inflate(R.layout.row_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
     return words.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewBookName.text=words.get(position).bookName
        holder.textViewAutherName.text=words.get(position).authorName
        holder.textViewDoi.text=words.get(position).doi
        holder.textViewPrice.text=words.get(position).price
    }


}