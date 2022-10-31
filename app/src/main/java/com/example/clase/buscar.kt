package com.example.clase

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dataClasses.Evento


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [buscar.newInstance] factory method to
 * create an instance of this fragment.
 */
class buscar : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
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
        savedInstanceState: Bundle?,
    ): View {

        val view: View = inflater.inflate(R.layout.fragment_buscar, container, false)


        setUpRecyclerView( view.findViewById(R.id.rvEventos) )
        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment buscar.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            buscar().apply {
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

    //TODO: sacar los eventos, facilitadores y ubicaciones de la BBDD en vez de tenerlos quemados
    @SuppressLint("Range")
    fun getEventos(): MutableList<Evento>{
        var eventos:MutableList<Evento> = ArrayList()

        var nom: String = ""
        var fech: String = ""
        var foto: String = ""
        var FID:String = ""
        var UBID:String = ""
        var descripcion:String = ""

        // Create or Instantiate the database
        val dataBaseHelper = DataBaseHelper(this.requireContext().applicationContext)
        val db_reader = dataBaseHelper.readableDatabase
        val cursor = db_reader.query(
            "Evento",      // The table to query
            null,           // The array of columns to return (pass null to get all)
            null,           // The columns for the WHERE clause
            null,           // The values for the WHERE clause
            null,           // don't group the rows
            null,           // don't filter by row groups
            null // The sort order
        )
        with(cursor) {
            while (moveToNext()) {
                nom = getString(getColumnIndex("nombre"))
                fech = getString(getColumnIndex("fecha"))
                foto = getString(getColumnIndex("foto"))
                FID = getString(getColumnIndex("FID"))
                UBID = getString(getColumnIndex("UBID"))
                descripcion=getString(getColumnIndex("descripcion"))
                //val fal=getFacilitador(FID)
                //val ub=getUbi(UBID)
                eventos.add(Evento(nom,fech,descripcion,foto,UBID,FID))
            }
        }
        cursor.close()

        print(eventos.get(0).foto)

        return eventos
    }
    /*@SuppressLint("Range")
    fun getFacilitador(string :String) : Facilitador{
        var facilitador: Facilitador=Facilitador("","","","")
        val FID=arrayOf("FID")
        val id=arrayOf(string)
        var nombre:String=""
        var foto2:String=""
        var email:String=""
        var bio:String=""

        // Create or Instantiate the database
        val dataBaseHelper = DataBaseHelper(this.requireContext().applicationContext)
        val db_reader = dataBaseHelper.readableDatabase
        val cursor = db_reader.query(
            "Facilitador",      // The table to query
            null,           // The array of columns to return (pass null to get all)
            FID.toString(),           // The columns for the WHERE clause
            id,           // The values for the WHERE clause
            null,           // don't group the rows
            null,           // don't filter by row groups
            null // The sort order
        )
        with(cursor) {
            while (moveToNext()) {
                nombre= getString(getColumnIndex("nombre"))
                foto2 = getString(getColumnIndex("foto"))
                email=getString(getColumnIndex("email"))
                bio=getString(getColumnIndex("descripcion"))
                facilitador = Facilitador(email ,bio ,nombre ,foto2)
            }
        }
        cursor.close()
        return facilitador

    }*/
   /* @SuppressLint("Range")
    fun getUbi(string :String) : Ubicacion{
        var ubi: Ubicacion=Ubicacion("","","")
        val FID=arrayOf("UBID")
        val id=arrayOf(string)
        var nombre:String=""
        var dir:String=""
        var aforo:String=""


        // Create or Instantiate the database
        val dataBaseHelper = DataBaseHelper(this.requireContext().applicationContext)
        val db_reader = dataBaseHelper.readableDatabase
        val cursor = db_reader.query(
            "Ubicacion",      // The table to query
            null,           // The array of columns to return (pass null to get all)
            FID.toString(),           // The columns for the WHERE clause
            id,           // The values for the WHERE clause
            null,           // don't group the rows
            null,           // don't filter by row groups
            null // The sort order
        )
        with(cursor) {
            while (moveToNext()) {
                nombre = getString(getColumnIndex("nombre"))
                dir = getString(getColumnIndex("direccion"))
                aforo=getString(getColumnIndex("aforo"))

                ubi = Ubicacion(dir, aforo, nombre)
            }
        }
        cursor.close()
        return ubi

    }*/
}