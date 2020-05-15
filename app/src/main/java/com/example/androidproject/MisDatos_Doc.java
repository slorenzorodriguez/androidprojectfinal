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

public class MisDatos_Doc extends AppCompatActivity {
    EditText m_matricula, m_bastidor, m_marca, m_modelo, m_anho, ingr_matricula;
    Button btbuscar, btnmodificardoc, btneliminardoc;
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_datos__doc);
        m_matricula = (EditText) findViewById(R.id.n_matricula);
        m_bastidor = (EditText) findViewById(R.id.n_bastidor);
        m_marca = (EditText) findViewById(R.id.mostrar_marca);
        m_modelo = (EditText) findViewById(R.id.mostrar_modelo);
        m_anho = (EditText) findViewById(R.id.mostrar_anho);
        ingr_matricula = (EditText) findViewById(R.id.ing_matricula);
        btbuscar = (Button) findViewById(R.id.bt_buscar);
        btnmodificardoc = (Button) findViewById(R.id.btn_modificardoc);
        btneliminardoc = (Button) findViewById(R.id.btn_eliminardoc);

        btbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarDatosDoc("http://10.0.2.2:80/buscar_datos_doc.php?matricula=" + ingr_matricula.getText() + "");
            }
        });

        btnmodificardoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModificarDatosDoc("http://10.0.2.2:80/modificar_datos_coche.php");
            }
        });

        btneliminardoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EliminarDatosDoc("http://10.0.2.2:80/eliminar_datos_coche.php");
            }
        });
    }

    public void buscarDatosDoc (String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        m_matricula.setText(jsonObject.getString("matricula"));
                        m_bastidor.setText(jsonObject.getString("numbastidor"));
                        m_marca.setText(jsonObject.getString("marca"));
                        m_modelo.setText(jsonObject.getString("modelo"));
                        m_anho.setText(jsonObject.getString("anho"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "DATOS INEXISTENTES PARA SU MATRÍCULA", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }


    private void ModificarDatosDoc (String URL){
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
                parametros.put("matricula",ingr_matricula.getText().toString());
                parametros.put("numbastidor",m_bastidor.getText().toString());
                parametros.put("marca", m_marca.getText().toString());
                parametros.put("modelo", m_modelo.getText().toString());
                parametros.put("anho", m_anho.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    private void EliminarDatosDoc (String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                LimpiarDatosDoc();
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
                parametros.put("matricula",ingr_matricula.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    private void LimpiarDatosDoc(){
        m_matricula.setText("");
        m_bastidor.setText("");
        m_marca.setText("");
        m_modelo.setText("");
        m_anho.setText("");

    }
    public void OpenPrincipalMenu (View view){
        startActivity(new Intent(this, MenuPrincipal.class));
    }
}
