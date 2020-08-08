package com.rahulchaube.simpletaskkotline.ui.fragments

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.graphics.Color
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.esafirm.imagepicker.features.ImagePicker
import com.esafirm.imagepicker.features.ReturnMode
import com.rahulchaube.simpletaskkotline.R
import com.rahulchaube.simpletaskkotline.data.entity.Book
import com.rahulchaube.simpletaskkotline.ui.BookViewModel
import com.weiwangcn.betterspinner.library.BetterSpinner
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CreateBookFragment : Fragment() {

    lateinit var betterSpinner: BetterSpinner
    lateinit var editTextBookName: EditText
    lateinit var editTextPrice: EditText
    lateinit var editTextDOI: EditText
    lateinit var submitButton: Button
    lateinit var button:Button
     var authorName: String=""
    lateinit var bookViewModel: BookViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_book_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        betterSpinner = view.findViewById(R.id.spinnerAuthor)
        editTextBookName = view.findViewById(R.id.etName)
        editTextPrice = view.findViewById(R.id.etPrice)
        submitButton = view.findViewById(R.id.submit)
        submitButton.setOnClickListener {
            submitCalled();
        }
        bookViewModel= ViewModelProvider(this).get(BookViewModel::class.java)

        var list = ArrayList<String>()
        list.add("Premchandra")
        list.add("Rahul")
        list.add("Chetan Bhagat")

        val adapter = ArrayAdapter.createFromResource(
            context!!,
            R.array.author, android.R.layout.simple_spinner_item
        )
        betterSpinner.setAdapter(adapter);
        betterSpinner.addTextChangedListener(textWatcher)
        editTextDOI=view.findViewById(R.id.date)
        button=view.findViewById(R.id.coverPhoto)
        button.setOnClickListener({
            openGalarry();
        })
        var myCalendar = Calendar.getInstance();

        val date =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth -> // TODO Auto-generated method stub
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = monthOfYear
                myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                val myFormat = "MM/dd/yy" //In which you need put here

                val sdf = SimpleDateFormat(myFormat, Locale.US)

                editTextDOI.setText(sdf.format(myCalendar.time))
            }
        editTextDOI.setOnClickListener { // TODO Auto-generated method stub
            DatePickerDialog(
                activity!!, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()
        }
    }
var imageList:String="";
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
            val images = ImagePicker.getImages(data)
            Log.e("Total Images "," "+images.size)
            imageList="";
            for (image in images) {
                imageList+=image.path+","
                    Log.e("Image Path "," "+image.path)
            }

            Log.e("Image List "," Images "+imageList)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun openGalarry() {
        ImagePicker.create(this)
            .folderMode(true) // folder mode (false by default)
            .toolbarFolderTitle("Folder") // folder selection title
            .toolbarImageTitle("Tap to select") // image selection title
            .toolbarArrowColor(Color.BLACK) // Toolbar 'up' arrow color
            .includeVideo(false) // Show video on image picker
            .multi() // multi mode (default mode)
            .limit(3) // max images can be selected (99 by default)
            .showCamera(true) // show camera or not (true by default)
            .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
            .start(); // start image picker activity with request code
    }



    private fun submitCalled() {
        if (editTextBookName.text.toString().isEmpty()) {
            Toast.makeText(context, "Enter Book Name ", Toast.LENGTH_SHORT).show()
        } else if (editTextPrice.text.toString().isEmpty()) {
            Toast.makeText(context, "Enter Price ", Toast.LENGTH_SHORT).show()
        }
        else if (authorName.isEmpty())
        {
            Toast.makeText(context,"Select Author",Toast.LENGTH_SHORT).show()
        }
        else if (editTextDOI.text.isEmpty())
        {
            Toast.makeText(context,"Select DOI",Toast.LENGTH_SHORT).show()

        }
        else
        {
            insertData();
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun insertData() {
        Log.e("Images List ", " "+imageList)
        var book=Book(null,editTextBookName.text.toString(),authorName,editTextPrice.text.toString(),
            editTextDOI.text.toString(),imageList)
        bookViewModel.insert(book)
        activity!!.onBackPressed()
    }


    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            authorName = s.toString()
        }
    }
}