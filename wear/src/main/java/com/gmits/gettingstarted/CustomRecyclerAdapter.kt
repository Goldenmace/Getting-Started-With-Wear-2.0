package com.gmits.gettingstarted

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CustomRecyclerAdapter : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolderA>() {

    lateinit var onItemClick: OnItemClick

    private val drawables = intArrayOf(
            R.drawable.color_red,
            R.drawable.color_blue,
            R.drawable.color_grey,
            R.drawable.color_orange,
            R.drawable.color_neon,
            R.drawable.color_pink
    )

    private val colorStrings = arrayOf(
            "Red",
            "Blue",
            "Grey",
            "Orange",
            "Neon",
            "Pink"
    )

    private val mColors = arrayOf(
            "#FFFF3232",
            "#FF385CDE",
            "#b9b9b9",
            "#fc8e4e",
            "#51d6a7",
            "#d151d6"
    )

    fun setOnItemSelectListener(onItemClick: OnItemClick) {
        this.onItemClick = onItemClick
    }


    interface OnItemClick {
        fun onItemColorSelect(colorCode: String)
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolderA {
        return ViewHolderA(LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_wear_list, viewGroup, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolderA, position: Int) {

        viewHolder.ivView.setBackgroundResource(drawables[position])
        viewHolder.tvName.text = colorStrings[position]

        viewHolder.itemView.setOnClickListener({ onItemClick.onItemColorSelect(mColors[position]) })

    }

    override fun getItemCount(): Int {
        return drawables.size
    }

    inner class ViewHolderA(view: View) : RecyclerView.ViewHolder(view) {

        val ivView: View
        val tvName: TextView

        init {

            ivView = view.findViewById(R.id.ivImage)
            tvName = view.findViewById(R.id.tvName)
        }

    }

}