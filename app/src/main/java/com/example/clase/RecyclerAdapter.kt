package com.example.clase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dataClasses.Evento

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var eventos: MutableList<Evento>  = ArrayList()
    lateinit var context:Context

    fun RecyclerAdapter(eventos: MutableList<Evento>, context: Context){
        this.eventos = eventos
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = eventos.get(position)
        holder.bind(item, context)
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

        fun bind(evento:Evento, context: Context){
            nombreEvento.text = evento.nombre
            ubicacionEvento.text = evento.ubicacion.nombre
            fechaEvento.text = evento.fecha
            itemView.setOnClickListener(View.OnClickListener { Toast.makeText(context, evento.nombre, Toast.LENGTH_SHORT).show() })
            imageEvento.loadUrl(evento.foto)
        }
        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }
}