package com.example.thakhiappv3

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.thakhiappv3.Clases.admEnttEntrega
import com.example.thakhiappv3.Conexion.ClsConexion
import kotlinx.android.synthetic.main.activity_calificacion.*
import kotlinx.android.synthetic.main.activity_login.*
import java.text.SimpleDateFormat
import java.util.*

class CalificacionActivity : AppCompatActivity() {

    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val fechaactual = sdf.format(Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calificacion)

        codigo.text = admEnttEntrega.ENTid
        descripcion.text = "Producto: " + admEnttEntrega.ENTdescripcion
        dni.text = "DNI Conductor: " + admEnttEntrega.CONdni
        conductor.text = "Conductor: " + admEnttEntrega.CONnombre + " " + admEnttEntrega.CONapellido
        celular.text = "Celular: " + admEnttEntrega.CONcelular
        costo.text = "S/ " + admEnttEntrega.ENTprecio

        llamada.setOnClickListener {
            //Toast.makeText(this,"conteo " + favorite.rating.toString(), Toast.LENGTH_LONG).show()
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:" + admEnttEntrega.CONcelular)
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

        btnguardar.setOnClickListener {
            Toast.makeText(this,"dni "+admEnttEntrega.CONdni+"-"+favorite.rating.toDouble()+ "-"+fechaactual.toString(),Toast.LENGTH_LONG).show()
            var url= ClsConexion.url + "Calificar.php?CONdni=" + admEnttEntrega.CONdni +
                    "&CALcalificacion=" + favorite.rating.toString() + "&CALfecha=" + fechaactual.toString()
            var rq= Volley.newRequestQueue(this)

            var sr= StringRequest(Request.Method.GET,url,
                Response.Listener { response ->
                    if(response=="1")
                        Toast.makeText(this,"Calificacion guardada!!",
                            Toast.LENGTH_LONG).show()
                    var i= Intent(this,MainActivity::class.java)
                    startActivity(i)
                },
                Response.ErrorListener {  })
            rq.add(sr)
        }
    }
}
