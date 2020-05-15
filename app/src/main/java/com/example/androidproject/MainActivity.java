package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //defino variables:
    EditText dni, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializo las variables:
        dni = (EditText)findViewById(R.id.txtdni);
        password = (EditText)findViewById(R.id.txtpwd);
    }

    //método OnLogic, ejecutado al presionar el boton 'Login'
    public void OnLogin (View view) {

        //esto guardará en dos nuevas variables (userdni y userpwd) lo escrito como dni y como contraseña.
        String userdni = dni.getText().toString();
        String userpwd = password.getText().toString();
        String type = "login";

        //instanciamos la clase backgroundworker
        backgroundworker bw = new backgroundworker(this);
        bw.execute(type, userdni, userpwd);

        startActivity(new Intent(this, MenuPrincipal.class));
    }

    public void OpenRegister (View view){
        startActivity(new Intent(this, Registrar.class));

        }


    }

