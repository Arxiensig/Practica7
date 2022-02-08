package com.example.practica7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_consulta.*

class ConsultaActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    //Creamps la constante que utilizaremos para instanciar Firebase
    private val db = FirebaseFirestore.getInstance()

    //Creamos 2 listas de objeto
    var listaObjetoCountry : MutableList<Pais> = mutableListOf()
    var listaObjetoCity : MutableList<Ciudades> = mutableListOf()

    //Creamos 2 variables para instanciar los niveles de poblacion del pais y de la ciudad
    var poblacionPaisNum:Double=0.0
    var poblacionCiudadNum:Double=0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta)
        //Setup
        setup()
    }
    private fun setup() {
        //Cargamos los spinner de Paises
        cargarSpinnerPaises()
    }
    private fun cargarSpinnerPaises() {
        //Creamos una lista para manipular los nombres de las ciudades
        var listCountry: MutableList<String> =mutableListOf()

        //Llamamos a la colección para cargar la lista de los paises. Al mismo tiempo creamos y cargamos una lista de objetos para mas adelante llamar a este objeto de ser necesario.
        db.collection("paises").get().addOnSuccessListener {
            result ->
            for (document in result){
                listCountry.add(document.data["nombre"].toString())
                var objectoPais=Pais(document.id,document.data["nombre"].toString(),document.data["poblacion"].toString())
                listaObjetoCountry.add(objectoPais)
            }
            //Tras cargar la lista cargamos el adaptador que utilizaremos para montar el spinner de paises
            montarAdaptador(listCountry)

            //Justo despues cargaremos el spinner de Ciudades
            cargarSpinnerCiudades()
        }.addOnFailureListener{
            exception ->
            Log.w("TAG","error de documentacion", exception)
        }
    }

    //Función creada para montar el adaptador de paises
    private fun montarAdaptador(listaPaises : MutableList<String>){
        var adaptadorPaises = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, listaPaises)
        spinnerPaises.adapter=adaptadorPaises
        spinnerPaises.onItemSelectedListener = this
    }

    private fun cargarSpinnerCiudades(){
        //Creamos una lista que almacenara los String de las ciudades
        var listCiudades: MutableList<String> =mutableListOf()

        //Creamos una variable donde instanciaremos el id del Pais que tenemos seleccionado en el spinner de paises
        var paisElegido=listaObjetoCountry[spinnerPaises.selectedItemPosition].idPais

        //Llamamos a la colección de ciudades para cargar el spinner de ciudades ( en relacion al pais seleccionado)
        db.collection("ciudades").get().addOnSuccessListener {
                result ->
            for (document in result){
                if (document.data["cod_pais"]==paisElegido){
                    listCiudades.add(document.data["nombre"].toString())
                    var objectoCiudad=Ciudades(document.id,document.data["cod_pais"].toString(),document.data["nombre"].toString(),document.data["poblacion"].toString())
                    listaObjetoCity.add(objectoCiudad)
                    }
            }
            //Montamos el adaptador de ciudades pasandole la lista de ciudades que contienen los nombres de las mismas
            montarAdaptadorCiudades(listCiudades)
        }.addOnFailureListener{
                exception ->
            Log.w("TAG","error de documentacion", exception)
        }
    }
    //adaptador del spinner de ciudades
    private fun montarAdaptadorCiudades(listaCiudades : MutableList<String>){
        var adaptadorCiudades = ArrayAdapter(this,
            android.R.layout.simple_list_item_1, listaCiudades)
        spinnerCiudades.adapter=adaptadorCiudades
        spinnerCiudades.onItemSelectedListener = this
    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //Condicionamos el trabajo del spinner en relacion al id del adaptador (po) que ha sido utilizado
        if (p0?.id==spinnerPaises.id){
            //Setteamos la selección del spinner pais
            spinnerPaises.setSelection(p2)

            var listCiudades: MutableList<String> =mutableListOf()
            //Creamos una variable con la coleccion de ciudades
            var paisElegido=listaObjetoCountry[spinnerPaises.selectedItemPosition].idPais
            db.collection("ciudades").get().addOnSuccessListener {
                    result ->
                for (document in result){
                    if (document.data["cod_pais"]==paisElegido){
                        listCiudades.add(document.data["nombre"].toString())
                    }
                }
                montarAdaptadorCiudades(listCiudades)

            }.addOnFailureListener{
                    exception ->
                Log.w("TAG","error de documentacion", exception)
            }
            }
        if (p0?.id==spinnerCiudades.id) {
            //Setteamos el spinner de ciudades
            spinnerCiudades.setSelection(p2)

            //Recogemos el id del item del spinner de pais
            var paisElegido = listaObjetoCountry[spinnerPaises.selectedItemPosition].idPais

            //Creamos una variable donde almacenaremos el numero de poblacion del pais
            poblacionPaisNum= listaObjetoCountry[spinnerPaises.selectedItemPosition].poblacionPais.toDouble()


            //TEXTVIEW DEL PAIS
            var oPais = Pais("", "", "")
            //Llamamos a la colección para leer y cargar un objeto que usaremos para cargar los text
            db.collection("paises").get().addOnSuccessListener { result ->
                for (document in result) {
                    if (document.id == paisElegido) {
                        oPais = Pais(
                            document.id,
                            document.data["nombre"].toString(),
                            document.data["poblacion"].toString()
                        )
                        textPais.setText(oPais.nombrePais)
                        textViewPais.setText(oPais.poblacionPais)
                    }



            }}


            //TEXTVIEW DE LA CIUDAD
            var ciudadElegida = listaObjetoCity[spinnerCiudades.selectedItemPosition].id

            //Le damos valor a la variable de la poblacion de la ciudad
            poblacionCiudadNum= listaObjetoCity[spinnerCiudades.selectedItemPosition].poblacion.toDouble()
            db.collection("ciudades").get().addOnSuccessListener { result ->
                var oCiudad = Ciudades("", "", "","")
                for (document in result) {
                    if (document.id == ciudadElegida) {
                        oCiudad= Ciudades(
                            document.id,
                            document.data["cod_pais"].toString(),
                            document.data["nombre"].toString(),
                            document.data["poblacion"].toString())
                            }
                        textCity.setText(oCiudad.nombre)
                        textViewCiudad.setText(oCiudad.poblacion)
                    }



                }

            //TEXT VIEW DEL MONUMENTO
            var ciudadElegidaMonument = listaObjetoCity[spinnerCiudades.selectedItemPosition].id
            var listMonumentos: MutableList<String> =mutableListOf()
            db.collection("monumentos").get().addOnSuccessListener { result ->
                for (document in result) {
                    if (document.data["cod_poblacion"] == ciudadElegidaMonument) {
                        listMonumentos.add(document.data["nombre"].toString())

                    }
                }
                textViewMonumentos.setText("")
                for (nombre in listMonumentos){
                    textViewMonumentos.append("${nombre}\n")
                }
            }

        }
        //Creamos una condicion para borrar la lista de objetos de ciudad para de este modo que no se carguen todos los monumentos seleccionado con anterioridad en otras ciudades
        if (listaObjetoCity.isNotEmpty()){
            var paisElegido=listaObjetoCountry[spinnerPaises.selectedItemPosition].idPais
            listaObjetoCity.clear()
            db.collection("ciudades").get().addOnSuccessListener {
                    result ->
                for (document in result){
                    if (document.data["cod_pais"]==paisElegido){
                        var objectoCiudad=Ciudades(document.id,document.data["cod_pais"].toString(),document.data["nombre"].toString(),document.data["poblacion"].toString())
                        listaObjetoCity.add(objectoCiudad)
                    }
                }
            }.addOnFailureListener{
                    exception ->
                Log.w("TAG","error de documentacion", exception)
            }
        }
        //Realizamos una operacion que servira para calcular el porcentaje de la poblacion seleccionada
        var resultado:Double=(poblacionCiudadNum/poblacionPaisNum)*100
        textViewPorcentaje.text= String.format("%.1f",resultado)

    }


    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}

