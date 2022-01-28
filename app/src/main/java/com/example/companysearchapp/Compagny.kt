package com.example.companysearchapp

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(indices = [ Index(value = ["id"], unique = true) ])
data class Compagny(@PrimaryKey(autoGenerate = true)
	var id : Int? = null,
	var name : String = "",

	var siren : String? = null,
	var siret : String? = null,

	var codePostal : String? = null,

	var numVoie : String? = null,
	var typeVoie : String? = null,
	var libVoie : String? = null,

	var arrondissement : String? = null,
	var libCommune : String? = null,

	var libRegion : String? = null,
	var departement : String? = null,

	var libActPrin : String? = null,
	var catEntre : String? = null
) : Serializable
{
	override fun toString(): String
	{
		return name
	}
}
