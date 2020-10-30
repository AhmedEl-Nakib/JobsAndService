package com.itgates.search_employee

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var bottomNavigationView: BottomNavigationView? = null
    var navController: NavController? = null
    var sharedpreferences: SharedPreferences? = null

    //    SharedPreferences.Editor editor;
    var Uid: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.bottom)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottomNavigationView!!, navController!!)
        sharedpreferences = getSharedPreferences("JS", MODE_PRIVATE)
        //        editor = sharedpreferences.edit();
        Uid = sharedpreferences!!.getString("Uid", "")
        Log.i("UidHome", Uid)
    }
}