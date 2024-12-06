package com.example.localestablishmentguide

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getUserName()
        findViewById<TextView>(R.id.mainActivityTextView).setText(com.example.localestablishmentguide.Global.globalInstance.global.userName)
    }

    val helloActivityLauncher: ActivityResultLauncher<Intent>? = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
            result->
        run {
            if (result.resultCode == RESULT_CANCELED) {
                getUserName()
            }
            else{
                com.example.localestablishmentguide.Global.globalInstance.global.saveSettings(this)
                findViewById<TextView>(R.id.mainActivityTextView).setText(com.example.localestablishmentguide.Global.globalInstance.global.userName)
            }
        }
    }

    fun getUserName(){
        val settingsFileName="Settings.txt"
        val file = File(this.getExternalFilesDir(null),settingsFileName)

        if (!file.exists()) {
            //launch hello activity for get the new user name
            helloActivityLauncher?.launch(Intent(this,HelloActivity::class.java))
        }
        else{
            com.example.localestablishmentguide.Global.globalInstance.global.loadSettings(this)
        }

    }




}