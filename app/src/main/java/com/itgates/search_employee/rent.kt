package com.itgates.search_employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class rent : Fragment() {
    var rent1: Button? = null
    var rent2: Button? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_rent, container, false)
        rent1 = view.findViewById(R.id.rent1)
        rent2 = view.findViewById(R.id.rent2)
        rent1!!.setOnClickListener(View.OnClickListener { view -> Navigation.findNavController(view).navigate(R.id.action_rent_to_rent22) })
        rent2!!.setOnClickListener(View.OnClickListener { view -> Navigation.findNavController(view).navigate(R.id.action_rent_to_rent3) })
        return view
    }
}