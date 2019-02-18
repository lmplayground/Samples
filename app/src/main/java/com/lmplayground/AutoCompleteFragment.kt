package com.lmplayground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_tabbed.view.*
import android.widget.AutoCompleteTextView
import android.widget.ArrayAdapter



class AutoCompleteFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_auto_complete, container, false)

        val adapter = Countries.getAdapter(context)
        val textView = rootView.findViewById(R.id.auto_complete) as AutoCompleteTextView
        textView.setAdapter<ArrayAdapter<String>>(adapter)

        return rootView
    }
}