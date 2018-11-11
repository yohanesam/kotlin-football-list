package com.yohanesam.footballclub

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {

    private var items : MutableList<FootballList> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        recycle_layout.layoutManager = LinearLayoutManager(this)
        recycle_layout.adapter = RecycleViewAdapter(this, items) {
            Log.d("TAG","img = ${it.image}, name = ${it.name}, detail = ${it.detail}")
            startActivity<SecondActivity>(
                "name" to it.name, "image" to it.image, "detail" to it.detail
            )
        }
    }

    @SuppressLint("ResourceType")
    private fun init () {
        val club_name = resources.getStringArray(R.array.club_name)
        val club_image =  resources.obtainTypedArray(R.array.club_image)
        val detail = resources.getStringArray(R.array.club_detail)

        items.clear()

        for (i in club_name.indices) {
            items.add(
                FootballList(club_name[i], club_image.getResourceId(i, 0), detail[i])
            )
        }

        club_image.recycle()
    }
}
