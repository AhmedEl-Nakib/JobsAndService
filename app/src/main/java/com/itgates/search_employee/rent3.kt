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

class rent3 : Fragment() {
    var spinner11: MaterialSpinner? = null
    var spinner22: MaterialSpinner? = null
    var spinner33: MaterialSpinner? = null
    var spinner44: MaterialSpinner? = null
    var search: Button? = null
    var mohafza: String? = "الوادي الجديد"
    var childgov: String? = "الوادي الجديد"
    var childcc: String? = null
    var childnn: String? = null
    var childpp: String? = null
    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null
    var progressDialog: ProgressDialog? = null
    var arrayList1: ArrayList<String?>? = null
    var arrayList2: ArrayList<String?>? = null
    var arrayList3: ArrayList<String?>? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rent3, container, false)
        search = view.findViewById(R.id.search)
        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("RentalData")
        arrayList1 = ArrayList()
        arrayList2 = ArrayList()
        arrayList3 = ArrayList()
        //        sharedHelper = new SharedHelp();
        progressDialog = ProgressDialog(activity)
        progressDialog!!.setMessage("Fetch data please wait ...")
        spinner11 = view.findViewById(R.id.gover)
        spinner11!!.setItems("الوادي الجديد", "المنيا", "المنوفيه", "مطروح", "كفر الشيخ", "قنا", "الفيوم", "شمال سيناء", "الشرقيه", "السويس", "دمياط", "الجيزه", "جنوب سيناء", "بور سعيد", "بنى سويف", "البحيره", "البحر الاحمر", "سوهاج", "الاقصر", "القاهره", "القليوبيه", "الغربية", "الإسكندرية", "الاسماعيليه", "اسوان", "الدقهلية")
        //        spinner11.setItems("القاهره", "القليوبيه", "بني سويف", "الدقهليه", "بورسعيد");
        spinner11!!.setOnItemSelectedListener(
                MaterialSpinner.OnItemSelectedListener<String?> { view, position, id, item ->
                    mohafza = item
                    childgov = item
                    progressDialog!!.show()
                    myRef!!.child(mohafza!!).addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            for (ds in dataSnapshot.children) {
                                arrayList1!!.add(ds.key)
                            }
                        }

                        override fun onCancelled(databaseError: DatabaseError) {}
                    })
                    progressDialog!!.dismiss()
                })
        spinner22 = view.findViewById(R.id.city1)
        spinner22!!.setItems(arrayList1!!)
        spinner22!!.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String?> { view, position, id, item ->
            childcc = item
            progressDialog!!.show()
            myRef!!.child(mohafza!!).child(item).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        arrayList2!!.add(ds.key)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
            progressDialog!!.dismiss()
        })
        spinner33 = view.findViewById(R.id.region)
        spinner33!!.setItems(arrayList2!!)
        spinner33!!.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String?> { view, position, id, item ->
            childnn = item
            progressDialog!!.show()
            myRef!!.child(mohafza!!).child(item).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        arrayList1!!.add(ds.key)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
            progressDialog!!.dismiss()
        })
        //        spinner44 = view. findViewById(R.id.product);
//        spinner44.setItems(arrayList3);
//        spinner44.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
//
//            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
//                childpp=item;
//            }
//        });
        search!!.setOnClickListener(View.OnClickListener { view ->
            val bundle = Bundle()
            bundle.putString("Child1Rent", childgov)
            bundle.putString("Child2Rent", childcc)
            bundle.putString("Child3Rent", childnn)
            //                bundle.putString("Child4Rent",childpp);
            Navigation.findNavController(view).navigate(R.id.action_rent3_to_rent_result, bundle)
        })
        return view
    }
}