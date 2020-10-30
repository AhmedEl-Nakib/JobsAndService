package com.itgates.search_employee

import android.app.ProgressDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.firebase.auth.FirebaseAuth
import com.itgates.search_employee.Login

class Login : AppCompatActivity() {
    var name: EditText? = null
    var phone: EditText? = null
    var email: EditText? = null
    var password: EditText? = null
    private var mAuth: FirebaseAuth? = null
    var progressDialog: ProgressDialog? = null
    var sharedpreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("please wait")
        name = findViewById(R.id.filledTextField)
        phone = findViewById(R.id.filledTextField2)
        email = findViewById(R.id.filledTextField3)
        password = findViewById(R.id.filled2TextField)
        sharedpreferences = getSharedPreferences("JS", MODE_PRIVATE)
        editor = sharedpreferences!!.edit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Animatoo.animateSlideLeft(this) //fire the slide left animation
    }

    fun signup(view: View?) {
        if (name!!.getText().toString().isEmpty()) {
            name!!.setError("please enter name")
        }
        if (phone!!.getText().toString().isEmpty()) {
            phone!!.setError("please enter phone")
        }
        if (email!!.getText().toString().isEmpty()) {
            email!!.setError("please enter email")
        }
        if (password!!.getText().toString().isEmpty()) {
            password!!.setError("please enter password")
        } else {
            progressDialog!!.show()
            mAuth!!.createUserWithEmailAndPassword(email!!.getText().toString(), password!!.getText().toString()).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    mAuth!!.getCurrentUser()!!.sendEmailVerification()
                    progressDialog!!.dismiss()
                    Toast.makeText(this@Login, "Success", Toast.LENGTH_SHORT).show()
                } else {
                    progressDialog!!.dismiss()
                    Toast.makeText(this@Login, task.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}