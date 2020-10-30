package com.itgates.search_employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class Categories : Fragment() {
    var add: Button? = null
    var search: Button? = null
    var addProcdut: Button? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        add = view.findViewById(R.id.button)
        search = view.findViewById(R.id.button2)
        addProcdut = view.findViewById(R.id.addProcdutBtn)
        add!!.setOnClickListener(View.OnClickListener { view -> Navigation.findNavController(view).navigate(R.id.action_categories_to_add2) })
        search!!.setOnClickListener(View.OnClickListener { view -> Navigation.findNavController(view).navigate(R.id.action_categories_to_search3) })
        addProcdut!!.setOnClickListener(View.OnClickListener { v -> Navigation.findNavController(v).navigate(R.id.action_categories_to_addProductFragment) })
        return view
    }
}