package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registrar extends AppCompatActivity {
    EditText u_dni, u_password, u_username, u_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        u_dni = (EditText) findViewById(R.id.reg_dni);
        u_password = (EditText) findViewById(R.id.reg_password);
        u_username = (EditText) findViewById(R.id.reg_username);
        u_email = (EditText) findViewById(R.id.reg_email);

    }

    public void registrarusuario (View view) {

        //esto guardará en dos nuevas variables (userdni y userpwd) lo escrito como dni y como contraseña.
        String str_dni = u_dni.getText().toString();
        String str_password = u_password.getText().toString();
        String str_username = u_username.getText().toString();
        String str_email = u_email.getText().toString();

        String type = "registrousuario";

        //instanciamos la clase backgroundworker

        backgroundworker bw = new backgroundworker(this);
        bw.execute(type, str_dni, str_password,str_username,str_email);

    }

    public void Volver(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
