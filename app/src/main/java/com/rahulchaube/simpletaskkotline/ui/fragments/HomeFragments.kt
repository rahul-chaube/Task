package com.rahulchaube.simpletaskkotline.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahulchaube.simpletaskkotline.R
import com.rahulchaube.simpletaskkotline.data.entity.Book
import com.rahulchaube.simpletaskkotline.ui.BookViewModel

class HomeFragments : Fragment() {

    lateinit var recyclerView:RecyclerView
    lateinit var bookAdapter: BookAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var bookViewModel: BookViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_fragments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.recyclerview_tasks)
        linearLayoutManager=LinearLayoutManager(context)
        val  textViewEmpty:TextView=view.findViewById(R.id.empty)
        recyclerView.layoutManager=linearLayoutManager
        bookAdapter=BookAdapter(context)
        recyclerView.adapter=bookAdapter
        bookViewModel=ViewModelProvider(this).get(BookViewModel::class.java)
        bookViewModel.allWords.observe(viewLifecycleOwner, Observer {books->
            run {
                Log.e("Data is Called ", "onViewCreated: ***************************  ")
                bookAdapter.setData(books)
                if (books.size==0)
                textViewEmpty.visibility=View.VISIBLE
                else
                    textViewEmpty.visibility=View.GONE

            }

        })
    }

}