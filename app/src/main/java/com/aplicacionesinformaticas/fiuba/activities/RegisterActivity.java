package com.aplicacionesinformaticas.fiuba.activities;

import android.app.DatePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.aplicacionesinformaticas.fiuba.R;
import com.aplicacionesinformaticas.fiuba.model.User;
import com.aplicacionesinformaticas.fiuba.utils.SharedPreferencesManager;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    public static final int RegisterActivityResultOK = 1;
    public static final int RegisterActivityResultNotOK = 2;
    public static final int RegisterActivityRequestCode = 1;
    private DatePickerDialog datePickerDialog;
    Button btnGuardar;
    TextInputEditText tieNombre;
    TextInputEditText tieApellido;
    TextInputEditText tieNacimiento;
    TextInputEditText tieHijos;
    TextInputEditText tieUser;
    TextInputEditText tiePassword;
    RadioGroup rgGenero;
    RadioButton rbHombre;
    RadioButton rbMujer;
    CheckBox cbHipertension;
    CheckBox cbHipotension;
    CheckBox cbDiabetico;
    CheckBox cbCeliaco;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        datePickerDialog = new DatePickerDialog(RegisterActivity.this, RegisterActivity.this, 1900 , 1, 1);

        tieNacimiento = (TextInputEditText) findViewById(R.id.tieDate);
        tieNombre = (TextInputEditText) findViewById(R.id.tieNombre);
        tieApellido = (TextInputEditText) findViewById(R.id.tieApellido);
        tieHijos = (TextInputEditText) findViewById(R.id.tieHijos);
        rgGenero = (RadioGroup) findViewById(R.id.rgGenero);
        rbHombre = (RadioButton)findViewById(R.id.rbGeneroHombre);
        rbMujer = (RadioButton)findViewById(R.id.rbGeneroMujer);
        cbCeliaco = (CheckBox) findViewById(R.id.cbCeliaco);
        cbHipertension = (CheckBox) findViewById(R.id.cbHipertension);
        cbHipotension = (CheckBox) findViewById(R.id.cbHipotension);
        cbDiabetico = (CheckBox) findViewById(R.id.cbDiabetes);

        tieUser = (TextInputEditText) findViewById(R.id.etUserName);
        tiePassword = (TextInputEditText) findViewById(R.id.etPassword);

        user = new User();

        tieNacimiento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    datePickerDialog.show();
                }
            }
        });

        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validar()){
                    setUser();
                    setPassword();
                    SharedPreferencesManager.getInstance(RegisterActivity.this).saveUser(User.getUsuarioActual());
                    volver();
                }
            }
        });
    }

    private void setUser(){
        user.setNacimiento(tieNacimiento.getText().toString());
        user.setNombre(tieNombre.getText().toString());
        user.setApellido(tieApellido.getText().toString());
        user.setHijos(Integer.valueOf(tieHijos.getText().toString()));
        user.setGenero(rbHombre.isChecked() ? User.GENDER_MALE : User.GENDER_FEMALE);
        user.setDiabetes(cbDiabetico.isChecked());
        user.setCeliaco(cbCeliaco.isChecked());
        user.setHipertension(cbHipertension.isChecked());
        user.setHipotension(cbHipotension.isChecked());

        User.setUsuarioActual(user);
    }

    private void setPassword(){
        SharedPreferencesManager pref = SharedPreferencesManager.getInstance(RegisterActivity.this);
        pref.setValue(SharedPreferencesManager.KEY_USER, tieUser.getText().toString());
        pref.setValue(SharedPreferencesManager.KEY_PASSWORD, tiePassword.getText().toString());
    }

    public boolean validar(){
        boolean result = true;
        result = result && validar(tieApellido) && validar(tieNacimiento)
                && validar(tieNombre) && validar(rgGenero) && validar(tieHijos);
        return result;
    }

    public boolean validar(TextInputEditText textInput){
        return validar(textInput.getText().toString());
    }

    public boolean validar(String string){
        return string.replace(" ", "").length() > 0;
    }

    public boolean validar(RadioGroup radioGroup){
        for (int i = 0; i < radioGroup.getChildCount(); i++){
            if (((RadioButton)radioGroup.getChildAt(i)).isChecked()){
                Toast.makeText(RegisterActivity.this, "Por favor complete los datos de registro", Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return false;
    }


    public void volver(){
        this.setResult(RegisterActivityResultOK);
        this.finish();
    }

    @Override
    public void onBackPressed() {
        this.setResult(RegisterActivityResultNotOK);
        this.finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Toast.makeText(RegisterActivity.this, "hola", Toast.LENGTH_LONG);
        TextInputEditText tieDate = (TextInputEditText) findViewById(R.id.tieDate);
        tieDate.setText(dayOfMonth + "/" + month + "/" + year);
    }

}
