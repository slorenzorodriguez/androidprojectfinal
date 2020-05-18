package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity {
    //defino variables:
    EditText dni, password;
    Button btncitas, btn_mcitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        //inicializo las variables:
       // dni = (EditText) findViewById(R.id.txtdni);
        //password = (EditText) findViewById(R.id.txtpwd);
        //btncitas = (Button) findViewById(R.id.button18);

        //Toast.makeText(getApplicationContext(), "¡Bienvenido/a!", Toast.LENGTH_SHORT).show();
    }



    public void OpenRegister(View view) {
        startActivity(new Intent(this, activity_registro.class));

    }
    public void OpenRegisterSeg(View view) {
        startActivity(new Intent(this, DocumentacionSeguro.class));
    }

    public void MostarDatosSeguro (View view){

        startActivity(new Intent(this, MisDatos.class));
    }

    public void OpenMisDatosUsuario (View view){

        startActivity(new Intent(this, MisDatosUsuario.class));
    }
    public void MostrarDatosCita (View view){

        startActivity(new Intent(this, MisDatosCita.class));
    }

}
