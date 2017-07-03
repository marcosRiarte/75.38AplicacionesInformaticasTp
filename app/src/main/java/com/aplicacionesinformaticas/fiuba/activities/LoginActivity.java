package com.aplicacionesinformaticas.fiuba.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.aplicacionesinformaticas.fiuba.R;
import com.aplicacionesinformaticas.fiuba.model.User;
import com.aplicacionesinformaticas.fiuba.utils.SharedPreferencesManager;

public class LoginActivity extends AppCompatActivity {
    public static String TAG = "LoginActivity";

    TextInputEditText etUser;
    TextInputEditText etPassword;
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

                showRegisterActivity();
            }
        });

        etUser = (TextInputEditText) findViewById(R.id.etUserName);
        etPassword = (TextInputEditText) findViewById(R.id.etPassword);
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

        SharedPreferencesManager spManager = SharedPreferencesManager.getInstance(LoginActivity.this);
        User u = SharedPreferencesManager.getInstance(LoginActivity.this).getUser();
        if (u == null){
            return false;
        } else {
            User.setUsuarioActual(u);
            //return true;
        }

        if (u.getUserNameLogin().equals(etUser.getText().toString()) &&
           (u.getPassword().equals(etPassword.getText().toString()))) {
            return true;
        } else {
            Toast.makeText(LoginActivity.this, "La combinacionde Usuario y Password no es correcta", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public void loadMainScreen(){
        Log.d(TAG, "loadMainScreen" );
        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
    }
}
