package com.superdo.magina.recyclerviewmanagers

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle("列表")

        btn1.setOnClickListener({
            startActivity(Intent(this, DrawActivity::class.java))
        })

        btn2.setOnClickListener({
            startActivity(Intent(this, DrawActivity2::class.java))
        })

        btn3.setOnClickListener({
            startActivity(Intent(this, CardActivity::class.java))
        })


    }
}
