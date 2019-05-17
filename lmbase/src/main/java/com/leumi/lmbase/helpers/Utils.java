package com.leumi.lmbase.helpers;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

public class Utils {

    static <K extends ViewModel> K getViewModel(Fragment fragment){
        return ViewModelProviders.of(fragment).get(null);
    }
}
