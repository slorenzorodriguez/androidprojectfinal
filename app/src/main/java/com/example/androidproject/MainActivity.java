package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

        recuperarpreferencias();


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
                    GuardarPreferencias();
                    Intent intent = new Intent(getApplicationContext(),MenuPrincipal.class);
                    startActivity(intent);
                    finish();
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
                    parametros.put("dni",usuario);
                    parametros.put("password", pwd);
                    return parametros;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }
        //método para guardar datos de logeo,
        // al cerrar y abrir la app una vez logeado no volverá a pedir el login
        private void GuardarPreferencias (){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("dni", usuario);
        editor.putString("password", pwd);
        editor.putBoolean("sesion", true);
        editor.commit();
        }
        //método que permite recuperar los datos de login guardados
    private void recuperarpreferencias (){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        dni.setText(preferences.getString("dni", "123456789M"));
        password.setText(preferences.getString("password", "1234"));

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

