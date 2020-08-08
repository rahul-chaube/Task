package com.rahulchaube.simpletaskkotline.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.rahulchaube.simpletaskkotline.R
import com.rahulchaube.simpletaskkotline.ui.fragments.CreateBookFragment
import com.rahulchaube.simpletaskkotline.ui.fragments.HomeFragments


class MainActivity : AppCompatActivity() {


    lateinit var  fragment: Fragment
    lateinit var manager: FragmentManager
    lateinit var ft: FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = getSupportFragmentManager()
        fragment = HomeFragments()
        ft = manager.beginTransaction()
        ft.add(R.id.container, fragment).commit()
    }

    fun addbook(view: View) {
        fragment = CreateBookFragment()
        ft = manager.beginTransaction()
        ft.replace(R.id.container, fragment).addToBackStack("test").commit()
    }
}