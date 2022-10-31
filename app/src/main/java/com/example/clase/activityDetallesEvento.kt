package com.example.clase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class activityDetallesEvento : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_evento)
        val extras = intent.extras
        var EID : Int
        if(extras == null) {
            EID= 0;
        } else {
            EID = extras.getInt("EID");
        }

    }

    fun fillData(){
        //TODO llamar a la bbdd y llenar las vistas con la informacion
    }
}