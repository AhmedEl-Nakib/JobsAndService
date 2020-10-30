package com.itgates.search_employee

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.firebase.database.*
import com.jaredrummler.materialspinner.MaterialSpinner
import java.util.*

class search_employee : Fragment() {
    var spinner4: MaterialSpinner? = null
    var spinner5: MaterialSpinner? = null
    var spinner6: MaterialSpinner? = null
    var spinner7: MaterialSpinner? = null
    var button4: Button? = null
    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null
    var progressDialog: ProgressDialog? = null
    var arrayList1: ArrayList<String?>? = null
    var arrayList2: ArrayList<String?>? = null
    var arrayList3: ArrayList<String?>? = null
    var gove = "الوادي الجديد"
    var childg = "الوادي الجديد"
    var childc: String? = null
    var childn: String? = null
    var childj: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search_employee, container, false)
        button4 = view.findViewById(R.id.button44)
        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("Jop")
        arrayList1 = ArrayList()
        arrayList2 = ArrayList()
        arrayList3 = ArrayList()
        //        sharedHelper = new SharedHelp();
        progressDialog = ProgressDialog(activity)
        progressDialog!!.setMessage("Fetch data please wait ...")
        spinner4 = view.findViewById(R.id.spinner41)
        spinner4!!.setItems("الوادي الجديد", "المنيا", "المنوفيه", "مطروح", "كفر الشيخ", "قنا", "الفيوم", "شمال سيناء", "الشرقيه", "السويس", "دمياط", "الجيزه", "جنوب سيناء", "بور سعيد", "بنى سويف", "البحيره", "البحر الاحمر", "سوهاج", "الاقصر", "القاهره", "القليوبيه", "الغربية", "الإسكندرية", "الاسماعيليه", "اسوان", "الدقهلية")
        //        spinner4.setItems("القاهره", "القليوبيه", "بني سويف", "الدقهليه", "بورسعيد");
        spinner4!!.setOnItemSelectedListener(
                MaterialSpinner.OnItemSelectedListener<String> { view, position, id, item ->
                    gove = item
                    childg = item
                    progressDialog!!.show()
                    myRef!!.child(gove).addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            for (ds in dataSnapshot.children) {
                                arrayList1!!.add(ds.key)
                            }
                        }

                        override fun onCancelled(databaseError: DatabaseError) {}
                    })
                    progressDialog!!.dismiss()
                })
        spinner5 = view.findViewById(R.id.spinner51)
        spinner5!!.setItems(arrayList1!!)
        spinner5!!.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String?> { view, position, id, item ->
            childc = item
            progressDialog!!.show()
            myRef!!.child(gove).child(item!!).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        arrayList2!!.add(ds.key)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
            progressDialog!!.dismiss()
        })
        spinner6 = view.findViewById(R.id.spinner61)
        spinner6!!.setItems(arrayList2!!)
        spinner6!!.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String?> { view, position, id, item ->
            childn = item
            progressDialog!!.show()
            myRef!!.child(gove).child(childc!!).child(item!!).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        arrayList3!!.add(ds.key)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
            progressDialog!!.dismiss()
        })
        spinner7 = view.findViewById(R.id.spinner71)
        spinner7!!.setItems(arrayList3!!)
        spinner7!!.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String?> { view, position, id, item -> childj = item })
        button4!!.setOnClickListener(View.OnClickListener { view ->
            val bundle = Bundle()
            bundle.putString("Child1Job", childg)
            bundle.putString("Child2Job", childc)
            bundle.putString("Child3Job", childn)
            bundle.putString("Child4Job", childj)
            Navigation.findNavController(view).navigate(R.id.action_search_employee_to_employee_result, bundle)
        })
        return view
    }
}