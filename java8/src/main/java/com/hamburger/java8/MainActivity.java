package com.hamburger.java8;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements NetworkInterface{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.main_button);

        button.setOnClickListener(this::animateView);
    }


    Runnable runnable = this::work;

    void animateView(View view){
        view.animate().rotation(360).setDuration(2000);
    }
    void work(){
        //do some serious work
        SystemClock.sleep(2000);
        //notify
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone sound = RingtoneManager.getRingtone(getApplicationContext(), notification);
        sound.play();
    }

    @Override
    public void onData(String data) {

    }



}
