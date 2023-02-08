package com.example.guidomia

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(parent: Parent) {
            val pTextView : TextView = itemView.findViewById(R.id.title_text_view)
            pTextView.text = parent.title
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