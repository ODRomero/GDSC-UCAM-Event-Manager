package com.example.clase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.clase.R.id.txtNom2

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Perfil.newInstance] factory method to
 * create an instance of this fragment.
 */
class Perfil : Fragment(){


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
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_perfil, container, false)
        val btnAct: Button = vista.findViewById(R.id.btRegister3)
        btnAct.setOnClickListener(){
            updateCambios(vista)
        }


        return vista

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Perfil.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Perfil().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    @SuppressLint("Range")
    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            val frag = requireArguments().getString("UserName")
            val et: EditText = requireView().findViewById(txtNom2)
            val et2: EditText = requireView().findViewById(R.id.txtUsu2)
            val et3: EditText = requireView().findViewById(R.id.txtTelf2)

            var usAct: String = ""
            var usNom: String = ""
            var usTel: String = ""
            var usCont:String = ""

            // Create or Instantiate the database
            val dataBaseHelper = DataBaseHelper(this.requireContext().applicationContext)

            val db_writer = dataBaseHelper.writableDatabase
            val selectQuery = "SELECT  * FROM Usuario WHERE email = ?"


            db_writer.rawQuery(selectQuery, arrayOf(frag)).use { // .use requires API 16
                if (it.moveToFirst()) {

                    usAct = it.getString(it.getColumnIndex("email"))
                    usNom = it.getString(it.getColumnIndex("nombre"))
                    usTel = it.getString(it.getColumnIndex("telefono"))
                    et.setText(usNom)
                    et2.setText(usAct)
                    et3.setText(usTel)

                }

            }

        }

    }

        fun updateCambios(view: View) {

                    val et: EditText = requireView().findViewById(txtNom2)
                    val et2: EditText = requireView().findViewById(R.id.txtUsu2)
                    val et3: EditText = requireView().findViewById(R.id.txtTelf2)
                    val et4: EditText = requireView().findViewById(R.id.txtPass2)
                    val et5: EditText = requireView().findViewById(R.id.txtPass3)


                    var usAct: String = ""
                    var usNom: String = ""
                    var usTel: String = ""
                    var usCont: String = ""
                    var usContComp: String = ""

                    usAct = et.text.toString();
                    usNom = et2.text.toString();
                    usTel = et3.text.toString();
                    usCont = et4.text.toString();
                    usContComp = et5.text.toString();

                    if (usAct != "" && usNom != "" && usTel != "" && usCont.equals(usContComp)) {
                        val dataBaseHelper = DataBaseHelper(this.requireContext().applicationContext)

                        val db_writer = dataBaseHelper.writableDatabase
                        // Create a new map of values, where column names are the keys
                        val values = ContentValues().apply {
                            put("email", usAct)
                            put("nombre", usNom)
                            put("telefono", usTel)
                            put("password", usCont)

                        }
                        Toast.makeText(getActivity(),
                            "Usuario Actualizado correctamente",
                            Toast.LENGTH_SHORT).show();
                        // Update rows, return the number of updated rows
                        val updatedRows = db_writer.update("Usuario", values, "email LIKE ?",
                            arrayOf(usAct))
                    } else {
                        Toast.makeText(getActivity(),
                            "El usuario debe rellenar alguno de los campos",
                            Toast.LENGTH_SHORT).show();
                    }

        }
}


