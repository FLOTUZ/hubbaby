package com.example.hubbaby.utils

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

abstract class Servicios {

    companion object {

        fun String.toast(c: Context) {
            Toast.makeText(c, this, Toast.LENGTH_LONG).show()
        }
    }

    fun consumePost(c: Context, url: String, params : MutableMap<String,String>) {
        val stringRequest = object : StringRequest(Request.Method.POST, url,
            Response.Listener { response ->
                respuestaServidor(response)
            },
            Response.ErrorListener { println("Error al consumir:\n$it") }

        ) {
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        Volley.newRequestQueue(c).add(stringRequest)
    }

    fun consumeGet(c: Context, url: String) {
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                respuestaServidor(response)
            },
            { println("Error al consumir:\n$it") }
        )
        Volley.newRequestQueue(c).add(stringRequest)
    }

    fun consumeGet(c: Context, url: String, params : MutableMap<String,String>) {
        val stringRequest = object : StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                respuestaServidor(response)
            },
            Response.ErrorListener { println("Error al consumir:\n$it") }
        ){
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        Volley.newRequestQueue(c).add(stringRequest)
    }

    abstract fun respuestaServidor(response: String)
}