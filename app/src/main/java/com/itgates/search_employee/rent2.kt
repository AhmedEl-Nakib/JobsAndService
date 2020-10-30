package com.itgates.search_employee

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
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
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.jaredrummler.materialspinner.MaterialSpinner
import java.util.*

class rent2 : Fragment() {
    var addrent: Button? = null
    var city: EditText? = null
    var neighborhood: EditText? = null
    var nameperson: EditText? = null
    var phoneperson: EditText? = null
    var nameproduct: EditText? = null
    var description: EditText? = null
    var price: EditText? = null
    var imageproduct: ImageView? = null
    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null
    var progressDialog: ProgressDialog? = null
    var spinnerrent: MaterialSpinner? = null
    var governate: String? = "الوادي الجديد"
    var imgUri: Uri? = null
    private var mStorageRef: StorageReference? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_rent2, container, false)
        addrent = view.findViewById(R.id.addrent)
        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("RentalData")
        progressDialog = ProgressDialog(activity)
        mStorageRef = FirebaseStorage.getInstance().reference
        city = view.findViewById(R.id.city1)
        imageproduct = view.findViewById(R.id.imgProductId)
        neighborhood = view.findViewById(R.id.region1)
        nameperson = view.findViewById(R.id.person1)
        phoneperson = view.findViewById(R.id.phone1)
        nameproduct = view.findViewById(R.id.nameproduct1)
        description = view.findViewById(R.id.description1)
        price = view.findViewById(R.id.price1)
        imageproduct!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        })
        spinnerrent = view.findViewById(R.id.spinnerr1)
        spinnerrent!!.setItems("الوادي الجديد", "المنيا", "المنوفيه", "مطروح", "كفر الشيخ", "قنا", "الفيوم", "شمال سيناء", "الشرقيه", "السويس", "دمياط", "الجيزه", "جنوب سيناء", "بور سعيد", "بنى سويف", "البحيره", "البحر الاحمر", "سوهاج", "الاقصر", "القاهره", "القليوبيه", "الغربية", "الإسكندرية", "الاسماعيليه", "اسوان", "الدقهلية")
        //        spinnerrent.setItems("القاهره", "القليوبيه", "بني سويف", "الدقهليه", "بورسعيد");
//
        spinnerrent!!.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String?> { view, position, id, item ->
            Snackbar.make(view, "Clicked $item", Snackbar.LENGTH_LONG).show()
            governate = item
        })
        addrent!!.setOnClickListener(View.OnClickListener {
            progressDialog!!.show()
            val ref = mStorageRef!!.child("Photos/" + System.currentTimeMillis())
            ref.putFile(imgUri!!).addOnCompleteListener {
                ref.downloadUrl.addOnSuccessListener { uri ->
                    val map: MutableMap<String?, String?> = HashMap()
                    map["governate"] = governate
                    map["city"] = city!!.getText().toString()
                    map["neighborhood"] = neighborhood!!.getText().toString()
                    map["personeName"] = nameperson!!.getText().toString()
                    map["personePhone"] = phoneperson!!.getText().toString()
                    map["productName"] = nameproduct!!.getText().toString()
                    map["description"] = description!!.getText().toString()
                    map["price"] = price!!.getText().toString()
                    map["imgUrl"] = uri.toString()
                    myRef!!.child(governate!!)
                            .child(city!!.getText().toString())
                            .child(neighborhood!!.getText().toString()).push().setValue(map)
                    progressDialog!!.dismiss()
                    Toast.makeText(activity, "insert succses", Toast.LENGTH_SHORT).show()
                }
            }
        })
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            imgUri = data!!.getData()
            imageproduct!!.setImageURI(imgUri)
        }
    }
}