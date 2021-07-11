package com.example.hubbaby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.hubbaby.databinding.ActivityMainBinding
import com.example.hubbaby.models.Usuario
import com.example.hubbaby.ui.GlobalViewModel
import com.example.hubbaby.utils.DBManager
import com.example.hubbaby.utils.Servicios
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var dbLocal: DBManager
    }

    private val viewModel: GlobalViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbLocal = DBManager(this, "hubbaby", null, 1)

        binding.btnIngresaLogin.setOnClickListener {

            val url = "${resources.getString(R.string.api_heroku)}usuarios"
            object : Servicios(){
                override fun respuestaServidor(response: String) {
                    val usuario = Gson().fromJson(response, Usuario::class.java)
                }
            }.consumeGet(this, url)
            if (binding.swRecuedameLogin.isChecked) {
                //TODO: dar de alta el usaurio logueado en la BD
                //dbLocal.altaUsuario()
            }

            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }
    }
}