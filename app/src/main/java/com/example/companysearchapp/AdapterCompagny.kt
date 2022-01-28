package com.example.companysearchapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterCompagny (private val context: Context, private val compDAO : CompagnyDAO) : RecyclerView.Adapter<CompagnyViewHolder>()
{
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompagnyViewHolder
	{
		return CompagnyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_search_list, parent, false))
	}

	override fun onBindViewHolder(holder: CompagnyViewHolder, position: Int)
	{

	}

	override fun getItemCount(): Int
	{
		return 0
	}
}