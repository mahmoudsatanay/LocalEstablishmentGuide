package com.example.localestablishmentguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class HelloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        findViewById<Button>(R.id.saveUsernameButton).setOnClickListener{
            val editTextRef=findViewById<EditText>(R.id.editText)

            if(editTextRef.text.toString().length==0)
                Toast.makeText(this,"Введите своё имя!",Toast.LENGTH_LONG).show()
            else {
                setResult(RESULT_OK,Intent().putExtra("userName",editTextRef.text.toString()))
                Global.globalInstance.global.userName=editTextRef.text.toString()
                finish()
            }
        }
    }
}