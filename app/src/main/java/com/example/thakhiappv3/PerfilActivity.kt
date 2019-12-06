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
import kotlinx.android.synthetic.main.activity_perfil.*
import kotlinx.android.synthetic.main.activity_registro.*
import kotlinx.android.synthetic.main.activity_registro.btnguardar
import kotlinx.android.synthetic.main.activity_registro.txtcelular
import kotlinx.android.synthetic.main.activity_registro.txtcorreo

class PerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)




        btnguardar.setOnClickListener {
            if(txtclave.text.toString() != txtconfirma.text.toString()){
                Toast.makeText(this,"Las contraseñas no coinciden... Intente de nuevo",
                    Toast.LENGTH_LONG).show()
            }
            else if(txtcelular.text.toString()=="" && txtcorreo.text.toString()=="" && txtnuevaclave.text.toString()==""){
                Toast.makeText(this,"Rellene los campos del formulario...",
                    Toast.LENGTH_LONG).show()
            }
            else{
                var url = ClsConexion.url + "ModificarClienteCliente.php?CLIcelular=" + txtcelular.text.toString() +
                        "&CLIemail=" + txtcorreo.text.toString() +
                        "&CLIclave=" + txtnuevaclave.text.toString() +
                        "&CLIdni=" + ClsConexion.dni
                var rq= Volley.newRequestQueue(this)
                var sr= StringRequest(Request.Method.GET,url,
                    Response.Listener { response ->
                        if(response=="0")
                            Toast.makeText(this,"Usuario no registrado...",
                                Toast.LENGTH_LONG).show()
                        else{
                            Toast.makeText(this,"Datos modificados correctamente... Ingrese nuevamente!",
                                Toast.LENGTH_LONG).show()
                            ClsConexion.dni == ""
                            ClsConexion.nombres == ""
                            var i= Intent(this,SplashActivity::class.java)
                            startActivity(i)
                        }
                    },
                    Response.ErrorListener {  })
                rq.add(sr)
            }
        }
    }
}
