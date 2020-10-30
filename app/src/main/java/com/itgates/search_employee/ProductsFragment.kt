package com.itgates.search_employee

import android.app.ProgressDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.*

class ProductsFragment : Fragment() {
    var progressDialog: ProgressDialog? = null
    var database: FirebaseDatabase? = null
    var myRef: DatabaseReference? = null
    var sharedpreferences: SharedPreferences? = null
    var productModels: ArrayList<ProductModel?>? = null
    var productRecyclerAdapter: ProductRecyclerAdapter? = null
    var recyclerView: RecyclerView? = null
    var uid: String? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_products, container, false)
        recyclerView = view.findViewById(R.id.rentrecycle)
        sharedpreferences = activity!!.getSharedPreferences("JS", Context.MODE_PRIVATE)
        productModels = ArrayList()
        progressDialog = ProgressDialog(activity)
        progressDialog!!.setMessage("Get data please wait ...")
        progressDialog!!.show()
        productRecyclerAdapter = ProductRecyclerAdapter(activity, productModels)
        recyclerView!!.setAdapter(productRecyclerAdapter)
        database = FirebaseDatabase.getInstance()
        uid = requireArguments().getString("UidForSearch")
        Log.i("Uid", sharedpreferences!!.getString("UidForSearch", ""))
        try {
            myRef = database!!.getReference("Products").child(uid!!)
            myRef!!.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        val img = ds.child("imgUrl").value.toString()
                        val title = ds.child("productName").value.toString()
                        val price = ds.child("productPrice").value.toString()
                        productModels!!.add(ProductModel(img, title, price))
                    }
                    productRecyclerAdapter!!.notifyDataSetChanged()
                    progressDialog!!.dismiss()
                    Log.i("DataSnap", dataSnapshot.toString())
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
        } catch (e: Exception) {
            Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
        }
        return view
    }
}