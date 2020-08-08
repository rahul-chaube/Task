package com.rahulchaube.simpletaskkotline.ui.fragments

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.rahulchaube.simpletaskkotline.R
import com.rahulchaube.simpletaskkotline.data.entity.Book
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
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
        var imagesData=words.get(position).images
        if (imagesData.endsWith(",")) {
            imagesData = imagesData.substring(0, imagesData.length - 1);
        }

        val images=imagesData.split(",")

        val imgFile = File(images.get(0))

        Log.e("Image Fle **** "," path  "+imgFile.absolutePath)
        if (imgFile.exists()) {
            val myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath())
            holder.coverImage.setImageBitmap(myBitmap)

            holder.coverImage.setOnClickListener({
                openDialod(images)
            })
//
//            if (context != null) {
//                Glide.with(context).load(imgFile).into(holder.coverImage)
//            }
        }
    }

    private fun openDialod(images: List<String>) {
        val builder =
            AlertDialog.Builder(context!!)
        // Get the layout inflater
        // Get the layout inflater
        val view =
            LayoutInflater.from(context).inflate(R.layout.image_dialog, null, false)
        val sliderView: SliderView = view.findViewById(R.id.imageSlider)

        val adapter = SliderAdapterExample(context)
        adapter.addItem(images)
        sliderView.setSliderAdapter(adapter)
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4);

//        sliderView.startAutoCycle();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
        builder.setCancelable(true)
        builder.show()

    }
}