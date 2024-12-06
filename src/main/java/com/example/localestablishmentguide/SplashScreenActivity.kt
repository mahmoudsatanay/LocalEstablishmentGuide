package com.example.localestablishmentguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.io.File

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Thread{
            goToMain()
        }.start()
    }
    fun goToMain(){
        Thread.sleep(5000)
        getUserName()



        runOnUiThread{
            findViewById<TextView>(R.id.helloMessageTextView).text=Global.globalInstance.global.userName
            findViewById<TextView>(R.id.helloMessageTextView).visibility=View.VISIBLE
            findViewById<ImageView>(R.id.logoImageView).visibility=View.GONE
        }

        Thread.sleep(2500)

        runOnUiThread{
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
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