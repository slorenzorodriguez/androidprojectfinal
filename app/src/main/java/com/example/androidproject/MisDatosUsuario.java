package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MisDatosUsuario extends AppCompatActivity {
    EditText m_dni, m_pwd, m_uname;
    Button btn_buscaruser, btnmodificaruser, btneliminaruser;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_datos_usuario);

        m_dni = (EditText) findViewById(R.id.mostrardni);
        m_uname = (EditText) findViewById(R.id.mostrarusername);
        m_pwd = (EditText) findViewById(R.id.ing_pwd);
        btn_buscaruser = (Button) findViewById(R.id.btnbuscar);
        btnmodificaruser = (Button) findViewById(R.id.button16);
        btneliminaruser = (Button) findViewById(R.id.button17);

        btn_buscaruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarDatosUser("http://10.0.2.2:80/buscar_datos_usuario.php?password=" + m_pwd.getText() + "");
            }
        });

        btnmodificaruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ModificarDatosUser("http://10.0.2.2:80/modificar_datos_usuario.php");
                }
        });

        btneliminaruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EliminarDatosUser("http://10.0.2.2:80/eliminar_datos_usuario.php");
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });

    }

    public void buscarDatosUser(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        m_dni.setText(jsonObject.getString("dni"));
                        m_uname.setText(jsonObject.getString("name"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "USUARIO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void ModificarDatosUser(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "MODIFICACIÓN EXITOSA", Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("dni", m_dni.getText().toString());
                parametros.put("name", m_uname.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    private void EliminarDatosUser(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LimpiarDatosUser();
                Toast.makeText(getApplicationContext(), "ELIMINACIÓN EXITOSA", Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("password", m_pwd.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


    private void LimpiarDatosUser() {
        m_dni.setText("");
        m_uname.setText("");


    }

    public void usuarioeliminado(View view) {
        startActivity(new Intent(this, MisDatos_Doc.class));
    }
}
