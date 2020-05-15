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

public class MisDatosCita extends AppCompatActivity {
    EditText dni_m, lugar_m, fecha_m, hora_m, ingr_dni;
    Button btnbuscar, btnmodificar, btneliminar, btnmenup;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_datos_cita);

        dni_m = (EditText) findViewById(R.id.m_dni);
        lugar_m = (EditText) findViewById(R.id.mostrar_lugar);
        fecha_m = (EditText) findViewById(R.id.mostrar_fecha);
        hora_m = (EditText) findViewById(R.id.mostrar_hora);
        ingr_dni = (EditText) findViewById(R.id.ing_dni);
        btnbuscar = (Button) findViewById(R.id.btnbuscar);
        btnmodificar = (Button) findViewById(R.id.btnmodificar);
        btneliminar = (Button) findViewById(R.id.btneliminar);
        btnmenup = (Button) findViewById(R.id.btn_menup);

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarDatosCitas("http://10.0.2.2:80/buscar_datos_cita.php?dni=" + ingr_dni.getText() + "");
            }
        });
        btnmodificar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ModificarDatosCitas("http://10.0.2.2:80/modificar_datos_cita.php");
            }

        });

          btneliminar.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
             Eliminardatoscitas("http://10.0.2.2:80/modificar_datos_cita.php");
        }

        });


    }
    public void buscarDatosCitas (String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        dni_m.setText(jsonObject.getString("dni"));
                        lugar_m.setText(jsonObject.getString("lugar"));
                        fecha_m.setText(jsonObject.getString("fecha"));
                        hora_m.setText(jsonObject.getString("hora"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


    private void ModificarDatosCitas (String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACIÓN EXITOSA", Toast.LENGTH_SHORT).show();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("dni",ingr_dni.getText().toString());
                parametros.put("lugar", lugar_m.getText().toString());
                parametros.put("fecha", fecha_m.getText().toString());
                parametros.put("hora", hora_m.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


    private void Eliminardatoscitas(String URL) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LimpiarDatosCitas();
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
                parametros.put("dni", ingr_dni.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    private void LimpiarDatosCitas() {
        dni_m.setText("");
        lugar_m.setText("");
        fecha_m.setText("");
        hora_m.setText("");


    }

    public void VolverMenuPrincipal (View view){
        startActivity(new Intent(this, MenuPrincipal.class));
    }

}



