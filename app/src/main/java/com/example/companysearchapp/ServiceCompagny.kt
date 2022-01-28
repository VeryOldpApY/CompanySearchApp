package com.example.companysearchapp

import android.util.JsonReader
import android.util.JsonToken
import java.io.IOException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ServiceCompagny
{
	private val serverUrl = "https://entreprise.data.gouv.fr"
	private val apiUrl = "$serverUrl/api/sirene/v1"
	private val searchApi = "$apiUrl/full_text/%s"

	fun getCompagny(searchCompagny: String) : List<Compagny>
	{
		val url = URL(String.format(searchApi, searchCompagny))
		var conn: HttpsURLConnection? = null
		try
		{
			conn = url.openConnection() as HttpsURLConnection
			conn.connect()
			if (conn.responseCode != HttpsURLConnection.HTTP_OK)
			{
				return emptyList();
			}
			val inputStream = conn.inputStream ?: return emptyList()
			val result = mutableListOf<Compagny>()
			var nb = 0
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
						result.add(nb, Compagny())
						while(reader.hasNext())
						{
							when (reader.nextName())
							{
								"id" -> result[nb].id = reader.nextInt()
								"nom_raison_sociale" -> result[nb].name = reader.nextString()

								"siren" -> result[nb].siren = reader.nextString()
								"siret" -> result[nb].siret = reader.nextString()

								"code_postal" -> result[nb].codePostal = reader.nextString()

								//"numero_voie" -> result[nb].numVoie = reader.nextString()
								//"type_voie" -> result[nb].typeVoie = reader.nextString()
								//"libelle_voie" -> result[nb].libVoie = reader.nextString()

								//"arrondissement" -> result[nb].arrondissement = reader.nextString()
								"libelle_commune" -> result[nb].libCommune = reader.nextString()

								"libelle_activite_principale" -> result[nb].libActPrin = reader.nextString()
								//"categorie_entreprise" -> result[nb].catEntre = reader.nextString()
								else -> reader.skipValue()
							}
						}
						reader.endObject()
						nb++
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
			return emptyList()
		}
		finally
		{
			conn?.disconnect()
		}
	}
}