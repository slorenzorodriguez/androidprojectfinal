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

public class MisDatos extends AppCompatActivity {
    EditText m_dni, m_numpoliza, m_nombcomp, m_matricula, ingr_dni;
    Button btnbuscar, btnmodificarseg, btneliminarseg;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_datos);
        m_dni = (EditText) findViewById(R.id.mostrar_dni);
        m_numpoliza = (EditText) findViewById(R.id.m_numpoliza);
        m_nombcomp = (EditText) findViewById(R.id.mostrar_nomcom);
        m_matricula = (EditText) findViewById(R.id.matricula_m);
        ingr_dni = (EditText) findViewById(R.id.ingresar_dni);
        btnbuscar = (Button) findViewById(R.id.btn_buscar);
        btnmodificarseg = (Button) findViewById(R.id.btn_modificarseg);
        btneliminarseg = (Button) findViewById(R.id.btn_eliminarseg);

        btnbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarDatos("http://10.0.2.2:80/buscar_datos.php?dni=" + ingr_dni.getText() + "");
            }
        });
        btnmodificarseg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ModificarDatosSeguro("http://10.0.2.2:80/modificar_datos_seguro.php");
            }
        });
        btneliminarseg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EliminarDatosSeguro("http://10.0.2.2:80/eliminar_datos_seguro.php");
            }
        });
    }


    private void buscarDatos(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        m_dni.setText(jsonObject.getString("dni"));
                        m_numpoliza.setText(jsonObject.getString("numpoliza"));
                        m_nombcomp.setText(jsonObject.getString("nomcomp"));
                        m_matricula.setText(jsonObject.getString("matricula"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "DATOS INEXISTENTES PARA SU DNI", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void ModificarDatosSeguro (String URL){
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
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("dni",ingr_dni.getText().toString());
                parametros.put("numpoliza", m_numpoliza.getText().toString());
                parametros.put("nomcomp", m_nombcomp.getText().toString());
                parametros.put("matricula", m_matricula.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    private void EliminarDatosSeguro (String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LimpiarDatosSeg();
                Toast.makeText(getApplicationContext(), "ELIMINACIÓN EXITOSA", Toast.LENGTH_SHORT).show();
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

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    private void LimpiarDatosSeg(){
        m_dni.setText("");
        m_numpoliza.setText("");
        m_nombcomp.setText("");
        m_matricula.setText("");

    }

    public void MostrarDatosDoc (View view){
        startActivity(new Intent(this, MisDatos_Doc.class));
    }

}

