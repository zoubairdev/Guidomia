package com.example.guidomia.viewholders

import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guidomia.R
import com.example.guidomia.models.Child


class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val prosLinearLayout: LinearLayout = itemView.findViewById(R.id.prosLinearLayout)
    private val consLinearLayout: LinearLayout = itemView.findViewById(R.id.consLinearLayout)
    private val refLinearLayout: LinearLayout = itemView.findViewById(R.id.refLinearLayout)
    private val circleIV: ImageView = itemView.findViewById(R.id.circleIV)
    private val descriptionTV: TextView = itemView.findViewById(R.id.descriptionTV)

    fun bind(child: Child) {
        prosLinearLayout.removeAllViews()
        consLinearLayout.removeAllViews()
        child.prosList.forEach{
            val horizontalLayout = LinearLayout(refLinearLayout.context)
            horizontalLayout.orientation = LinearLayout.HORIZONTAL
            val checkCircleImageView = ImageView(circleIV.context)
            val imageViewParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.1f)
            imageViewParams.gravity = Gravity.CENTER
            checkCircleImageView.layoutParams = imageViewParams
            checkCircleImageView.setImageResource(R.drawable.ic_baseline_check_circle_24)
            val prosTextView = TextView(descriptionTV.context)
            val textViewParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.9f)
            textViewParams.gravity = Gravity.CENTER
            prosTextView.layoutParams = textViewParams
            prosTextView.textSize = 18f
            prosTextView.text = it
            horizontalLayout.addView(checkCircleImageView)
            horizontalLayout.addView(prosTextView)
            prosLinearLayout.addView(horizontalLayout)
        }

        child.consList.forEach{
            val horizontalLayout = LinearLayout(refLinearLayout.context)
            horizontalLayout.orientation = LinearLayout.HORIZONTAL
            val checkCircleImageView = ImageView(circleIV.context)
            val imageViewParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.1f)
            imageViewParams.gravity = Gravity.CENTER
            checkCircleImageView.layoutParams = imageViewParams
            checkCircleImageView.setImageResource(R.drawable.ic_baseline_check_circle_24)
            val consTextView = TextView(descriptionTV.context)
            val textViewParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.9f)
            textViewParams.gravity = Gravity.CENTER
            consTextView.layoutParams = textViewParams
            consTextView.textSize = 18f
            consTextView.text = it
            horizontalLayout.addView(checkCircleImageView)
            horizontalLayout.addView(consTextView)
            consLinearLayout.addView(horizontalLayout)
        }

    }
}