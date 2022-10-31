package com.example.clase

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var txtUsu: EditText?=null
    var txtPass: EditText?=null

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtUsu=findViewById(R.id.txtUsu)
        txtPass=findViewById(R.id.txtPass)



    }
    fun regBt(v : View){
        val intent : Intent = Intent(this@MainActivity,    activity_register::class.java)
        startActivity(intent)
    }

    @SuppressLint("Range")
    fun loginBt(v : View){
        var pass=txtPass?.text.toString()
        var us=txtUsu?.text.toString()
        var parametros = arrayOf(pass, us)
        var passAct:String?=""
        var usAct:String?=""
        var cont:Int=0

        // Create or Instantiate the database
        if(pass!="" && us!="") {
            val dataBaseHelper = DataBaseHelper(applicationContext)
            val db_reader = dataBaseHelper.readableDatabase
            val cursor =
                db_reader.rawQuery("SELECT * FROM Usuario WHERE password=?"+"and email=?",
                    parametros)

            with(cursor) {
                while (moveToNext()) {

                        passAct = getString(getColumnIndex("password"))
                        usAct = getString(getColumnIndex("email"))
                        cont++

                }
            }
            cursor.close()
            if (cont >= 1) {
                val home = Intent(this, activity_home_BotNavBar::class.java).apply {
                    putExtra("UserName", us)
                    putExtra("UserPass", pass)
                }
                txtPass?.setText("")
                txtUsu?.setText("")
                startActivity(home)
            } else {
                Toast.makeText(this, "El usuario se debe loguear", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(this, "El usuario ha dejado alguno de los campos vacios", Toast.LENGTH_LONG).show()
        }
        txtPass?.setText("")
        txtUsu?.setText("")
    }

}


