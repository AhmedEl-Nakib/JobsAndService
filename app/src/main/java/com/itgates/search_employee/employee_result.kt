package com.itgates.search_employee

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.*

class employee_result : Fragment() {
    var recyclerView: RecyclerView? = null
    var recycleAdapter: adapter? = null
    var progressDialog: ProgressDialog? = null
    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null
    var arrayList: ArrayList<model?>? = null
    var childg: String? = null
    var childc: String? = null
    var childn: String? = null
    var childj: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_employee_result, container, false)
        recyclerView = view.findViewById(R.id.res)
        arrayList = ArrayList()
        progressDialog = ProgressDialog(activity)
        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("Jop")
        //        sharedHelper = new SharedHelp();
        childg = requireArguments().getString("Child1Job")
        childc = requireArguments().getString("Child2Job")
        childn = requireArguments().getString("Child3Job")
        childj = requireArguments().getString("Child4Job")
        progressDialog!!.show()
        myRef!!.child(childg!!).child(childc!!).child(childn!!).child(childj!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val name = ds.child("name").value.toString()
                    val phone = ds.child("phone").value.toString()
                    val salary = ds.child("salary").value.toString()
                    val mym = model("Name : $name", "Phone : $phone", "Salary : $salary")
                    arrayList!!.add(mym)
                }
                recycleAdapter = adapter(activity, arrayList)
                recyclerView!!.setLayoutManager(LinearLayoutManager(activity))
                recyclerView!!.setAdapter(recycleAdapter)
                progressDialog!!.dismiss()
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        return view
    }
}