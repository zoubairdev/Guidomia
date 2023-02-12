package com.example.guidomia.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guidomia.R
import com.example.guidomia.models.Child
import com.example.guidomia.models.Parent
import com.example.guidomia.viewholders.ChildViewHolder
import com.example.guidomia.viewholders.ParentViewHolder

class ExpandableRecyclerViewAdapter(private val parentList: List<Parent>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_PARENT = 0
    private val VIEW_TYPE_CHILD = 1

    private val expandedParentIndexList = mutableListOf<Int>()
    var expandedParentPosition = RecyclerView.NO_POSITION


    override fun getItemViewType(position: Int): Int {
        return if (isParent(position)) VIEW_TYPE_PARENT else VIEW_TYPE_CHILD
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_PARENT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.parent_item, parent, false)
            ParentViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.child_item, parent, false)
            ChildViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        var count = 0
        for (i in parentList.indices) {
            count++
            if (isExpanded(i)) {
                count += parentList[i].children.size
            }
        }
        return count
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isParent(position)) {
            (holder as ParentViewHolder).bind(getParentAt(position))
        } else {
            (holder as ChildViewHolder).bind(getChildAt(position))
        }
    }

    fun isExpanded(position: Int): Boolean {
        return expandedParentIndexList.contains(position)
    }

    private fun isParent(position: Int): Boolean {
        var count = 0
        for (i in parentList.indices) {

            if (count == position) {
                return true
            }
            count++
            if (isExpanded(count - 1)) {
                count += parentList[i].children.size
            }
        }
        return false
    }

    private fun getParentAt(position: Int): Parent {
        var count = 0
        for (i in parentList.indices) {
            if (count == position) {
                return parentList[i]
            }
            count++
            if (isExpanded(count - 1)) {
                count += parentList[i].children.size
            }
        }
        throw IllegalArgumentException("Invalid position")
    }

    private fun getChildAt(position: Int): Child {
        var count = 0
        for (i in parentList.indices) {
            count++
            if (isExpanded(count - 1)) {
                val children = parentList[i].children
                if (position <= count + children.size - 1) {
                    return children[position - count]
                }
                count += children.size
            }
        }
        throw IllegalArgumentException("Invalid position")
    }

    internal fun expandParent(position: Int) {
        expandedParentPosition = position
        expandedParentIndexList.add(position)
        notifyItemRangeInserted(position + 1, parentList[position].children.size)
    }

    fun collapseParent(position: Int) {
        expandedParentIndexList.remove(position)
        notifyItemRangeRemoved(position + 1, parentList[position].children.size)
    }
}