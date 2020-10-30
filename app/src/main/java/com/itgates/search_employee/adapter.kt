package com.itgates.search_employee

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapter(var context: Context?, var list: MutableList<model?>?) : RecyclerView.Adapter<adapter.ViewHolder?>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.room, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list!!.get(position)
        holder.name!!.setText(model!!.getName())
        holder.phone!!.setText(model.getPhone())
        holder.salary!!.setText(model.getSalary())
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView?
        var phone: TextView?
        var salary: TextView?

        init {
            name = itemView.findViewById(R.id.name)
            phone = itemView.findViewById(R.id.phone)
            salary = itemView.findViewById(R.id.salary)
        }
    }
}