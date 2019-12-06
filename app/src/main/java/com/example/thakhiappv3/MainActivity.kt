package com.example.thakhiappv3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.thakhiappv3.Clases.admEnttEntrega
import com.example.thakhiappv3.Conexion.ClsConexion
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val fechaactual = sdf.format(Date())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtdni.text = "DNI N° " + ClsConexion.dni
        txtnombre.text = ClsConexion.nombres

        ubicacion.setOnClickListener {
            var i= Intent(this,UbicacionActivity::class.java)
            startActivity(i)
        }

        valoracion.setOnClickListener {

            ObtenerDatosEntrega(ClsConexion.dni,fechaactual)
            var i= Intent(this,Splash2Activity::class.java)
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

    fun ObtenerDatosEntrega(dni:String,fecha:String){
        Toast.makeText(this,"DNI " + dni + " fecha: " + fecha,Toast.LENGTH_LONG).show()
        var url= ClsConexion.url + "ListarCalificaciones.php?CLIdni=" + dni + "&ENTfechahora=" + fecha
        var rq= Volley.newRequestQueue(this)

        val sr = JsonArrayRequest( Request.Method.GET, url, null,
            Response.Listener { response ->
                admEnttEntrega.ENTid = response.getJSONObject(0).getString("ENTid")
                admEnttEntrega.CONdni = response.getJSONObject(0).getString("CONdni")
                Toast.makeText(this,"numero:" + admEnttEntrega.ENTid,Toast.LENGTH_SHORT).show()
                /*ClsConexion.dni = response.getJSONObject(0).getString("CLIdni")
                ClsConexion.nombres = response.getJSONObject(0).getString("CLInombre") + " " +
                        response.getJSONObject(0).getString("CLIapellido")

                var i= Intent(this,MainActivity::class.java)
                startActivity(i)*/
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                Toast.makeText(this,"No hay entregas pendientes para hoy...", Toast.LENGTH_SHORT).show()
            }
        )
        rq.add(sr)
    }
}
