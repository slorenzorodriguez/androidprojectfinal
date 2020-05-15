package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class activity_registro extends AppCompatActivity {
    EditText rg_dni, rg_password, rg_lugar, rg_fecha, rg_hora;
    //String str_dni, str_password, str_lugar, str_fecha, str_hora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        rg_dni = (EditText)findViewById(R.id.etdni);
        rg_password = (EditText)findViewById(R.id.etpassword);
        rg_lugar = (EditText)findViewById(R.id.etlugar);
        rg_fecha = (EditText)findViewById(R.id.etfecha);
        rg_hora = (EditText)findViewById(R.id.ethora);

    }

    public void OnRegister (View view) {

        //esto guardará en dos nuevas variables (userdni y userpwd) lo escrito como dni y como contraseña.
        String str_dni = rg_dni.getText().toString();
        String str_password = rg_password.getText().toString();
        String str_lugar = rg_lugar.getText().toString();
        String str_fecha = rg_fecha.getText().toString();
        String str_hora = rg_hora.getText().toString();
        String type = "register";

        //instanciamos la clase backgroundworker
        backgroundworker bw = new backgroundworker(this);
        bw.execute(type, str_dni, str_password,str_lugar, str_fecha, str_hora);

    }
}
