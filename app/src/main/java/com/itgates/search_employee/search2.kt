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

class search2 : Fragment() {
    var recyclerView: RecyclerView? = null
    var recycleAdapter: MyAdapter? = null
    var progressDialog: ProgressDialog? = null
    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null
    private var arrayList: ArrayList<MyModel?>? = null
    var child1: String? = null
    var child2: String? = null
    var child3: String? = null
    var child4: String? = null

    //    SharedHelp sharedHelper;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_search2, container, false)
        recyclerView = view.findViewById(R.id.res)
        arrayList = ArrayList()
        progressDialog = ProgressDialog(activity)
        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("data")
        //        sharedHelper = new SharedHelp();
        child1 = requireArguments().getString("Child1Categore")
        child2 = requireArguments().getString("Child2Categore")
        child3 = requireArguments().getString("Child3Categore")
        child4 = requireArguments().getString("Child4Categore")
        progressDialog!!.show()
        myRef!!.child(child1!!).child(child2!!).child(child3!!).child(child4!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val place = ds.child("place").value.toString()
                    val description = ds.child("description").value.toString()
                    val Category = ds.child("category").value.toString()
                    val Uid = ds.child("Uid").value.toString()
                    val mym = MyModel("Place : $place", "Description : $description", "Category : $Category", Uid)
                    arrayList!!.add(mym)
                }
                recycleAdapter = MyAdapter(activity, arrayList)
                recyclerView!!.setLayoutManager(LinearLayoutManager(activity))
                recyclerView!!.setAdapter(recycleAdapter)
                progressDialog!!.dismiss()
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        return view
    }
}