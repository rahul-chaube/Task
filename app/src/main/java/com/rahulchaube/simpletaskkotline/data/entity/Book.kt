package com.rahulchaube.simpletaskkotline.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Book(@PrimaryKey(autoGenerate = true) val id:Int,val bookName:String, val authorName:String,val price:String,val doi:String,val images:String) {
}