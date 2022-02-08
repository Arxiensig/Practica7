package com.example.practica7

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.practica7.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*



enum class ProviderType {
    BASIC
}


private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //Recupero los datos y los meto dentro de los TextView
        val bundle=intent.extras
        var email=bundle?.getString("email")
        val provider=bundle?.getString("provider")
        setup(email?:"",provider?:"")

        //Guardado de datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file),
            Context.MODE_PRIVATE).edit()
        prefs.putString("email",email)
        prefs.putString("provider",provider)
        prefs.apply()


        initListeners()


    }

    private fun setup(email:String, provider:String){
        title= "Inicio"

        emailTextView.text=email
        providerTextView.text=provider
        //Creo un objeto para llamar a los metodos de otro Activity
        val instanceData = CargarDatosActivity()

        closeButton.setOnClickListener{
            val prefs = getSharedPreferences(getString(R.string.prefs_file),
                Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
        loadButton.setOnClickListener{
            instanceData.loadDataPais()
            instanceData.loadDataCiudad()
            instanceData.loadDataMonumento()

            Toast.makeText(
                this, "Información cargada con éxito", Toast.LENGTH_SHORT
            ).show()
        }
        consultarButton.setOnClickListener{
            val consulta = Intent(this, ConsultaActivity::class.java)
            startActivity(consulta)
        }

    }
    private fun initListeners() {
        val bannerIntent = Intent(this, BannerActivity::class.java)
        binding.btnBanner.setOnClickListener { startActivity(bannerIntent) }
    }
}