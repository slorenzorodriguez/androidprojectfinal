package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //defino variables:
    EditText dni, password;
    Button btn_login;
    String usuario, pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializo las variables:
        dni = (EditText)findViewById(R.id.edtUsuario);
        password = (EditText)findViewById(R.id.edtPassword);
        btn_login = (Button) findViewById(R.id.btnLogin);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario=dni.getText().toString();
                pwd=password.getText().toString();
                Login("http://10.0.2.2:80/login.php");
                if(!usuario.isEmpty() && !pwd.isEmpty()){

                }else{
                    Toast.makeText(MainActivity.this, "Campos vacíos no permitidos", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }

        private void Login (String URL){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                if (!response.isEmpty()){
                    Intent intent = new Intent(getApplicationContext(),MenuPrincipal.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT);
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros=new HashMap<String, String>();
                    parametros.put("dni", dni.getText().toString());
                    parametros.put("password", password.getText().toString());
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }

    //método OnLogic, ejecutado al presionar el boton 'Login'
    //public void OnLogin (View view) {

        //esto guardará en dos nuevas variables (userdni y userpwd) lo escrito como dni y como contraseña.
        //  String userdni = dni.getText().toString();
        //String userpwd = password.getText().toString();
    //String type = "login";

        //instanciamos la clase backgroundworker
    //  backgroundworker bw = new backgroundworker(this);
    //  bw.execute(type, userdni, userpwd);

    //  startActivity(new Intent(this, MenuPrincipal.class));
    //}

    public void OpenRegister (View view){
        startActivity(new Intent(this, Registrar.class));

        }


    }

