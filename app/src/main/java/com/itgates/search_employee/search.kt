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

class search : Fragment() {
    var spinner4: MaterialSpinner? = null
    var spinner5: MaterialSpinner? = null
    var spinner6: MaterialSpinner? = null
    var spinner7: MaterialSpinner? = null
    var button4: Button? = null
    var categorei: String? = "مطاعم"
    var mohafza: String? = "الوادي الجديد"
    var child1: String? = "مطاعم"
    var child2: String? = "الوادي الجديد"
    var child3: String? = null
    var child4: String? = null
    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null
    var progressDialog: ProgressDialog? = null
    var arrayList1: ArrayList<String?>? = null
    var arrayList2: ArrayList<String?>? = null

    //    SharedHelp sharedHelper;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        button4 = view.findViewById(R.id.button4)
        spinner4 = view.findViewById(R.id.spinner4)
        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("data")
        arrayList1 = ArrayList()
        arrayList2 = ArrayList()
        //        sharedHelper = new SharedHelp();
        progressDialog = ProgressDialog(activity)
        progressDialog!!.setMessage("Fetch data please wait ...")
        spinner4!!.setItems("مطاعم", "مولات", "صيدلبات", "محلات ملابي", "عطاره")
        spinner4!!.setOnItemSelectedListener(
                MaterialSpinner.OnItemSelectedListener<String?> { view, position, id, item ->
                    categorei = item
                    child1 = item
                })
        spinner5 = view.findViewById(R.id.spinner5)
        spinner5!!.setItems("الوادي الجديد", "المنيا", "المنوفيه", "مطروح", "كفر الشيخ", "قنا", "الفيوم", "شمال سيناء", "الشرقيه", "السويس", "دمياط", "الجيزه", "جنوب سيناء", "بور سعيد", "بنى سويف", "البحيره", "البحر الاحمر", "سوهاج", "الاقصر", "القاهره", "القليوبيه", "الغربية", "الإسكندرية", "الاسماعيليه", "اسوان", "الدقهلية")
        //        spinner5.setItems("القاهره", "القليوبيه", "بني سويف", "الدقهليه", "بورسعيد");
        spinner5!!.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String?> { view, position, id, item ->
            mohafza = item
            child2 = item
            progressDialog!!.show()
            myRef!!.child(categorei!!).child(mohafza!!).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        arrayList1!!.add(ds.key)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
            progressDialog!!.dismiss()
        })
        spinner6 = view.findViewById(R.id.spinner6)
        spinner6!!.setItems(arrayList1!!)
        spinner6!!.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String?> { view, position, id, item -> //                countary = item;
            child3 = item
            progressDialog!!.show()
            myRef!!.child(categorei!!).child(mohafza!!).child(item).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        arrayList2!!.add(ds.key)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
            progressDialog!!.dismiss()
        })
        spinner7 = view.findViewById(R.id.spinner7)
        spinner7!!.setItems(arrayList2)
        spinner7!!.setOnItemSelectedListener(MaterialSpinner.OnItemSelectedListener<String?> { view, position, id, item -> child4 = item })
        button4!!.setOnClickListener(View.OnClickListener { view -> //                sharedHelper.putKey(getActivity(),"Child1Categore",child1);
//                sharedHelper.putKey(getActivity(),"Child2Categore",child2);
//                sharedHelper.putKey(getActivity(),"Child3Categore",child3);
//                sharedHelper.putKey(getActivity(),"Child4Categore",child4);
            val bundle = Bundle()
            bundle.putString("Child1Categore", child1)
            bundle.putString("Child2Categore", child2)
            bundle.putString("Child3Categore", child3)
            bundle.putString("Child4Categore", child4)
            Navigation.findNavController(view).navigate(R.id.action_search3_to_search2, bundle)
        })
        return view
    }
}