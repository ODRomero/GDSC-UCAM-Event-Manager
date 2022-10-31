package com.example.clase

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class activityDetallesEvento : AppCompatActivity() {
    var EID : Int?=0
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
        tvNombreEventoDetalle2 = findViewById(R.id.tvNombreEvento)
        tvDescripcionEventoDetalle2 = findViewById(R.id.tvDescripcionEventoDetalle)
        tvUbicacionEventoDetalle2 = findViewById(R.id.tvUbicacionEventoDetalle)
        tvFechaEventoDetalle2 = findViewById(R.id.tvFechaEventoDetalle)
        tvFacilitador2 = findViewById(R.id.tvFacilitador)
        tvBioFacilitador2 = findViewById(R.id.tvBioFacilitador)
        tvNombreFacilitador2 = findViewById(R.id.tvNombreFacilitador)
        ivFacilitador2 = findViewById(R.id.ivFacilitador)

        val extras = intent.extras
        if(extras == null) {
            EID= 0;
        } else {
            EID = extras.getInt("EID");
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

        val dataBaseHelper = DataBaseHelper(applicationContext)
        val db_reader = dataBaseHelper.readableDatabase
        //TODO llamar a la bbdd y llenar las vistas con la informacion
        val cursor = db_reader.rawQuery("SELECT Evento.nombre as Enombre, fecha, descripcion, Evento.foto as Efoto, Facilitador.nombre as Fnombre, email, " +
                "bio, Facilitador.foto as Ffoto, Ubicacion.nombre as Unombre, aforo, " +
                "direccion FROM Evento INNER JOIN Facilitador ON Evento.FID = Facilitador.FID INNER JOIN Ubicacion ON Evento.EID = Ubicacion.UBID where Evento.EID=?", eid)
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