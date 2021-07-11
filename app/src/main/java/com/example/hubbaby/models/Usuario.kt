package com.example.hubbaby.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Usuario(
    @SerializedName("idUser")
    val idUser: Int,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("create_time")
    val createTime: String,
    @SerializedName("idPersona")
    val idPersona: Int
) : Serializable