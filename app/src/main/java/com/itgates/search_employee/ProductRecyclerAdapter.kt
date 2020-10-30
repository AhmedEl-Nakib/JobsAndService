package com.itgates.search_employee

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.itgates.search_employee.ProductRecyclerAdapter.productRecyclerAdapter
import com.squareup.picasso.Picasso

class ProductRecyclerAdapter(var context: Context?, var list: MutableList<ProductModel?>?) : RecyclerView.Adapter<productRecyclerAdapter?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): productRecyclerAdapter {
        val v = LayoutInflater.from(context).inflate(R.layout.row2, parent, false)
        return productRecyclerAdapter(v)
    }

    override fun onBindViewHolder(holder: productRecyclerAdapter, position: Int) {
        val mymodel = list!!.get(position)
        holder.title!!.setText(mymodel!!.getTitle())
        holder.price!!.setText(mymodel!!.getPrice())
        Picasso.get().load(mymodel!!.getImgUrl()).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    inner class productRecyclerAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView?
        var title: TextView?
        var price: TextView?

        init {
            imageView = itemView.findViewById(R.id.imageproduct)
            title = itemView.findViewById(R.id.NameOfProduct)
            price = itemView.findViewById(R.id.PriceOfProduct)
        }
    }
}