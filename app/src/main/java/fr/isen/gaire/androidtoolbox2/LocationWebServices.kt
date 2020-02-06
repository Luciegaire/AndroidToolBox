package fr.isen.gaire.androidtoolbox2

class LocationWebServices{

    var street : StreetWebServices ?= null
    var city: String ?=null
    var state: String ?= null
    var country: String ?= null


    override fun toString(): String {
        return "${street} ${city} ${state} ${country}"
    }
}