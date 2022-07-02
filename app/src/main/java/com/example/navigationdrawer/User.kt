package com.example.navigationdrawer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var email:String,
    var name:String,
    var age:Int,
    var ph_no:Long



)


