package com.example.navigationdrawer

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.util.ArrayList

val DATABASE_NAME="MyDb2"
val TABLE_NAME="User"
val COL_ID="id"
val COL_EMAIL="email"
val COL_NAME="name"
val COL_AGE="age"
val COL_PH="ph_no"

class DbHandler (context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null,6) {
    val context = context
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "create table $TABLE_NAME($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COL_EMAIL VARCHAR(100),$COL_NAME VARCHAR(25),$COL_AGE INTEGER,$COL_PH LONG)"
        db?.execSQL(createTable)
    }

    fun addUser(user: User) {
        val db = this.writableDatabase
        var value = ContentValues()


        value.put(COL_EMAIL, user.email)
        value.put(COL_NAME, user.name)
        value.put(COL_AGE, user.age)
        value.put(COL_PH, user.ph_no)


        var result = db.insert(TABLE_NAME, null, value)
        if (result == (-1).toLong()) {
            Toast.makeText(context, "Insert Failed", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Record Inserted", Toast.LENGTH_LONG).show()
        }

    }

    @SuppressLint("Range")
    fun loadUsers(): MutableList<User> {
        var list: MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "select * from $TABLE_NAME"
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var user = User(
                    id = result.getString(result.getColumnIndex(COL_ID)).toInt(),
                    email = result.getString(result.getColumnIndex(COL_EMAIL)),
                    name = result.getString(result.getColumnIndex(COL_NAME)),
                    age = result.getString(result.getColumnIndex(COL_AGE)).toInt(),
                    ph_no = result.getString(result.getColumnIndex(COL_PH)).toLong()
                )
                list.add(user)

            } while (result.moveToNext())
        }

        return list
    }

    @SuppressLint("Range")
    fun updateUsers(user: User) {
        var db = this.writableDatabase
        var query = "select * from $TABLE_NAME where email='${user.email}'"
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            var value = ContentValues()
            value.put(COL_AGE, user.age)
            value.put(COL_NAME, user.name)
            value.put(COL_PH, user.ph_no)
            var res = db.update(
                TABLE_NAME, value, "$COL_EMAIL=?",
                arrayOf(result.getString(result.getColumnIndex(COL_EMAIL)))
            )
            Toast.makeText(context, "Record updated", Toast.LENGTH_LONG).show()
        }
        db.close()
        result.close()
    }

    @SuppressLint("Range")
    fun deleteUsers(email: String) {
        var db = this.writableDatabase
        var query = "delete from $TABLE_NAME where email='$email'"
        var result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            var value = ContentValues()
            value.remove(COL_EMAIL)
            var res = db.delete(
                TABLE_NAME, "$COL_EMAIL=?",
                arrayOf((result.getString(result.getColumnIndex(COL_EMAIL))))
            )
            Toast.makeText(context, "Record Deleted", Toast.LENGTH_LONG).show()
        }
//        else {
//            Toast.makeText(context, "Delete Failed.. Record Not Found", Toast.LENGTH_LONG).show()
//        }
        db.close()
        result.close()
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}