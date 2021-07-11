package com.example.hubbaby.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.hubbaby.models.Usuario

class DBManager(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql =
            "CREATE TABLE usuario(id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, email TEXT NOT NULL, password TEXT )"

        db?.let {
            it.execSQL(sql)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    @Throws
    fun altaUsuario(usuario: Usuario) {
        val db = writableDatabase

        val sql =
            "INSERT INTO usuario(username, email, password ) VALUES('${usuario.userName}', '${usuario.email}', '${usuario.password}')"

        db.execSQL(sql)

        db.close()
    }

    @Throws
    fun borrarUsuario(usuario: Usuario) {
        val db = writableDatabase

        val sql = "DELETE FROM contacto WHERE id=${usuario.idUser}"

        db.execSQL(sql)

        db.close()
    }
}