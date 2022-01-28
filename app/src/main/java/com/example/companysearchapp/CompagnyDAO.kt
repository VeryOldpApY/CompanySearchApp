package com.example.companysearchapp

import androidx.room.*
import androidx.room.Dao

@Dao
interface CompagnyDAO
{
	@Query("SELECT * FROM compagny WHERE ")
	fun getAllTask(category_id : Long): List<Compagny>

	@Query("SELECT COUNT(*) FROM compagny WHERE ")
	fun count(category_id: Long?): Int

	@Query("SELECT * FROM compagny WHERE ")
	fun getByPosition(category_id: Int, position: Int): Compagny?

	@Insert
	fun insert(task: Compagny): Long

	@Update
	fun update(task: Compagny)

	@Delete
	fun delete(task: Compagny)
}
