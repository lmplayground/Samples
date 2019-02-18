package com.hamburger.java8;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainMainActivity extends AppCompatActivity {
    private static final String TAG = "MainMainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.main_button);

        button.setOnClickListener(view -> {
            Thread thread = new Thread(MainMainActivity.this::work);

            thread.start();
        });
    }

    private void rotateView(View view) {
        view.animate().rotation(360).setDuration(1500);
    }



    void work(){
        //some serious work
        SystemClock.sleep(5000);
        //do some serious work
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
    }


    static class WorkerThread extends Thread{
        @Override
        public void run() {
            //some serious work
            SystemClock.sleep(5000);
        }
    }
}
