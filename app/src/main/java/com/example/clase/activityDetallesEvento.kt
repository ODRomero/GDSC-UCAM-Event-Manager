package com.example.clase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.SQLException
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso

class activityDetallesEvento : AppCompatActivity() {
    var EID : Int?=0
    var URL : String?=""
    var ivImagenEventoDetalle2 :ImageView?=null
    var ivFacilitador2 :ImageView?=null
    var tvNombreEventoDetalle2 :TextView?=null
    var tvDescripcionEventoDetalle2:TextView?=null
    var tvUbicacionEventoDetalle2:TextView?=null
    var tvFechaEventoDetalle2:TextView?=null
    var tvFacilitador2:TextView?=null
    var tvBioFacilitador2:TextView?=null
    var tvNombreFacilitador2:TextView?=null



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_evento)

        ivImagenEventoDetalle2 = findViewById(R.id.ivImagenEventoDetalle)
        tvNombreEventoDetalle2 = findViewById(R.id.tvNombreEventoDetalle)
        tvDescripcionEventoDetalle2 = findViewById(R.id.tvDescripcionEventoDetalle)
        tvUbicacionEventoDetalle2 = findViewById(R.id.tvUbicacionEventoDetalle)
        tvFechaEventoDetalle2 = findViewById(R.id.tvFechaEventoDetalle)
        tvFacilitador2 = findViewById(R.id.tvFacilitador)
        tvBioFacilitador2 = findViewById(R.id.tvBioFacilitador)
        tvNombreFacilitador2 = findViewById(R.id.tvNombreFacilitador)
        ivFacilitador2 = findViewById(R.id.ivFacilitador)
        var btAsistir:Button = findViewById(R.id.btAsistirEventoDetalle)



        val extras = intent.extras
        if(extras == null) {
            EID= 0;
        } else {
            EID = extras.getInt("EID");
            URL = extras.getString("URL")
        }

        btAsistir.setOnClickListener() {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(URL)
            ContextCompat.startActivity(it.context, intent, null)

        }

        fillData()

    }
    @SuppressLint("Range")
    fun fillData(){
        var eid= arrayOf(EID.toString())

        var ivImagenEventoDetalle :String?=""
        var ivFacilitador :String?=""
        var tvNombreEventoDetalle :String?=""
        var tvDescripcionEventoDetalle:String?=""
        var tvUbicacionEventoDetalle:String?=""
        var tvFechaEventoDetalle:String?=""
        var tvFacilitador:String?=""
        var tvBioFacilitador:String?=""
        var tvNombreFacilitador:String?=""
        //TODO mostrar el nombre del evento, aparentemente no carga correctamente
        val dataBaseHelper = DataBaseHelper(applicationContext)
        val db_reader = dataBaseHelper.readableDatabase
        val cursor = db_reader.rawQuery("SELECT Evento.nombre as Enombre, fecha, descripcion, Evento.foto as Efoto, Facilitador.nombre as Fnombre, email, " +
                "bio, Facilitador.foto as Ffoto, Ubicacion.nombre as Unombre, aforo, " +
                "direccion FROM Evento INNER JOIN Facilitador ON Evento.FID = Facilitador.FID INNER JOIN Ubicacion ON Evento.UBID = Ubicacion.UBID where Evento.EID=?", eid)
        with(cursor) {
            while (moveToNext()) {
                tvNombreEventoDetalle = getString(getColumnIndex("Enombre"))
                tvFechaEventoDetalle = getString(getColumnIndex("fecha"))
                ivImagenEventoDetalle = getString(getColumnIndex("Efoto"))
                tvDescripcionEventoDetalle = getString(getColumnIndex("descripcion"))
                tvUbicacionEventoDetalle = getString(getColumnIndex("direccion"))
                tvNombreFacilitador = getString(getColumnIndex("Fnombre"))
                ivFacilitador = getString(getColumnIndex("Ffoto"))
                tvBioFacilitador = getString(getColumnIndex("bio"))

                ivImagenEventoDetalle2?.loadUrl(ivImagenEventoDetalle.toString())
                ivFacilitador2?.loadUrl(ivFacilitador.toString())
                tvNombreEventoDetalle2?.text=tvNombreEventoDetalle
                tvFechaEventoDetalle2?.text=tvFechaEventoDetalle
                tvDescripcionEventoDetalle2?.text=tvDescripcionEventoDetalle
                tvUbicacionEventoDetalle2?.text=tvUbicacionEventoDetalle
                tvNombreFacilitador2?.text=tvNombreFacilitador
                tvBioFacilitador2?.text=tvBioFacilitador

            }
        }
        db_reader.close()

    }
    fun ImageView.loadUrl(url: String) {
        Picasso.with(context).load(url).into(this)
    }
}