package com.github.arielalves1.barbershop

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.github.arielalves1.barbershop.databinding.ActivityMainBinding
import com.github.arielalves1.barbershop.view.Home
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar?.hide()

        binding.buttonLogin.setOnClickListener {

            val nome = binding.editName.text.toString()
            val senha = binding.editPassword.text.toString()

            when{
              nome.isEmpty() -> {
                 mensagem(it,"Preencha o seu Nome!")
              }senha.isEmpty() -> {
                mensagem(it,"Preencha a sua Senha!")
              }senha.length <= 5 -> {
                mensagem(it,"A Senha deve conter no mínimo 6 Caracteres!")
              }else -> {
                 goToHome(nome)
              }

            }
        }
    }

    private fun mensagem(view: View, mensagem: String){

        val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor( "#FF0000"))
        snackbar.setTextColor(Color.parseColor("#FFFFFFFF"))
        snackbar.show()


    }
    private fun goToHome(nome: String){
        val intent = Intent(this,Home::class.java)
        intent.putExtra("nome",nome)
        startActivity(intent)

    }
}