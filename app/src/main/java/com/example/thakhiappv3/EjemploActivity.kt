package com.example.thakhiappv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.thakhiappv3.Conexion.ClsConexion
import java.util.*

class EjemploActivity : AppCompatActivity() {

    //lateinit var cls : UbicacionActivity

    private lateinit var mRunnable:Runnable
    private lateinit var mHandler: Handler
    private lateinit var mRandom1: Random
    private lateinit var mRandom2: Random

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejemplo)

        //cls = new UbicacionActivity()
        mRandom1 = Random()
        mRandom2 = Random()
        mHandler = Handler()

        var lat : Double = 0.0
        var lon : Double = 0.0

        mRunnable = Runnable {
            // Do something here
            lat = (mRandom1.nextInt(100)).toDouble()
            lon = (mRandom2.nextInt(100)).toDouble()
            Toast.makeText(this,
                "Random Number : ${lat}, ${lon}",
                Toast.LENGTH_LONG).show()

            GuardarUbicacion("45454545",lat,lon)
            // Schedule the task to repeat after 1 second
            mHandler.postDelayed(
                mRunnable, // Runnable
                10000 // Delay in milliseconds
            )

        }
        mHandler.postDelayed(
            mRunnable, // Runnable
            5000 // Delay in milliseconds
        )
    }

    fun GuardarUbicacion (dni: String,lat: Double,lon: Double){
        //codigo para guardar la latitud y longitud en la base de datos
        var url = ClsConexion.url + "CapturarUbicacion.php?CLIlatitud=" + lat + "&CLIlongitud=" + lon + "&CLIdni=" + dni
        var rq = Volley.newRequestQueue(this)
        var sr = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                Toast.makeText(this,"Ubicacion guardada!",Toast.LENGTH_LONG).show()
            },
            Response.ErrorListener { })
        rq.add(sr)
    }
}
