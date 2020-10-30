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

class rent_result : Fragment() {
    var recyclerViewrent: RecyclerView? = null
    var recycleAdapterrent: Adapter_rent? = null
    var progressDialog: ProgressDialog? = null
    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null
    var arrayList: ArrayList<Model_rent?>? = null
    var childgov: String? = null
    var childcc: String? = null
    var childnn: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_rent_result, container, false)
        recyclerViewrent = view.findViewById(R.id.rentrecycle)
        arrayList = ArrayList()
        progressDialog = ProgressDialog(activity)
        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("RentalData")
        assert(arguments != null)
        childgov = requireArguments().getString("Child1Rent")
        childcc = requireArguments().getString("Child2Rent")
        childnn = requireArguments().getString("Child3Rent")
        //        childpp=  getArguments().getString("Child4Rent");
        progressDialog!!.show()
        myRef!!.child(childgov!!).child(childcc!!).child(childnn!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    val productname = ds.child("productName").value.toString()
                    val description = ds.child("description").value.toString()
                    val price = ds.child("price").value.toString()
                    val imgUrl = ds.child("imgUrl").value.toString()
                    val personeName = ds.child("personeName").value.toString()
                    val personePhone = ds.child("personePhone").value.toString()
                    //                    Model_rent model_rent=new Model_rent("Product_name : "+productname,"Description : "+description,"price: "+price , imgUrl);
                    val model_rent = Model_rent(imgUrl, "Product_name : $productname", "Description : $description", "price: $price", personeName, personePhone)
                    arrayList!!.add(model_rent)
                }
                recycleAdapterrent = Adapter_rent(activity, arrayList)
                recyclerViewrent!!.setLayoutManager(LinearLayoutManager(activity))
                recyclerViewrent!!.setAdapter(recycleAdapterrent)
                progressDialog!!.dismiss()
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        return view
    }
}