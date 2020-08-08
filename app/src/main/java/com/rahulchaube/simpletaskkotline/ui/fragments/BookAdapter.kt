package com.rahulchaube.simpletaskkotline.ui.fragments

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahulchaube.simpletaskkotline.R
import com.rahulchaube.simpletaskkotline.data.entity.Book
import java.io.File


class BookAdapter internal constructor(context:Context?):
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    val context:Context?=context;
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

        Log.e("Image Fle "," path  "+words.get(position).images)

        val images=words.get(position).images.split(",")

        val imgFile = File(images.get(0))

        Log.e("Image Fle **** "," path  "+imgFile.absolutePath)
        if (imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath())
            holder.coverImage.setImageBitmap(myBitmap)
//
//            if (context != null) {
//                Glide.with(context).load(imgFile).into(holder.coverImage)
//            }
        }
    }


}