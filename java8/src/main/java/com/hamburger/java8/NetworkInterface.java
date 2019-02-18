package com.hamburger.java8;

import android.util.Log;

public interface NetworkInterface {
    String TAG = "NetworkInterface";

    void onData(String data);

    default void onError(Exception exception){
        printMessage(exception.getMessage());
    }

    static void printMessage(String message){
        Log.d(TAG,message);
    }

}
