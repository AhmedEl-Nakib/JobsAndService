package com.itgates.search_employee

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MyAdapter(var context: Context?, var list: ArrayList<MyModel?>?) : RecyclerView.Adapter<MyAdapter.ViewHolder?>() {
    var sharedpreferences: SharedPreferences?
    var editor: SharedPreferences.Editor?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.room_two, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mymodel = list!!.get(position)
        holder.place!!.setText(mymodel!!.getPlace())
        holder.descri!!.setText(mymodel.getDescription())
        holder.category!!.setText(mymodel.getCategory())
        holder.constraintLayout!!.setOnClickListener(View.OnClickListener { v -> //                editor.putString("UidForSearch",mymodel.Uid);
            val bundle = Bundle()
            bundle.putString("UidForSearch", mymodel.getUid())
            Navigation.findNavController(v).navigate(R.id.action_search2_to_productsFragment, bundle)
        })
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var place: TextView?
        var descri: TextView?
        var category: TextView?
        var constraintLayout: ConstraintLayout?

        init {
            place = itemView.findViewById(R.id.textview4)
            descri = itemView.findViewById(R.id.textview5)
            category = itemView.findViewById(R.id.cate)
            constraintLayout = itemView.findViewById(R.id.mainConstraintId)
        }
    }

    init {
        sharedpreferences = context!!.getSharedPreferences("JS", Context.MODE_PRIVATE)
        editor = sharedpreferences!!.edit()
    }
}