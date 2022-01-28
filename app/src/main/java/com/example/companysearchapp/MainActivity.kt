package com.example.companysearchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
class MainActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val api = ServiceCompagny()
		val lst = findViewById<ListView>(R.id.lstResult)

		findViewById<Button>(R.id.btnsearch).setOnClickListener {
			val query = findViewById<EditText>(R.id.txtSearch).text.toString()
			Thread {
				val result = api.getCompagny(query)
				runOnUiThread {
					lst.adapter = ArrayAdapter(
						applicationContext,
						android.R.layout.simple_list_item_1,
						android.R.id.text1,
						result)
				}
			}.start()
		}
	}
}