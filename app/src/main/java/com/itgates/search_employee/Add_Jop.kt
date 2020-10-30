package com.itgates.search_employee

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jaredrummler.materialspinner.MaterialSpinner
import java.util.*

class Add_Jop : Fragment() {
    var name: EditText? = null
    var title: EditText? = null
    var description: EditText? = null
    var phone: EditText? = null
    var salary: EditText? = null
    var country: EditText? = null
    var governorate: EditText? = null
    var neighporhood: EditText? = null
    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null
    var progressDialog: ProgressDialog? = null
    var button: Button? = null
    var spinner: MaterialSpinner? = null
    var selectedItemSpinner: String? = "الوادي الجديد"
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add__jop, container, false)
        spinner = view.findViewById(R.id.spinner1)
        //        spinner.setItems("القاهره", "القليوبيه", "بني سويف", "الدقهليه", "بورسعيد");
        spinner!!.setItems("الوادي الجديد", "المنيا", "المنوفيه", "مطروح", "كفر الشيخ", "قنا", "الفيوم", "شمال سيناء", "الشرقيه", "السويس", "دمياط", "الجيزه", "جنوب سيناء", "بور سعيد", "بنى سويف", "البحيره", "البحر الاحمر", "سوهاج", "الاقصر", "القاهره", "القليوبيه", "الغربية", "الإسكندرية", "الاسماعيليه", "اسوان", "الدقهلية")
        spinner!!.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String?> { view, position, id, item -> //                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            selectedItemSpinner = item
        })
        name = view.findViewById(R.id.textInputLayout61)
        country = view.findViewById(R.id.textInputLayout111)
        neighporhood = view.findViewById(R.id.textInputLayout121)
        governorate = view.findViewById(R.id.textInputLayout101)
        phone = view.findViewById(R.id.textInputLayout71)
        title = view.findViewById(R.id.textInputLayout81)
        salary = view.findViewById(R.id.textInputLayout131)
        description = view.findViewById(R.id.textInputLayout91)
        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("Jop")
        button = view.findViewById(R.id.button3)
        progressDialog = ProgressDialog(activity)
        button!!.setOnClickListener(View.OnClickListener {
            progressDialog!!.show()
            val map: MutableMap<String?, String?> = HashMap()
            map["name"] = name!!.getText().toString()
            map["title"] = title!!.getText().toString()
            map["description"] = description!!.getText().toString()
            map["governate"] = governorate!!.getText().toString()
            map["country"] = country!!.getText().toString()
            map["phone"] = phone!!.getText().toString()
            map["salary"] = salary!!.getText().toString()
            map["neighporhood"] = neighporhood!!.getText().toString()
            myRef!!.child(selectedItemSpinner!!)
                    .child(country!!.getText().toString())
                    .child(neighporhood!!.getText().toString())
                    .child(title!!.getText().toString())
                    .push()
                    .setValue(map)
            progressDialog!!.dismiss()
            Toast.makeText(activity, "insert sucses", Toast.LENGTH_SHORT).show()
        })
        return view
    }
}