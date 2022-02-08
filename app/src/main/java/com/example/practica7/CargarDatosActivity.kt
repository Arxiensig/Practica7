package com.example.practica7

import com.google.firebase.firestore.FirebaseFirestore


class CargarDatosActivity {
    private val db = FirebaseFirestore.getInstance()

    val paises:String="paises"
    val ciudades:String="ciudades"
    val monumentos:String="monumentos"
    fun loadDataPais() {
        //PAISES
        db.collection("paises").document("ES").set(
            hashMapOf(
                "nombre" to "ESPAÑA",
                "poblacion" to 47350000,
            )
        )
        db.collection("paises").document("FR").set(
            hashMapOf(
                "nombre" to "FRANCIA",
                "poblacion" to 67390000,
            )
        )
        db.collection("paises").document("IT").set(
            hashMapOf(
                "nombre" to "ITALIA",
                "poblacion" to 59550000,
            )
        )
        db.collection("paises").document("DE").set(
            hashMapOf(
                "nombre" to "ALEMANIA",
                "poblacion" to 83240000,
            )
        )
        db.collection("paises").document("UK").set(
            hashMapOf(
                "nombre" to "INGLATERRA",
                "poblacion" to 55980000,
            )
        )
    }
    fun loadDataCiudad(){
        // CIUDADES
        db.collection("ciudades").document("mad").set(
            hashMapOf(
                "nombre" to "Madrid",
                "poblacion" to 3223000,
                "cod_pais" to "ES",
            )
        )
        db.collection("ciudades").document("val").set(
            hashMapOf(
                "nombre" to "Valencia",
                "poblacion" to 791413,
                "cod_pais" to "ES",
            )
        )
        db.collection("ciudades").document("bar").set(
            hashMapOf(
                "nombre" to "Barcelona",
                "poblacion" to 1620000,
                "cod_pais" to "ES",
            )
        )
        db.collection("ciudades").document("par").set(
            hashMapOf(
                "nombre" to "París",
                "poblacion" to 2161000,
                "cod_pais" to "FR",
            )
        )
        db.collection("ciudades").document("tou").set(
            hashMapOf(
                "nombre" to "Toulouse",
                "poblacion" to 471941,
                "cod_pais" to "FR",
            )
        )
        db.collection("ciudades").document("ly").set(
            hashMapOf(
                "nombre" to "Lyon",
                "poblacion" to 513275,
                "cod_pais" to "FR",
            )
        )
        db.collection("ciudades").document("rom").set(
            hashMapOf(
                "nombre" to "Roma",
                "poblacion" to 2873000,
                "cod_pais" to "IT",
            )
        )
        db.collection("ciudades").document("mil").set(
            hashMapOf(
                "nombre" to "Milán",
                "poblacion" to 1352000,
                "cod_pais" to "IT",
            )
        )
        db.collection("ciudades").document("nap").set(
            hashMapOf(
                "nombre" to "Nápoles",
                "poblacion" to 3085000,
                "cod_pais" to "IT",
            )
        )
        db.collection("ciudades").document("mun").set(
            hashMapOf(
                "nombre" to "Munich",
                "poblacion" to 1472000,
                "cod_pais" to "DE",
            )
        )
        db.collection("ciudades").document("ber").set(
            hashMapOf(
                "nombre" to "Berlin",
                "poblacion" to 3645000,
                "cod_pais" to "DE",
            )
        )
        db.collection("ciudades").document("stu").set(
            hashMapOf(
                "nombre" to "Stuttgart",
                "poblacion" to 634830,
                "cod_pais" to "DE",
            )
        )
        db.collection("ciudades").document("lon").set(
            hashMapOf(
                "nombre" to "Londres",
                "poblacion" to 8982000,
                "cod_pais" to "UK",
            )
        )
        db.collection("ciudades").document("man").set(
            hashMapOf(
                "nombre" to "Manchester",
                "poblacion" to 553230,
                "cod_pais" to "UK",
            )
        )
        db.collection("ciudades").document("liv").set(
            hashMapOf(
                "nombre" to "Liverpool",
                "poblacion" to 496784,
                "cod_pais" to "UK",
            )
        )
    }
    fun loadDataMonumento(){
        // MONUMENTOS
        db.collection("monumentos").document("pr").set(
            hashMapOf(
                "nombre" to "Palacio Real",
                "cod_poblacion" to "mad",
            )
        )
        db.collection("monumentos").document("cac").set(
            hashMapOf(
                "nombre" to "Ciudad de las Artes y las Ciencias",
                "cod_poblacion" to "val",
            )
        )
        db.collection("monumentos").document("pg").set(
            hashMapOf(
                "nombre" to "Park Güell",
                "cod_poblacion" to "bar",
            )
        )
        db.collection("monumentos").document("tei").set(
            hashMapOf(
                "nombre" to "Torre Eiffel",
                "cod_poblacion" to "par",
            )
        )
        db.collection("monumentos").document("jarjap").set(
            hashMapOf(
                "nombre" to "Jardin Japonais Pierre Baudis",
                "cod_poblacion" to "tou",
            )
        )
        db.collection("monumentos").document("galr").set(
            hashMapOf(
                "nombre" to "Gallo-Roman Museum of Lyon-Fourvière",
                "cod_poblacion" to "ly",
            )
        )
        db.collection("monumentos").document("col").set(
            hashMapOf(
                "nombre" to "Coliseo Romano",
                "cod_poblacion" to "rom",
            )
        )
        db.collection("monumentos").document("duo").set(
            hashMapOf(
                "nombre" to "Duomo de Milán",
                "cod_poblacion" to "mil",
            )
        )
        db.collection("monumentos").document("ves").set(
            hashMapOf(
                "nombre" to "Monte Vesubio",
                "cod_poblacion" to "nap",
            )
        )
        db.collection("monumentos").document("mar").set(
            hashMapOf(
                "nombre" to "Marienplatz",
                "cod_poblacion" to "mun",
            )
        )
        db.collection("monumentos").document("muc").set(
            hashMapOf(
                "nombre" to "Museo de la cerveza",
                "cod_poblacion" to "mun",
            )
        )
        db.collection("monumentos").document("pbr").set(
            hashMapOf(
                "nombre" to "Puerta de Brandeburgo",
                "cod_poblacion" to "ber",
            )
        )
        db.collection("monumentos").document("lps").set(
            hashMapOf(
                "nombre" to "Librería pública de Stuttgart",
                "cod_poblacion" to "stu",
            )
        )
        db.collection("monumentos").document("lbr").set(
            hashMapOf(
                "nombre" to "London Bridge",
                "cod_poblacion" to "lon",
            )
        )
    }
}