package com.example.navigationdrawer

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.regex.Pattern
import android.content.Intent as Intent1

class MainActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        supportActionBar?.setTitle("Onboarding App")
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val db = DbHandler(this)
        btn_add.setOnClickListener {
            try {
                val newUser = User(
                    1,
                    edit_email.text.toString(),
                    edit_name.text.toString(),
                    Integer.parseInt(edit_age.text.toString()),
                    edit_phno.text.toString().toLong(10)
                )


                db.addUser(newUser)
                val intent = Intent1(this, ShowUserActivity::class.java)
                startActivity(intent)
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Enter some value in Email,Name,Age,Phone No", Toast.LENGTH_LONG).show()
            }
        }
    }

}


