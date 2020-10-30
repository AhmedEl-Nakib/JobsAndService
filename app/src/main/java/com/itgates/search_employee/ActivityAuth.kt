package com.itgates.search_employee

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo

class ActivityAuth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    fun login(view: View?) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        Animatoo.animateZoom(this) //fire the zoom animation
    }

    fun Log(view: View?) {
        val intent2 = Intent(this, log::class.java)
        startActivity(intent2)
        Animatoo.animateZoom(this)
    }
}