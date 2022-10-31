package com.example.clase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Inicio.newInstance] factory method to
 * create an instance of this fragment.
 */
class Inicio : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)
        val imageTM1 = view.findViewById(R.id.ivTeamMember1) as ImageView
        val imageTM2 = view.findViewById(R.id.ivTeamMember2) as ImageView
        val imageTM3 = view.findViewById(R.id.ivTeamMember3) as ImageView
        val imageTM4 = view.findViewById(R.id.ivTeamMember4) as ImageView
        val imageTM5 = view.findViewById(R.id.ivTeamMember5) as ImageView
        val imageTM6 = view.findViewById(R.id.ivTeamMember6) as ImageView
        val imageTM7 = view.findViewById(R.id.ivTeamMember7) as ImageView
        val imageTM8 = view.findViewById(R.id.ivTeamMember8) as ImageView

        imageTM1.loadUrl("https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/avatars/oscar_romero_KyOMdfC.jpg")
        imageTM2.loadUrl("https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/avatars/daniel_rosique_egea_5dpR5L9.jpg")
        imageTM3.loadUrl("https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/avatars/alejandro_illan_marcos_BKEo4YM.jpg")
        imageTM4.loadUrl("https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/avatars/jorge_arcas.jfif")
        imageTM5.loadUrl("https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/avatars/david_macario_1vNwklj.jpg")
        imageTM6.loadUrl("https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/avatars/juan_miguel_correa_garcia.jpg")
        imageTM7.loadUrl("https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/avatars/gonzalo_del_fraile_and%C3%BAjar_kofmR5V.jpg")
        imageTM8.loadUrl("https://res.cloudinary.com/startup-grind/image/upload/c_fill,dpr_2.0,f_auto,g_center,h_250,q_auto:good,w_250/v1/gcs/platform-data-dsc/avatars/jesus_cristobal_gonzalez_gomez.png")


        // Inflate the layout for this fragment
        return view
    }

    fun ImageView.loadUrl(url: String) {
        Picasso.with(context).load(url).into(this)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Inicio.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Inicio().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}