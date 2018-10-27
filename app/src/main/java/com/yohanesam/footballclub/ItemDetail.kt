package com.yohanesam.footballclub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.TextureView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jetbrains.anko.*

class ItemDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SecondActivityUI().setContentView(this)

        val img = intent.getIntExtra("image", 0)
        val name = intent.getStringExtra("name")
        val detail = intent.getStringExtra("detail")

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

    class SecondActivityUI : AnkoComponent<ItemDetail> {
        override fun createView(ui: AnkoContext<ItemDetail>): View = with(ui) {
            return relativeLayout {
                padding = dip(20)

                imageView {
                    id = R.id.club_image_detail
                }.lparams(width = dip(100), height = dip(100)) {
                    centerHorizontally()
                }

                textView {
                    textSize = sp(10).toFloat()
                    id = R.id.club_image_detail
                }.lparams(width = wrapContent, height = wrapContent) {
                    centerHorizontally()
                    below(R.id.club_image_detail)
                }

                textView {
                    textSize = sp(10).toFloat()
                    id = R.id.club_exp_detail
                }.lparams(width = matchParent, height = wrapContent) {
                    centerHorizontally()
                    below(R.id.club_name_detail)
                }
            }
        }

    }
}