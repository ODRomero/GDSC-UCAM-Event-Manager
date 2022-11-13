package com.example.clase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.SQLException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dataClasses.Evento


class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var eventos: MutableList<Evento>  = ArrayList()
    lateinit var context:Context

    var userId: String? = ""

    @SuppressLint("NotConstructor")
    fun RecyclerAdapter(eventos: MutableList<Evento>, context: Context, userId: String?){
        this.eventos = eventos
        this.context = context
        this.userId =userId.toString()

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = eventos.get(position)
        holder.bind(item, context, userId)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_evento, parent, false))
    }

    override fun getItemCount(): Int {

        return eventos.size
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombreEvento = view.findViewById(R.id.tvNombreEvento) as TextView
        val ubicacionEvento = view.findViewById(R.id.tvUbicacionEvento) as TextView
        val fechaEvento = view.findViewById(R.id.tvFechaEvento) as TextView
        val imageEvento = view.findViewById(R.id.ivImagenEvento) as ImageView
        val btDetallesEvento = view.findViewById(R.id.btMasInfo) as Button
        val btRegistrarmeEvento =  view.findViewById(R.id.btRegistrarmeEvento) as Button


        fun bind(evento:Evento, context: Context, userId: String?){
            nombreEvento.text = evento.nombre
            ubicacionEvento.text = evento.ubicacion
            fechaEvento.text = evento.fecha
            itemView.setOnClickListener(View.OnClickListener { Toast.makeText(context, evento.nombre, Toast.LENGTH_SHORT).show() })
            btDetallesEvento.setOnClickListener(){
                val detallesEvento = Intent(context, activityDetallesEvento::class.java).apply {
                    putExtra("EID",evento.EID)
                }

                startActivity(context,detallesEvento,null)
            }
            imageEvento.loadUrl(evento.foto)

            btRegistrarmeEvento.setOnClickListener() {
                val dataBaseHelper = DataBaseHelper(context.applicationContext)
                val db_writer = dataBaseHelper.writableDatabase



                val values = ContentValues().apply {
                    put("UID", userId.toString())
                    put("EID", evento.EID.toString())
                }

                try {
                    val newRowId = db_writer?.insert("UserEvent", null, values)
                }catch (e: SQLException){
                    val message:String?="No se pudo añadir userxEvent"
                    println(e.message)
                }

            }



        }
        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }
}