package com.example.guidomia

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(child: Child) {
        val pTextView : TextView = itemView.findViewById(R.id.title_text_view)
        pTextView.text = child.title
    }
}