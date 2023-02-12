package com.example.guidomia.viewholders

import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guidomia.adapters.ExpandableRecyclerViewAdapter
import com.example.guidomia.R
import com.example.guidomia.models.Parent

class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val starLinearLayout: LinearLayout = itemView.findViewById(R.id.starLinearLayout)
    private var starImageView: ImageView = itemView.findViewById(R.id.imageView)
    private val modelTV : TextView = itemView.findViewById(R.id.title_textView)
    private val priceTV : TextView = itemView.findViewById(R.id.subtitle_textView)

        fun bind(parent: Parent) {
            starLinearLayout.removeAllViews()
            for (i in 0 until parent.rating - 1) {
                val newStarImageView = ImageView(starImageView.context)
                newStarImageView.setImageResource(R.drawable.ic_baseline_star_24)
                starLinearLayout.addView(newStarImageView)
            }
            modelTV.text = parent.model
            priceTV.text = parent.customerPrice.toString()
            //val drawableResourceId = itemView.context.resources.getIdentifier(parent.imgFile, "drawable", itemView.context.packageName)
            val inputStream = itemView.context.assets.open(parent.imgFile)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            starImageView.setImageBitmap(bitmap)
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    (itemView.parent as RecyclerView).adapter?.let { adapter ->
                        if (adapter is ExpandableRecyclerViewAdapter) {
                            val expandedParentPosition = adapter.expandedParentPosition
                            if (expandedParentPosition != RecyclerView.NO_POSITION) {
                                adapter.collapseParent(expandedParentPosition)
                            }
                            if (adapter.isExpanded(adapterPosition)) {
                                adapter.collapseParent(adapterPosition)
                            } else {
                                adapter.expandParent(adapterPosition)
                            }
                        }
                    }
                }
            }
        }
    }