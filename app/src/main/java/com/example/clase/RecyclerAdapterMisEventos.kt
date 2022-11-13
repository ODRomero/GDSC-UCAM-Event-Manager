package com.example.clase

import android.content.Context
import android.content.Intent
import android.net.Uri
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

class RecyclerAdapterMisEventos : RecyclerView.Adapter<RecyclerAdapterMisEventos.ViewHolder>() {

    var eventos: MutableList<Evento>  = ArrayList()
    lateinit var context:Context

    fun RecyclerAdapter(eventos: MutableList<Evento>, context: Context){
        this.eventos = eventos
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = eventos[position]
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_mi_evento, parent, false))
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
        val btIrEvento = view.findViewById(R.id.btIrEvento) as Button

        fun bind(evento:Evento, context: Context){
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
            btIrEvento.setOnClickListener(){
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(evento.url)
                startActivity(context,intent, null)
            }
            imageEvento.loadUrl(evento.foto)
        }
        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }
}