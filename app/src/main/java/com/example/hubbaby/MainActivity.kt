package com.example.hubbaby

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.hubbaby.databinding.ActivityMainBinding
import com.example.hubbaby.models.ListaUsuarios
import com.example.hubbaby.models.UsuarioItem
import com.example.hubbaby.ui.GlobalViewModel
import com.example.hubbaby.utils.DBManager
import com.example.hubbaby.utils.Servicios
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var dbLocal: DBManager
        lateinit var usuarioLogeado: UsuarioItem
    }

    private val viewModel: GlobalViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbLocal = DBManager(this, "hubbaby", null, 1)

        val usuarios = dbLocal.consultaUsuarios()
        if (usuarios.isNotEmpty()) {
            usuarioLogeado = usuarios[0]
            val url = "${resources.getString(R.string.api_heroku)}usuarios"
            object : Servicios() {
                override fun respuestaServidor(response: String) {
                    var exitoAlAcceder = false
                    val listaUsuarios = Gson().fromJson(response, ListaUsuarios::class.java)
                    for (usuario in listaUsuarios) {
                        if (usuario.email == usuarioLogeado.email && usuario.password == usuarioLogeado.password) {
                            val intent = Intent(this@MainActivity, MenuPrincipal::class.java)
                            startActivity(intent)
                            exitoAlAcceder = true
                            break
                        }
                    }
                    if (!exitoAlAcceder) {
                        dbLocal.borrarUsuario(usuarioLogeado)
                    }
                }
            }.consumeGet(this, url)
        }

        binding.btnIngresaLogin.setOnClickListener {

            val mail = binding.tfEmailLogin.text.toString()
            val pass = binding.tfPasswordLogin.text.toString()

            val url = "${resources.getString(R.string.api_heroku)}usuarios"
            object : Servicios() {
                override fun respuestaServidor(response: String) {
                    val listaUsuarios = Gson().fromJson(response, ListaUsuarios::class.java)
                    for (usuario in listaUsuarios) {
                        if (usuario.email == mail && usuario.password == pass) {
                            if (binding.swRecuedameLogin.isChecked) {
                                dbLocal.altaUsuario(usuario)
                            }
                            usuarioLogeado = usuario
                            val intent = Intent(this@MainActivity, MenuPrincipal::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }.consumeGet(this, url)
        }
    }
}