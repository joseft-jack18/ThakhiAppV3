package com.example.thakhiappv3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.thakhiappv3.Clases.Client
import com.example.thakhiappv3.Clases.Cliente
import com.example.thakhiappv3.Conexion.ClsConexion
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var cli = Client()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btningresar.setOnClickListener {

            var url= ClsConexion.url + "Login.php?CLIemail=" + txtcorreo.text.toString() + "&CLIclave=" + txtpassword.text.toString()
            var rq= Volley.newRequestQueue(this)

            val sr = JsonArrayRequest( Request.Method.GET, url, null,
                Response.Listener { response ->
                    Toast.makeText(this,"Bienvenido " + response.getJSONObject(0).getString("CLInombre"),
                        Toast.LENGTH_SHORT).show()
                    cli.clIdni = response.getJSONObject(0).getString("CLIdni")
                    ClsConexion.nombres = response.getJSONObject(0).getString("CLInombre") + " " +
                            response.getJSONObject(0).getString("CLIapellido")

                    var i= Intent(this,MainActivity::class.java)
                    startActivity(i)
                },
                Response.ErrorListener { error ->
                    error.printStackTrace()
                    Toast.makeText(this,"Usuario y/o clave incorrecto...", Toast.LENGTH_SHORT).show()
                }
            )
            rq.add(sr)
        }

        txtregistrar.setOnClickListener{
            var i = Intent(this,RegistroActivity::class.java)
            startActivity(i)
        }
    }
}
