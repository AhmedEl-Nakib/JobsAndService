package com.itgates.search_employee

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class AddProductFragment : Fragment() {
    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null
    private var mStorageRef: StorageReference? = null
    var imageView: ImageView? = null
    var imgUri: Uri? = null
    var name: EditText? = null
    var price: EditText? = null
    var uploadBtn: Button? = null
    var sharedpreferences: SharedPreferences? = null
    var progressDialog: ProgressDialog? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)
        mStorageRef = FirebaseStorage.getInstance().reference
        database = FirebaseDatabase.getInstance()
        name = view.findViewById(R.id.productNameId)
        price = view.findViewById(R.id.productPriceId)
        uploadBtn = view.findViewById(R.id.uploadBtn)
        myRef = database!!.getReference("Products")
        imageView = view.findViewById(R.id.imgUpload)
        sharedpreferences = requireActivity().getSharedPreferences("JS", Context.MODE_PRIVATE)
        progressDialog = ProgressDialog(activity)
        progressDialog!!.setMessage("Uploading please wait")
        imageView!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        })
        uploadBtn!!.setOnClickListener(View.OnClickListener {
            if (imgUri == null) {
                Toast.makeText(activity, "Please select image !", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    progressDialog!!.show()
                    val ref = mStorageRef!!.child("Photos/" + System.currentTimeMillis())
                    ref.putFile(imgUri!!).addOnCompleteListener {
                        ref.downloadUrl.addOnSuccessListener { uri ->
                            val map: MutableMap<String?, String?> = HashMap()
                            map["imgUrl"] = uri.toString()
                            map["productName"] = name!!.getText().toString()
                            map["productPrice"] = price!!.getText().toString()
                            map["Uid"] = sharedpreferences!!.getString("Uid", "")
                            myRef!!.child(sharedpreferences!!.getString("Uid", "")!!).push().setValue(map)
                            progressDialog!!.dismiss()
                            Toast.makeText(activity, "Uploaded Success", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    Toast.makeText(activity, "Exception " + e.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            imgUri = data!!.getData()
            imageView!!.setImageURI(imgUri)
        }
    }
}