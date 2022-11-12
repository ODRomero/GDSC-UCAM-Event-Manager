package com.example.clase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dataClasses.Evento

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Eventos.newInstance] factory method to
 * create an instance of this fragment.
 */
class Eventos(userEmail: String) : Fragment() {
    // TODO: Rename and change types of parameters
    private  var userEmail = userEmail
    private var param1: String? = userEmail
    private var param2: String? = null

    lateinit var mRecyclerView : RecyclerView
    private val mAdapter : RecyclerAdapter = RecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_eventos, container, false)


        setUpRecyclerView( view.findViewById(R.id.rvMisEventos) )


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Eventos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Eventos(param1 ).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun setUpRecyclerView(rvEventos : RecyclerView){
        mRecyclerView = rvEventos
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(view?.context)
        mAdapter.RecyclerAdapter(getEventos(), requireContext())
        mRecyclerView.adapter = mAdapter
    }


    @SuppressLint("Range")
    fun getEventos(): MutableList<Evento>{
        var eventos:MutableList<Evento> = ArrayList()
        var EID: String = ""
        var nom: String = ""
        var fech: String = ""
        var foto: String = ""
        var FNombre:String = ""
        var UNombre:String = ""
        var descripcion:String = ""



        val dataBaseHelper = DataBaseHelper(this.requireContext().applicationContext)
        val db_reader = dataBaseHelper.readableDatabase
        val cursor = db_reader.rawQuery("SELECT Evento.EID as EID, Evento.nombre as Enombre, fecha, descripcion, Evento.foto as Efoto, Facilitador.nombre as Fnombre, Ubicacion.nombre as Unombre FROM Evento LEFT JOIN Facilitador ON Evento.FID = Facilitador.FID LEFT JOIN Ubicacion ON Evento.UBID = Ubicacion.UBID JOIN UserEvent ON UserEvent.EID = Evento.EID JOIN Usuario ON UserEvent.UID = Usuario.UID WHERE Usuario.email = ?", arrayOf(userEmail))

        with(cursor) {
            while (moveToNext()) {
                EID = getString(getColumnIndex("EID"))
                nom = getString(getColumnIndex("Enombre"))
                fech = getString(getColumnIndex("fecha"))
                foto = getString(getColumnIndex("Efoto"))
                FNombre = getString(getColumnIndex("Fnombre"))
                UNombre = getString(getColumnIndex("Unombre"))
                descripcion=getString(getColumnIndex("descripcion"))
                eventos.add(Evento(EID.toInt(),nom,fech,descripcion,foto,UNombre, FNombre))
            }
        }
        cursor.close()
        return eventos
    }
}