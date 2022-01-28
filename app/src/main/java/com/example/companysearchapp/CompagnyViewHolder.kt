package com.example.companysearchapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CompagnyViewHolder (row : View) : RecyclerView.ViewHolder(row)
{
	val imgTask: TextView = row.findViewById(R.id.txtNameComp)
	val txtTask: TextView = row.findViewById(R.id.txtDepComp)
}