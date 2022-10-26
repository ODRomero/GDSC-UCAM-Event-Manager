package com.example.clase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.clase.R
import com.example.clase.databinding.ActivityHomeBotNavBarBinding
import com.example.clase.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class activity_home_BotNavBar : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBotNavBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBotNavBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Inicio())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.inicio -> replaceFragment(Inicio())
                R.id.buscar -> replaceFragment(buscar())
                R.id.ajustes -> replaceFragment(Ajustes())
                R.id.eventos -> replaceFragment(Eventos())
                R.id.perfil -> replaceFragment(Perfil())
                else -> {false}
            }
        }
    }

    private fun replaceFragment(fragment : Fragment): Boolean {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
        return true
    }
}