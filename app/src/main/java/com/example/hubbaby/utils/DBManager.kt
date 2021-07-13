package com.example.hubbaby.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.hubbaby.models.UsuarioItem

class DBManager(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql =
            "CREATE TABLE usuario(idUser INTEGER PRIMARY KEY, username TEXT NOT NULL, email TEXT NOT NULL, password TEXT )"

        db?.let {
            it.execSQL(sql)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    @Throws
    fun altaUsuario(usuario: UsuarioItem) {
        val db = writableDatabase

        val sql =
            "INSERT INTO usuario(idUser, username, email, password ) VALUES('${usuario.idUser}','${usuario.userName}', '${usuario.email}', '${usuario.password}')"

        db.execSQL(sql)

        db.close()
    }

    @Throws
    fun consultaUsuarios(): ArrayList<UsuarioItem> {
        val db = readableDatabase

        val sql = "SELECT idUser, username, email, password FROM usuario"

        val cursor = db.rawQuery(sql, null)

        val resultados = ArrayList<UsuarioItem>()
        while (cursor.moveToNext()) {
            val contact = UsuarioItem(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
            )

            resultados.add(contact)
        }
        db.close()

        return resultados
    }

    @Throws
    fun borrarUsuario(usuario: UsuarioItem) {
        val db = writableDatabase

        val sql = "DELETE FROM usuario WHERE idUser=${usuario.idUser}"

        db.execSQL(sql)

        db.close()
    }
}