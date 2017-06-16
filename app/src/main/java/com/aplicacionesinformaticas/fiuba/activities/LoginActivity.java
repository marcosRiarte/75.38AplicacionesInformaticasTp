package com.aplicacionesinformaticas.fiuba.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aplicacionesinformaticas.fiuba.R;

public class LoginActivity extends AppCompatActivity {
    public static String TAG = "LoginActivity";

    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick" );
                if (validar()){
                    loadMainScreen();
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabRegister);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                showRegisterActivity();
            }
        });
    }

    private void showRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, RegisterActivity.RegisterActivityRequestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RegisterActivity.RegisterActivityRequestCode){
            if (resultCode == RegisterActivity.RegisterActivityResultOK){
                loadMainScreen();
            }
        }
    }

    public boolean validar(){
        Log.d(TAG, "Validar" );
        return true;
    }

    public void loadMainScreen(){
        Log.d(TAG, "loadMainScreen" );
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
    }
}
