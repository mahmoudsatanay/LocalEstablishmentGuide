package com.example.localestablishmentguide

import android.app.Activity
import android.content.res.Resources.NotFoundException
import android.widget.Toast
import java.io.File

class Global {
    var userName:String=""
    val settingsFileName="Settings.txt"

    object globalInstance {
        var global:Global = Global()
    }

    fun loadSettings(activity: Activity):Unit{
        val file = File(activity.getExternalFilesDir(null),settingsFileName)

        try {
            if (file.exists())
                userName = file.readText()
            else
                throw NotFoundException("Settings file not found")
        }
        catch (e:Exception) {
            activity.runOnUiThread {
                Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    fun saveSettings(activity: Activity){
        val file = File(activity.getExternalFilesDir(null),settingsFileName)

        try {
            file.writeText(userName)
        }
        catch (e:Exception) {
            activity.runOnUiThread {
                Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}