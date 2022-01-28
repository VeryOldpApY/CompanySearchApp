package com.example.companysearchapp

import android.util.JsonReader
import java.io.IOException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ServiceCompagny
{
	private val serverUrl = "https://entreprise.data.gouv.fr"
	private val apiUrl = "$serverUrl/api/sirene/v1"
	private val searchApi = "$apiUrl/full_text/%s"

	fun getCompagny(searchCompagny: String): Compagny?
	{
		val url = URL(String.format(searchApi, searchCompagny))
		var conn: HttpsURLConnection? = null
		try
		{
			conn = url.openConnection() as HttpsURLConnection
			conn.connect()
			if (conn.responseCode != HttpsURLConnection.HTTP_OK)
			{
				return null;
			}
			val inputStream = conn.inputStream ?: return null
			val result = Compagny()
			val reader = JsonReader(inputStream.bufferedReader())
			reader.beginObject()
			while (reader.hasNext())
			{
				if (reader.nextName() == "etablissement")
				{
					reader.beginArray()
					while(reader.hasNext())
					{
						reader.beginObject()
						while(reader.hasNext())
						{
							when (reader.nextName())
							{
								"id" -> result.id = reader.nextInt()
								/*"nom_raison_sociale" -> result.name = reader.nextString()*/

								"siren" -> result.siren = reader.nextString()
								"siret" -> result.siret = reader.nextString()

								"code_postal" -> result.codePostal = reader.nextString()

								/*"numero_voie" -> result.numVoie = reader.nextString()
								"type_voie" -> result.typeVoie = reader.nextString()
								"libelle_voie" -> result.libVoie = reader.nextString()

								"arrondissement" -> result.arrondissement = reader.nextString()
								"libelle_commune" -> result.libCommune = reader.nextString()

								"libelle_activite_principale" -> result.libActPrin = reader.nextString()
								"categorie_entreprise" -> result.catEntre = reader.nextString()*/
								else -> reader.skipValue()
							}
						}
						reader.endObject()
					}
					reader.endArray()
				}
				else
				{
					reader.skipValue()
				}
			}
			reader.endObject()
			reader.close()
			return result
		}
		catch (e: IOException)
		{
			return null
		}
		finally
		{
			conn?.disconnect()
		}
	}
}