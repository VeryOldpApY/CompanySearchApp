package com.example.companysearchapp

data class Compagny(var id : Int? = null,
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
                    var catEntre : String? = null)
{
    override fun toString(): String
    {
        return "$name\n$codePostal - $libCommune\n$libActPrin"
    }
}
