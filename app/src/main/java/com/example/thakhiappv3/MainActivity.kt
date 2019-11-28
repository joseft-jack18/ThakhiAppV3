package com.example.thakhiappv3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.thakhiappv3.Conexion.ClsConexion
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtdni.text = "DNI N° " + ClsConexion.dni
        txtnombre.text = ClsConexion.nombres

        ubicacion.setOnClickListener {
            var i= Intent(this,UbicacionActivity::class.java)
            startActivity(i)
        }

        perfil.setOnClickListener {
            var i= Intent(this,PerfilActivity::class.java)
            startActivity(i)
        }

        salir.setOnClickListener {
            Toast.makeText(this,"Cerrando Sesión...",Toast.LENGTH_LONG).show()
            ClsConexion.dni == ""
            ClsConexion.nombres == ""
            var i= Intent(this,SplashActivity::class.java)
            startActivity(i)
        }
    }
}
