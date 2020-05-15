package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DocumentacionSeguro extends AppCompatActivity {
    EditText rg_dni, rg_numpoliza, rg_nomcomp, rg_matricula;
    //String str_dni, str_password, str_lugar, str_fecha, str_hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentacion_seguro);
        rg_dni = (EditText) findViewById(R.id.dniet);
        rg_numpoliza = (EditText) findViewById(R.id.etnumpoliza);
        rg_nomcomp = (EditText) findViewById(R.id.etnomcomp);
        rg_matricula = (EditText) findViewById(R.id.matriculaet);
    }

    public void RegisterDocSeg(View view) {

        //esto guardará en dos nuevas variables (userdni y userpwd) lo escrito como dni y como contraseña.
        String str_dni = rg_dni.getText().toString();
        String str_numpoliza = rg_numpoliza.getText().toString();
        String str_nomcomp = rg_nomcomp.getText().toString();
        String str_matricula = rg_matricula.getText().toString();
        String type = "registroseguro";

        //instanciamos la clase backgroundworker
        backgroundworker bw = new backgroundworker(this);
        bw.execute(type, str_dni, str_numpoliza, str_nomcomp, str_matricula);

    }

    public void OpenRegisterDoc(View view) {
        startActivity(new Intent(this, DocumentacionCoche.class));
    }
}

