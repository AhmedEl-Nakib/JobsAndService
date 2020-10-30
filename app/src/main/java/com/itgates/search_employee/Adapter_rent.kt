package com.itgates.search_employee

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter_rent(var context: Context?, var list: MutableList<Model_rent?>?) : RecyclerView.Adapter<Adapter_rent.ViewHolder?>() {
    var aBuilder: AlertDialog.Builder?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model_rent = list!!.get(position)
        // holder.imageproduct.setText(model.getName());
        holder.nameprod!!.setText(model_rent!!.getProductName())
        holder.descriprod!!.setText(model_rent!!.getDescription())
        holder.priceprod!!.setText(model_rent!!.getPrice())
        Picasso.get().load(model_rent!!.getImage()).into(holder.imageView)
        holder.constraintLayout!!.setOnClickListener(View.OnClickListener {
            aBuilder!!.setTitle("Person Data ")
            aBuilder!!.setMessage("""
    Person Name : ${model_rent.getPersonName()}
    Person Phone : ${model_rent.getPersonPhone()}
    """.trimIndent())
            aBuilder!!.create().show()
        })
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameprod: TextView?
        var descriprod: TextView?
        var priceprod: TextView?
        var imageView: ImageView?
        var constraintLayout: ConstraintLayout?

        init {
            nameprod = itemView.findViewById(R.id.NameOfProduct)
            descriprod = itemView.findViewById(R.id.Description)
            priceprod = itemView.findViewById(R.id.Price)
            imageView = itemView.findViewById(R.id.imageproduct)
            constraintLayout = itemView.findViewById(R.id.mainConstraintId)
        }
    }

    init {
        aBuilder = AlertDialog.Builder(context)
    }
}