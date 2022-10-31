package com.example.clase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

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
        //val cursor = db.rawQuery("SELECT Evento.nombre as Enombre, fecha, descripcion, Evento.foto as Efoto, Facilitador.nombre as Fnombre, email, bio, Facilitador.foto as Ffoto, Ubicacion.nombre as Unombre, aforo, " +
          //      "direccion FROM Evento INNER JOIN Facilitador ON Evento.FID = Facilitador.FID INNER JOIN Ubicacion ON Evento.EID = Ubicacion.UBID", null)
    }
}