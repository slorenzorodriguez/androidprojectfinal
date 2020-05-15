package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class DocumentacionCoche extends AppCompatActivity {
    EditText rg_matricula, rg_numbastidor, rg_marca, rg_modelo, rg_anho;
    Button rg_hablitarcitas;
    ImageButton rg_citas;
    //String str_dni, str_password, str_lugar, str_fecha, str_hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentacion_coche);
        rg_matricula = (EditText)findViewById(R.id.matriculaet);
        rg_numbastidor = (EditText)findViewById(R.id.etnumbastidor);
        rg_marca = (EditText)findViewById(R.id.etmarca);
        rg_modelo = (EditText)findViewById(R.id.etmodelo);
        rg_anho = (EditText)findViewById(R.id.etanho);
        rg_hablitarcitas = (Button)findViewById(R.id.btndoccoc);
        rg_citas = (ImageButton)findViewById(R.id.btncitaprevia);
    }

    public void RegisterDocCoche(View view) {

        //esto guardará en dos nuevas variables (userdni y userpwd) lo escrito como dni y como contraseña.
        String str_matricula = rg_matricula.getText().toString();
        String str_numbastidor = rg_numbastidor.getText().toString();
        String str_marca = rg_marca.getText().toString();
        String str_modelo = rg_modelo.getText().toString();
        String str_anho = rg_anho.getText().toString();
        String type = "registrodoc";

        //instanciamos la clase backgroundworker
        backgroundworker bw = new backgroundworker(this);
        bw.execute(type, str_matricula, str_numbastidor, str_marca, str_modelo, str_anho);

    }
    public void OpenMenuPrincipal(View view) {
        startActivity(new Intent(this, MenuPrincipal.class));

    }

}
