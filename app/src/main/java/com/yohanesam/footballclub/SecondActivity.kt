package com.yohanesam.footballclub

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        SecondActivityUI().setContentView(this)

        val img = intent.getIntExtra("image", 0)
        val name = intent.getStringExtra("name")
        val detail = intent.getStringExtra("detail")

        Log.d("TAG","img = ${img}, name = ${name}, detail = ${detail}")

        init(img, name, detail)
    }

    private fun init(img : Int, name : String, detail : String) {
        val nameDetail = find<TextView>(R.id.club_name_detail)
        val imageDetail = find<ImageView>(R.id.club_image_detail)
        val explaDetail = find<TextView>(R.id.club_exp_detail)

        nameDetail.text = name
        Glide.with(this).load(img).into(imageDetail)
        explaDetail.text = detail
    }

    class SecondActivityUI : AnkoComponent<SecondActivity> {
        override fun createView(ui: AnkoContext<SecondActivity>): View = with(ui) {
            return relativeLayout {
                padding = dip(20)

                imageView {
                    id = R.id.club_image_detail
                }.lparams(width = dip(100), height = dip(100)) {
                    centerHorizontally()
                }

                textView {
                    textSize = sp(10).toFloat()
                    id = R.id.club_name_detail
                    textColor = Color.BLACK
                }.lparams(width = wrapContent, height = wrapContent) {
                    centerHorizontally()
                    setMargins(0, dip(12), 0, 0)
                    below(R.id.club_image_detail)
                }

                textView {
                    textSize = sp(8).toFloat()
                    id = R.id.club_exp_detail
                    textColor = Color.BLACK
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    centerHorizontally()
                    setMargins(0, dip(20), 0, 0)
                    below(R.id.club_name_detail)
                }
            }
        }

    }
}
