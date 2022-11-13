package com.example.clase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.fragment.app.Fragment
import com.example.clase.databinding.ActivityHomeBotNavBarBinding


class activity_home_BotNavBar : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBotNavBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBotNavBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Inicio())

        var userEmail = intent.getStringExtra("UserName").toString()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.inicio -> replaceFragment(Inicio())
                R.id.buscar -> replaceFragment(buscar())
                R.id.ajustes -> replaceFragment(SettingsFragment())
                R.id.eventos -> replaceFragment(Eventos(userEmail))
                R.id.perfil -> replaceFragment(Perfil())
                else -> {false}
            }
        }
    }

    private fun replaceFragment(fragment : Fragment): Boolean {
        var intent: Intent =intent
        var userRecibed=intent.getStringExtra("UserName")
        val bundle = Bundle()
        bundle.putString("UserName", userRecibed)
        fragment.arguments = bundle

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)

        fragmentTransaction.commit()
        return true
    }

    fun setDayNight(theme:Int?) {
        //val sp = getSharedPreferences("SP", MODE_PRIVATE)
        //val theme = sp.getInt("Theme",1)
        if (theme == 0) {
            delegate.localNightMode = MODE_NIGHT_YES
        } else {
            delegate.localNightMode = MODE_NIGHT_NO
        }
    }
}
