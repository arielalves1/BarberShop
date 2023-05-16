package com.github.arielalves1.barbershop.view

import android.content.Intent
import android.os.Build.VERSION_CODES.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.github.arielalves1.barbershop.R
import com.github.arielalves1.barbershop.adapter.ServicosAdapter
import com.github.arielalves1.barbershop.databinding.ActivityHomeBinding
import com.github.arielalves1.barbershop.model.Servicos

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var servicosAdapter: ServicosAdapter
    private val listaServicos: MutableList<Servicos> = mutableListOf()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val nome = intent.extras?.getString("nome")

        binding.textNameUser.text = "Bem-Vindo(a) , $nome"
        val recyclerViewServicos = binding.recyclerViewServices
        recyclerViewServicos.layoutManager = GridLayoutManager(this,2)
        servicosAdapter = ServicosAdapter(this,listaServicos)
        recyclerViewServicos.setHasFixedSize(true)
        recyclerViewServicos.adapter = servicosAdapter
        getServicos()

        binding.buttonAgendar.setOnClickListener{
            val intent = Intent( this,Agendamento::class.java)
            intent.putExtra("nome",nome)
            startActivity(intent)

        }

    }

    private fun getServicos(){
        val servico1 = Servicos(R.drawable.barbershop,"Corte de Cabelo")
        listaServicos.add(servico1)

        val servico2 = Servicos(R.drawable.hairstyle,"Corte de Barba")
        listaServicos.add(servico2)

        val servico3 = Servicos(R.drawable.sink,"Lavagem Capilar")
        listaServicos.add(servico3)

        val servico4 = Servicos(R.drawable.gel,"Tratamentos")
        listaServicos.add(servico4)



    }


}