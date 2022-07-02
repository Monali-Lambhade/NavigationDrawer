package com.example.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqllite.adapter.MyAdapter

import kotlinx.android.synthetic.main.activity_show_user2.*

class ShowUserActivity : AppCompatActivity() {

    lateinit var myAdapter: MyAdapter
    lateinit var layoutManager: LinearLayoutManager
    var context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_user2)

            supportActionBar?.setTitle("Onboarding App")
            supportActionBar?.setDisplayHomeAsUpEnabled(true);

            var db=DbHandler(this)
            var data=db.loadUsers()

            recView.setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this)
            recView.layoutManager=layoutManager

            myAdapter = MyAdapter(this,data)
            myAdapter.setList(data)
            recView.adapter = myAdapter



            /*details.text=" "
            for (i in 0 until (data.size-1)){
                //Toast.makeText(this,"${data[i].id} | ${data[i].name} | ${data[i].age} \n",Toast.LENGTH_LONG).show()
                details.append("${data[i].id} | ${data[i].name} | ${data[i].age} \n")
            }*/
        }

}