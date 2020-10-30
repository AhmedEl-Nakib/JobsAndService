package com.itgates.search_employee

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jaredrummler.materialspinner.MaterialSpinner
import java.util.*

import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.itgates.search_employee.model
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.itgates.search_employee.MyAdapter
import com.itgates.search_employee.MyModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.constraintlayout.widget.ConstraintLayout
import com.itgates.search_employee.Adapter_rent
import com.itgates.search_employee.Model_rent
import com.itgates.search_employee.Login
import com.itgates.search_employee.log
import com.squareup.picasso.Picasso
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.itgates.search_employee.adapter
import com.itgates.search_employee.ProductModel
import com.itgates.search_employee.ProductRecyclerAdapter
import com.itgates.search_employee.ProductRecyclerAdapter.productRecyclerAdapter

class Add : Fragment() {
    var button3: Button? = null
    var city: EditText? = null
    var neighborhood: EditText? = null
    var place: EditText? = null
    var description: EditText? = null
    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null
    var progressDialog: ProgressDialog? = null
    var spinner: MaterialSpinner? = null
    var spinner2: MaterialSpinner? = null
    var spinner3: MaterialSpinner? = null
    var categoreSelected: String? = "مطاعم"
    var governate: String? = "الوادي الجديد"
    var sharedpreferences: SharedPreferences? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_add, container, false)
        button3 = view.findViewById(R.id.button3)
        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("data")
        progressDialog = ProgressDialog(activity)
        neighborhood = view.findViewById(R.id.textInputLayout31)
        description = view.findViewById(R.id.textInputLayout1)
        place = view.findViewById(R.id.textInputLayout21)
        city = view.findViewById(R.id.governateEdit1)
        sharedpreferences = requireActivity().getSharedPreferences("JS", Context.MODE_PRIVATE)
        spinner = view.findViewById(R.id.spinner3)
        spinner!!.setItems("مطاعم", "مولات", "صيدلبات", "محلات ملابي", "عطاره")
        spinner!!.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String?> { view, position, id, item ->
            Snackbar.make(view, "Clicked $item", Snackbar.LENGTH_LONG).show()
            categoreSelected = item
        })
        spinner2 = view.findViewById(R.id.spinner2)
        spinner!!.setItems("الوادي الجديد", "المنيا", "المنوفيه", "مطروح", "كفر الشيخ", "قنا", "الفيوم", "شمال سيناء", "الشرقيه", "السويس", "دمياط", "الجيزه", "جنوب سيناء", "بور سعيد", "بنى سويف", "البحيره", "البحر الاحمر", "سوهاج", "الاقصر", "القاهره", "القليوبيه", "الغربية", "الإسكندرية", "الاسماعيليه", "اسوان", "الدقهلية")
        spinner2!!.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String?> { view, position, id, item ->
            Snackbar.make(view, "Clicked $item", Snackbar.LENGTH_LONG).show()
            governate = item
        })
        //        spinner3 = view. findViewById(R.id.spinner3);
//        spinner3.setItems("Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow");
//        spinner3.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//
//            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
//            }
//        });
        button3!!.setOnClickListener(View.OnClickListener {
            progressDialog!!.show()
            val map: MutableMap<String?, String?> = HashMap()
            map["neighborhood"] = neighborhood!!.getText().toString()
            map["place"] = place!!.getText().toString()
            map["description"] = description!!.getText().toString()
            map["city"] = city!!.getText().toString()
            map["governate"] = governate
            map["category"] = categoreSelected
            map["Uid"] = sharedpreferences!!.getString("Uid", "")
            myRef!!.child(categoreSelected!!).child(governate!!)
                    .child(city!!.getText().toString())
                    .child(neighborhood!!.getText().toString()).push().setValue(map)
            progressDialog!!.dismiss()
            Toast.makeText(activity, "insert succses", Toast.LENGTH_SHORT).show()
        })
        return view
    }
}