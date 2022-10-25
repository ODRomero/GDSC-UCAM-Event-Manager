package com.example.clase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btLogin: Button = findViewById(R.id.btLogin)



    }
    fun regBt(v : View){
        val intent : Intent = Intent(this@MainActivity,    activity_home_BotNavBar::class.java)
        startActivity(intent)
    }

    fun loginBt(v : View){
        println("he entrado")
        val etPass : EditText = findViewById(R.id.etPassword)
        val etCorreo : EditText = findViewById(R.id.etCorreo)
        val pass : String = etPass.text.toString()
        val correo : String = etCorreo.text.toString()
        if(pass.equals("pass") && correo.equals("oscar")){
            val intent : Intent = Intent(this@MainActivity, HomeActivity::class.java).apply {
                putExtra("correo", correo)
            }
            startActivity(intent)
        }
    }
}