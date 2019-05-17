package com.leumi.lmbase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

open class BaseFragment<FVM : ViewModel,AVM: ViewModel>:Fragment() {
//    var fvmClass :Class<FVM> = Class.forName(FVM::class.qualifiedName) as Class<FVM>
    init {
//        fvmClass = this.javaClass.
    }
    lateinit var fragmentViewModel: FVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        fragmentViewModel = ViewModelProviders.of(this,)

        return null
    }


}
