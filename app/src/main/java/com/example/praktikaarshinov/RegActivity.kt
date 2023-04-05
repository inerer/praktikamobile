package com.example.praktikaarshinov

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.praktikaarshinov.model.User
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class RegActivity : AppCompatActivity() {

    val newUser = User()
    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            isGranted ->
        }

        var birthdayEditText:EditText = findViewById(R.id.editTextDate2)
        var genderMass = resources.getStringArray(R.array.genders)
        val spinner:Spinner = findViewById(R.id.genderSpinner)
        if(spinner!=null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderMass)
            spinner.adapter = adapter
        }
        val regButton:Button = findViewById(R.id.regButton)
        val nameEditText:EditText = findViewById(R.id.editTextTextPersonName)
        val surnameEditText:EditText = findViewById(R.id.editTextLastName)
        val patronymicEditText:EditText =  findViewById(R.id.editTextMiddleName)
        val passwordEditText:EditText = findViewById(R.id.passwordEditText)
        val loginEditText:EditText = findViewById(R.id.loginEditText)
        val emailEditText:EditText = findViewById(R.id.emailEditText)

        regButton.setOnClickListener{
            newUser.UserLogin = loginEditText.text.toString()
            newUser.UserRole = "1"
            newUser.UserGender = spinner.selectedItemPosition.toString()
            newUser.UserBithday = birthdayEditText.text.toString()
            newUser.UserPhoto = ""
            newUser.UserPassword = passwordEditText.text.toString()
            newUser.UserMiddleName = surnameEditText.text.toString()
            newUser.UserFirstName = nameEditText.text.toString()
            newUser.UserLastName = patronymicEditText.text.toString()
            newUser.UserEmail = emailEditText.text.toString()
            Thread(Runnable {
                val connectionString = resources.getString(R.string.connectionString)
                val connection = URL(connectionString+"/api/User/create").openConnection() as HttpURLConnection
                connection.requestMethod = "POST"
                val gson = Gson()
                val requestParameter = gson.toJson(newUser)
                connection.setRequestProperty("Content-Type","application/json")
                val outputStream: OutputStream = connection.outputStream
                val outputWriter = OutputStreamWriter(outputStream)
                outputWriter.write(requestParameter)
                outputWriter.flush()
                outputWriter.close()
                println(connection.responseMessage)
                if(connection.responseCode!=200){
                    runOnUiThread{
                        Toast.makeText(
                            this@RegActivity,
                            connection.responseMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    if(connection.responseCode==200){
                        val inputStream = BufferedReader(InputStreamReader(connection.inputStream)).use {
                            val response = StringBuffer()
                            var inputLine = it.readLine()
                            while(inputLine != null){
                                response.append(inputLine)
                                inputLine = it.readLine()
                            }
                            it.close()
                            println(">>>> Response: $response")
                        }
                        runOnUiThread{
                            Toast.makeText(
                                this@RegActivity,
                                "Пользователь добавлен",
                                Toast.LENGTH_LONG
                            ).show()
                            onBackPressed()
                        }
                    }
                    connection.disconnect()
                }
            }).start()
        }
    }
}