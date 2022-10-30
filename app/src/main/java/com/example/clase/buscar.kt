package com.example.clase

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dataClasses.Evento
import dataClasses.Facilitador
import dataClasses.Ubicacion


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
    val mAdapter : RecyclerAdapter = RecyclerAdapter()

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
    ): View? {
        setUpRecyclerView( requireView().findViewById<RecyclerView>(R.id.rvEventos) )
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buscar, container, false)
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

    fun setUpRecyclerView(rvEventos : RecyclerView){
        mRecyclerView = rvEventos
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
         mAdapter.RecyclerAdapter(getEventos(), requireContext())
        mRecyclerView.adapter = mAdapter
    }

    //TODO: sacar los eventos, facilitadores y ubicaciones de la BBDD en vez de tenerlos quemados
    fun getEventos(): MutableList<Evento>{
        var eventos:MutableList<Evento> = ArrayList()
        //Ubicaciones
        var ubicacionHiTech: Ubicacion = Ubicacion("Ctra. Nora Guadalupe, 3A, 30107 Murcia", "50 Personas", "UCAM HiTech")
        var ubicacionUCAM: Ubicacion = Ubicacion("Av. de los Jerónimos, 135, 30107 Guadalupe de Maciascoque, Murcia", "28 Personas", "UCAM Pabellón 5 Sótano API 6")
        var ubicacionOnline: Ubicacion = Ubicacion("Plataforma GDSC UCAM", "Ilimitado", "Online")
        //Facilitadores
        var facilitadorOscar: Facilitador = Facilitador("Oscar Romero", "Team Lead del GDSC UCAM", "oromero@gdsc_ucam.com", "https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/events/IMG_20220129_120113_494.jpg")
        var facilitadorJorge: Facilitador = Facilitador("Jorge Arcas", "Core Team Member del GDSC UCAM", "jarcas@gdsc_ucam.com", "https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/events/ezgif.com-gif-maker_22hnFZ2.png")
        var facilitadorJesus: Facilitador = Facilitador("Jesus Gonzalez", "Core Team Member del GDSC UCAM", "jgonzalez@gdsc_ucam.com", "https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/events/ezgif.com-gif-maker%20%282%29.png")
        //Eventos
        eventos.add(Evento("Presentacion GDSC UCAM", "Oct 4, 2022", "Presentaremos al equipo que conforma al equipo del GDSC UCAM y los tipo de eventos que queremos realizar este año", "https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_xy_center,h_650,q_auto:good,w_1200,x_w_mul_0.5,y_h_mul_0.38/v1/gcs/platform-data-dsc/event_banners/Event%20Promo%20-%20Presentacion_lJi3o2J.png", ubicacionHiTech, facilitadorOscar))
        eventos.add(Evento("Taller GitHub Basico", "Oct 11, 2022", "Introduccion al control de versiones e integraciones con VS Code", "https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_xy_center,h_650,q_auto:good,w_1200,x_w_mul_0.5,y_h_mul_0.55/v1/gcs/platform-data-dsc/event_banners/GitHub%20Basico%20Promo%20-%20Green.jpg", ubicacionUCAM, facilitadorJorge))
        eventos.add(Evento("Taller Python Basico", "Oct 15, 2022", "Introduccion al lenguaje de progracion Python", "https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_xy_center,h_650,q_auto:good,w_1200,x_w_mul_0.5,y_h_mul_0.65/v1/gcs/platform-data-dsc/event_banners/promo%20taller%20basico%20de%20python.png",  ubicacionOnline, facilitadorJesus))
        return eventos
    }
}