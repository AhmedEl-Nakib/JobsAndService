package com.itgates.search_employee

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    var topanimation: Animation? = null
    var bottomanimation: Animation? = null
    var imageView: ImageView? = null
    var welcome: TextView? = null
    var J_s: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        topanimation = AnimationUtils.loadAnimation(this, R.anim.top_animator)
        bottomanimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animator)
        imageView = findViewById(R.id.imageView)
        welcome = findViewById(R.id.textView2)
        J_s = findViewById(R.id.textView3)
        imageView!!.setAnimation(topanimation)
        welcome!!.setAnimation(bottomanimation)
        J_s!!.setAnimation(bottomanimation)
        Handler().postDelayed({
            val intent = Intent(applicationContext, ActivityAuth::class.java)
            startActivity(intent)
            finish()
        }, splash_screen.toLong())
    }

    companion object {
        private const val splash_screen = 5000
    }
}