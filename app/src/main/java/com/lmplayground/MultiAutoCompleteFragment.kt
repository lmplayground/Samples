package com.lmplayground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.MultiAutoCompleteTextView



class MultiAutoCompleteFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_multi_auto, container, false)

        val textView = rootView.findViewById(R.id.multi_complete) as MultiAutoCompleteTextView
        textView.setAdapter(Countries.getAdapter(context))
        textView.setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())

        return rootView
    }
}