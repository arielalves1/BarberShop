package com.github.arielalves1.barbershop.view

import android.app.Activity
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.github.arielalves1.barbershop.databinding.ActivityAgendamentoBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import org.intellij.lang.annotations.JdkConstants.CalendarMonth
import java.time.Year
import java.util.Calendar

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome").toString()
        
        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->

            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,monthOfYear)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            var dia = dayOfMonth.toString()
            var mes = monthOfYear.toString()

            if (dayOfMonth < 10){
                dia = "0$dayOfMonth"
            }
            if (monthOfYear < 10){
                mes = "" + (monthOfYear+1)

            }else{
                mes = (monthOfYear +1).toString()
            }

            data = "$dia / $mes / $year"
        }

        binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->

            val minuto: String

            if(minute < 10){
                minuto ="0$minute"

            }else{
                minuto = minute.toString()

            }

            hora = "$hourOfDay:$minuto"


        }

        binding.timePicker.setIs24HourView(true)

        binding.buttonAgendar.setOnClickListener {
            val barbeiro1 = binding.barber1
            val barbeiro2 = binding.barber2
            val barbeiro3 = binding.barber3

            when{
                hora.isEmpty() -> {
                    mensagem(it,"Preencha o Horário!","#FF0000")
                }
                hora < "8:00" && hora  > "19:00" -> {
                    mensagem(it,"Estamos fechados nesse horário - Horário de funcionamento das 08:00h até 19:00h","#FF0000")
                }
                data.isEmpty() -> {
                    mensagem(it,"Preencha a Data!","#FF0000")
                }
                barbeiro1.isChecked && data.isNotEmpty() && hora.isNotEmpty() ->{
                 //   salvarAgendamento(it,nome, "João Pedro",data,hora)

                }
                barbeiro2.isChecked && data.isNotEmpty() && hora.isNotEmpty() ->{
                 //   salvarAgendamento(it,nome,"Thiago",data,hora)

                }
                barbeiro3.isChecked && data.isNotEmpty() && hora.isNotEmpty() ->{
                  //  salvarAgendamento(it,nome,"Manoel",data,hora)

                }
                else -> {
                    mensagem(it,"Escolha um profissional para realizar o seu atendimento!","#FF0000")

                }
            }
        }
    }
    private fun mensagem(view: View, mensagem: String, cor: String){
        val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }

    //FIREBASE
    
    /*
    private fun salvarAgendamento(view: View, cliente: String, barbeiro: String, data: String, hora: String){
        val db = FirebaseFirestore.getInstance()

        val dadosUser = hashMapOf(
            "cliente" to cliente,
            "barbeiro" to barbeiro,
            "data" to data,
            "hora" to hora
        )
*/
        db.collection("agendamento").document(cliente).set(dadosUser).addOnCompleteListener {
            mensagem(view, "Agendamento realizado com sucesso!","#4CAF50")

        }.addOnFailureListener {
            mensagem(view,"Erro no servidor!","#FF0000")
        }

    }





}