package com.itgates.search_employee

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.firebase.auth.FirebaseAuth
import com.itgates.search_employee.log

class log : AppCompatActivity() {
    var email: EditText? = null
    var password: EditText? = null
    private var mAuth: FirebaseAuth? = null
    var progressDialog: ProgressDialog? = null
    var sharedpreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        mAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("please wait")
        email = findViewById(R.id.filledTextFieldd)
        password = findViewById(R.id.filled2TextFieldd2)
        sharedpreferences = getSharedPreferences("JS", MODE_PRIVATE)
        editor = sharedpreferences!!.edit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Animatoo.animateSlideLeft(this) //fire the slide left animation
    }

    fun loggin(view: View?) {
        if (email!!.getText().toString().isEmpty()) {
            email!!.setError("please enter email")
        }
        if (password!!.getText().toString().isEmpty()) {
            password!!.setError("please enter password")
        } else {
            progressDialog!!.show()
            mAuth!!.signInWithEmailAndPassword(email!!.getText().toString(), password!!.getText().toString()).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (mAuth!!.getCurrentUser()!!.isEmailVerified()) {
                        progressDialog!!.dismiss()
                        Toast.makeText(this@log, "Login Success", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        editor!!.putString("Uid", mAuth!!.getCurrentUser()!!.getUid())
                        editor!!.commit()
                        Log.i("UidLogin", mAuth!!.getCurrentUser()!!.getUid())
                    } else {
                        progressDialog!!.dismiss()
                        Toast.makeText(this@log, "check in box", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    progressDialog!!.dismiss()
                    Toast.makeText(this@log, task.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}