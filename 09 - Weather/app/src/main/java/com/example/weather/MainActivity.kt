package com.example.weather

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val resp = findViewById<TextView>(R.id.textView)
        val btn = findViewById<Button>(R.id.button)
        val lat = findViewById<EditText>(R.id.editTextNumberDecimal)
        val lon = findViewById<EditText>(R.id.editTextNumberDecimal2)




        btn.setOnClickListener {
            if (!lat.text.isEmpty() && !lon.text.isEmpty()) {
                Thread {
                    runOnUiThread {
                        resp.text = "REFRESHING"
                    }
                    for (i in 1..5) {
                        runOnUiThread {
                            resp.text = resp.text.toString() + " ."
                        }
                        Thread.sleep(300)
                    }
                    fetchweatherdata(
                        resp,
                        lat.text.toString().toDouble(),
                        lon.text.toString().toDouble()
                    )
                }.start()
            } else {
                resp.text = "PLEASE ENTER LATITUDE AND LONGITUDE"
            }
        }


    }

    fun fetchweatherdata(resp: TextView, latitude: Double, longitude: Double) {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val weatherapi = retrofit.create(Weatherapi::class.java)

        val call = weatherapi.getWeather(
            latitude = latitude,
            longitude = longitude,
            currentWeather = true,
            hourly = "temperature_2m,relative_humidity_2m,wind_speed_10m"
        )

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val weatherData = response.body()
                    Log.d("TAG", "Response: $weatherData") // Log response
                    resp.text = "TEMPERATURE = ${weatherData?.current_weather?.temperature}\n" +
                            "IS DAY = ${weatherData?.current_weather?.is_day}\n" +
                            "TIME = ${weatherData?.current_weather?.time}\n" +
                            "WIND DIRECTION = ${weatherData?.current_weather?.wind_direction}\n" +
                            "WEATHER CODE = ${weatherData?.current_weather?.weathercode}\n"

                } else {
                    Log.e("TAG", "Error Code: ${response.code()}")
                    resp.text = "${response.code()} - ${response.message()}"
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("TAG", "Failure: ${t.message}")
                resp.text = t.message
            }
        })
    }
}

interface Weatherapi {
    @GET("forecast")
    fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current_weather") currentWeather: Boolean,
        @Query("hourly") hourly: String
    ): Call<WeatherResponse>
}

data class WeatherResponse(
    val current_weather: CurrentWeather?
)

data class CurrentWeather(
    val temperature: Double?,
    val wind_speed: Double?,
    val wind_direction: Int?,
    val weathercode: Int?,
    val is_day: Int?,
    val time: String?
)




