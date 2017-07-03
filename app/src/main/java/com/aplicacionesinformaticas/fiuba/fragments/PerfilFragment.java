package com.aplicacionesinformaticas.fiuba.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.aplicacionesinformaticas.fiuba.R;
import com.aplicacionesinformaticas.fiuba.activities.RegisterActivity;
import com.aplicacionesinformaticas.fiuba.model.User;
import com.aplicacionesinformaticas.fiuba.utils.SharedPreferencesManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PerfilFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "PerfilFragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View root;

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

    private OnFragmentInteractionListener mListener;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        User user = SharedPreferencesManager.getInstance(this.getActivity()).getUser();

        root = inflater.inflate(R.layout.activity_register, container, false);

        inicializarVista(user);

        btnGuardar = (Button) root.findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validar()){
                    setUser();
                    setPassword();
                    SharedPreferencesManager.getInstance(getActivity()).saveUser(User.getUsuarioActual());
                    Toast.makeText(getActivity(), "Cambios Guardados", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Por favor complete los datos del registro", Toast.LENGTH_LONG).show();
                }
            }
        });
        return root;
    }

    public boolean validar(){
        boolean result = true;
        result = result && validar(tieApellido) && validar(tieNacimiento)
                && validar(tieNombre) && validar(rgGenero) && validar(tieHijos)
                && validar(tieUser) && validar(tiePassword);
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
                return true;
            }
        }
        return false;
    }

    private void setUser(){
        User user = User.getUsuarioActual();
        user.setNacimiento(tieNacimiento.getText().toString());
        user.setNombre(tieNombre.getText().toString());
        user.setApellido(tieApellido.getText().toString());
        user.setHijos(Integer.valueOf(tieHijos.getText().toString()));
        user.setGenero(rbHombre.isChecked() ? User.GENDER_MALE : User.GENDER_FEMALE);
        user.setDiabetes(cbDiabetico.isChecked());
        user.setCeliaco(cbCeliaco.isChecked());
        user.setUserNameLogin(tieUser.getText().toString());
        user.setPassword(tiePassword.getText().toString());
        user.setHipertension(cbHipertension.isChecked());
        user.setHipotension(cbHipotension.isChecked());

        User.setUsuarioActual(user);
    }

    private void setPassword(){
        SharedPreferencesManager pref = SharedPreferencesManager.getInstance(getActivity());
        pref.setValue(SharedPreferencesManager.KEY_USER_NAME, tieUser.getText().toString());
        pref.setValue(SharedPreferencesManager.KEY_PASSWORD, tiePassword.getText().toString());
    }

    private void inicializarVista(User user){

        tieNacimiento = (TextInputEditText) root.findViewById(R.id.tieDate);
        tieNombre = (TextInputEditText) root.findViewById(R.id.tieNombre);
        tieApellido = (TextInputEditText) root.findViewById(R.id.tieApellido);
        tieHijos = (TextInputEditText) root.findViewById(R.id.tieHijos);
        rgGenero = (RadioGroup) root.findViewById(R.id.rgGenero);
        rbHombre = (RadioButton)root.findViewById(R.id.rbGeneroHombre);
        rbMujer = (RadioButton)root.findViewById(R.id.rbGeneroMujer);
        cbCeliaco = (CheckBox) root.findViewById(R.id.cbCeliaco);
        cbHipertension = (CheckBox) root.findViewById(R.id.cbHipertension);
        cbHipotension = (CheckBox) root.findViewById(R.id.cbHipotension);
        cbDiabetico = (CheckBox) root.findViewById(R.id.cbDiabetes);

        tieUser = (TextInputEditText) root.findViewById(R.id.etUserName);
        tiePassword = (TextInputEditText) root.findViewById(R.id.etPassword);

        tieNacimiento.setText(user.getNacimiento());
        tieNombre.setText(user.getNombre());
        tieApellido.setText(user.getApellido());
        tieHijos.setText(String.valueOf(user.getHijos()));
        //rgGenero.setText(user);
        rbHombre.setChecked(user.getGenero() == User.GENDER_MALE);
        rbMujer.setChecked(user.getGenero() == User.GENDER_FEMALE);
        cbCeliaco.setChecked(user.isCeliaco());
        cbHipertension.setChecked(user.isHipertension());
        cbHipotension.setChecked(user.isHipotension());
        cbDiabetico.setChecked(user.isDiabetes());

        tieUser.setText(user.getUserNameLogin());
        tiePassword.setText(user.getPassword());
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        Toast.makeText(getActivity(), "actualizar", Toast.LENGTH_LONG);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
