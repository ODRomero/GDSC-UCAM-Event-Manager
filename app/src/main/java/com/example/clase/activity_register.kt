package com.example.clase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class activity_register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
    fun registerBt(v : View){
        println("he entrado")
        val etPass : EditText = findViewById(R.id.etPassword)
        val etCorreo : EditText = findViewById(R.id.etCorreo)
        val etNom : EditText = findViewById(R.id.etNombre)

        val pass : String = etPass.text.toString()
        val correo : String = etCorreo.text.toString()
        val nombre : String = etNom.text.toString()

        if(pass.isNotEmpty() && correo.isNotEmpty() && nombre.isNotEmpty()){
            val intent : Intent = Intent(this@activity_register, HomeActivity::class.java).apply {
                putExtra("correo", correo)
                putExtra("nombre", nombre)
            }
            startActivity(intent)
        }
    }
}