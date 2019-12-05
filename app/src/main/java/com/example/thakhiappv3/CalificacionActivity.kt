package com.example.thakhiappv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thakhiappv3.Clases.admEnttEntrega
import kotlinx.android.synthetic.main.activity_calificacion.*

class CalificacionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calificacion)

        codigo.text = "Codigo : " + admEnttEntrega.ENTid
        dni.text = admEnttEntrega.CONdni

    }
}
