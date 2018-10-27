package com.yohanesam.footballclub

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class RecycleViewAdapter(private val context: Context, private val items: List<FootballList>, private val listener: (FootballList) -> Unit) :
        RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(UI().createView(AnkoContext.create(parent.context)))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], context, listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        val name = containerView.find<TextView>(R.id.club_name)
        val image = containerView.find<ImageView>(R.id.club_image)

        fun bindItem(items: FootballList, context: Context, listener: (FootballList) -> Unit) {
            name.text = items.name
            items.image?.let { Glide.with(context).load(it).into(image) }
            containerView.setOnClickListener{listener(items)}
        }
    }

}

class UI : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {

        return cardView {
            layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT).apply {
                leftMargin = dip(10)
                topMargin = dip(10)
                bottomMargin = dip(10)
                rightMargin = dip(10)
            }

            radius = dip(14).toFloat()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                elevation = dip(4).toFloat()
            }

            relativeLayout {
                padding = dip(16)

                imageView {
                    id = R.id.club_image
                }.lparams(width = dip(100), height = dip(100)) {
                    setMargins(0, 0, dip(16), 0)
                }

                textView {
                    textSize = sp(9).toFloat()
                    textColor = Color.BLACK
                    id = R.id.club_name
                }.lparams(width = wrapContent, height = wrapContent) {
                    centerVertically()
                    setMargins(dip(12), 0, 0, 0)
                    rightOf(R.id.club_image)
                }
            }
        }
    }

}