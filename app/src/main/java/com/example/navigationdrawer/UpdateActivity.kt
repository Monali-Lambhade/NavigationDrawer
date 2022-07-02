package com.example.navigationdrawer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_update.*
import java.lang.NumberFormatException

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

            supportActionBar?.setTitle("Onboarding App")
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val db=DbHandler(this)
        btn_update1.setOnClickListener {
            try {
                val newUser=User(1,
                    edit_email1.text.toString(),
                    edit_name1.text.toString(),
                    Integer.parseInt(edit_age1.text.toString()),
                    edit_phno1.text.toString().toLong(10)
                )
                db.updateUsers(newUser)
                val intent= Intent(this,ShowUserActivity::class.java)
                startActivity(intent)
            }catch (e: NumberFormatException){
                //Toast.makeText(this,"Enter some value in Email,Name,Age,Phone No", Toast.LENGTH_LONG).show()
            }

        }

    }
}