package com.example.navigationdrawer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_delete.*
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_update.*
import java.lang.NumberFormatException

class deleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)


        supportActionBar?.setTitle("Onboarding App")
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        val db=DbHandler(this)
        btn_delete2.setOnClickListener {
            db.deleteUsers(edit_email2.text.toString())
            val intent= Intent(this,ShowUserActivity::class.java)
            startActivity(intent)
        }
    }
    }
