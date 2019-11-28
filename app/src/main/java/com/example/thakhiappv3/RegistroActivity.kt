package com.example.thakhiappv3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.thakhiappv3.Conexion.ClsConexion
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        btnguardar.setOnClickListener {
            if(txtclave.text.toString() == txtconfirma.text.toString()){
                var url = ClsConexion.url + "RegistroCliente.php?CLIdni=" + txtdni.text.toString() +
                        "&CLInombre=" + txtnombre.text.toString() +
                        "&CLIapellido=" + txtapellido.text.toString() +
                        "&CLIcelular=" + txtcelular.text.toString() +
                        "&CLIemail=" + txtcorreo.text.toString() +
                        "&CLIclave=" + txtclave.text.toString()
                var rq= Volley.newRequestQueue(this)
                var sr= StringRequest(Request.Method.GET,url,
                    Response.Listener { response ->
                        if(response=="0")
                            Toast.makeText(this,"El cliente ya se encuentra registrado...",
                                Toast.LENGTH_LONG).show()
                        else{
                            Toast.makeText(this,"Datos guardados correctamente...",
                                Toast.LENGTH_LONG).show()
                            var i= Intent(this,LoginActivity::class.java)
                            startActivity(i)
                        }
                    },
                    Response.ErrorListener {  })
                rq.add(sr)
            }
            else{
                Toast.makeText(this,"Las contraseñas no coinciden... Intente de nuevo",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
}
