package com.aplicacionesinformaticas.fiuba.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.aplicacionesinformaticas.fiuba.R;

public class MainActivity extends AppCompatActivity {

    private View root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//        startActivity(intent);

        // Start timer and launch main activity
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();

    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        root = super.onCreateView(parent, name, context, attrs);
/*
        root.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, 10000);*/
        return root;
    }

    private class IntentLauncher extends Thread {
        @Override
        /**
         * Sleep for some time and than start new activity.
         */
        public void run() {
            try {
                // Sleeping
                Thread.sleep(5000);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage());
            }

            // Start main activity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(intent);
            MainActivity.this.finish();
        }
    }
}
