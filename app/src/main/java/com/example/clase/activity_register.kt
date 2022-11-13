package com.example.clase

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class activity_register : AppCompatActivity() {
    var txtUsu: EditText?=null
    var txtPass: EditText?=null
    var txtNombre: EditText?=null
    var txtTelf: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        txtUsu=findViewById(R.id.txtUsu)
        txtPass=findViewById(R.id.txtPass)
        txtNombre=findViewById(R.id.txtNom)
        txtTelf=findViewById(R.id.txtTelf)

    }
    fun registerBt(v : View){
        var us=txtUsu?.text.toString()
        var pass=txtPass?.text.toString()
        var nom=txtNombre?.text.toString()
        var tel=txtTelf?.text.toString()
        var cont:Int=0
        val dataBaseHelper = DataBaseHelper(applicationContext)
        val db_writer = dataBaseHelper.writableDatabase
        val db_writer2 = dataBaseHelper.writableDatabase


        if(us!="" && pass!="" && nom!="" && tel!=""){

            val values = ContentValues().apply {
                put("email", us)
                put("password", pass)
                put("nombre", nom)
                put("telefono", tel)
            }

            val selectQuery = "SELECT  * FROM Usuario WHERE email = ?"
            db_writer2.rawQuery(selectQuery, arrayOf(us)).use {
                if (it.moveToFirst()) {
                    cont++
                }
                if(cont<1){
                    val view: activity_register =this
                    val newRowId = db_writer?.insert("Usuario", null, values)
                    Toast.makeText(this,"El usuario se ha guardado correctamente", Toast.LENGTH_LONG).show()
                    irLogin(view)
                }else{
                    Toast.makeText(this,"Existe un usuario con el mismo email, por favor cambialo", Toast.LENGTH_LONG).show()
                }

            }
        }
        txtPass?.setText("")
        txtUsu?.setText("")
        txtNombre?.setText("")
        txtTelf?.setText("")

    }
    fun irLogin(view: activity_register){

        val login=Intent(this, MainActivity::class.java)

        txtPass?.setText("")
        txtUsu?.setText("")
        txtNombre?.setText("")
        txtTelf?.setText("")
        startActivity(login)

    }
}
