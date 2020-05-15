package com.example.androidproject;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class backgroundworker extends AsyncTask <String, Void, String> {

    Context context;
    AlertDialog alertDialog;
    backgroundworker (Context ctx) {
        context = ctx;
    }

    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];
        //establecemos una url para comunicarnos con ella, en este caso nos comunicaremos con la base de datos.
        //ip de url estándar por android 10.0.2.2:
        String login_url = "http://10.0.2.2:80/login.php";//si da problemas al logear puede que sea esta ip (normalmente funciona), para cambiarla, comando - ipconfig
        String register_url = "http://10.0.2.2:80/register.php";
        String registrodoc_url = "http://10.0.2.2:80/registrodoc.php";
        String registroseguro_url = "http://10.0.2.2:80/registroseguro.php";
        String registrousuario_url = "http://10.0.2.2:80/registrousuario.php";
        if (type.equals("login")){
            try {
                String user_dni = voids[1];
                String user_pwd = voids[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter (new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("dni", "UTF-8")+"="+URLEncoder.encode(user_dni, "UTF-8")+"&"
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(user_pwd, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result="";
                String line = "";
                while((line = bufferedReader.readLine())!= null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }else  if (type.equals("register")) {
            try {
                String u_dni = voids[1];
                String u_password = voids[2];
                String u_lugar = voids[3];
                String u_fecha = voids[4];
                String u_hora = voids[5];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("dni", "UTF-8") + "=" + URLEncoder.encode(u_dni, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(u_password, "UTF-8") + "&"
                        + URLEncoder.encode("lugar", "UTF-8") + "=" + URLEncoder.encode(u_lugar, "UTF-8") + "&"
                        + URLEncoder.encode("fecha", "UTF-8") + "=" + URLEncoder.encode(u_fecha, "UTF-8") + "&"
                        + URLEncoder.encode("hora", "UTF-8") + "=" + URLEncoder.encode(u_hora, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else  if (type.equals("registrodoc")) {
                try {
                    String u_matricula = voids[1];
                    String u_numbastidor = voids[2];
                    String u_marca = voids[3];
                    String u_modelo = voids[4];
                    String u_anho = voids[5];
                    URL url = new URL(registrodoc_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter (new OutputStreamWriter(outputStream, "UTF-8"));

                    String post_data = URLEncoder.encode("matricula", "UTF-8")+"="+URLEncoder.encode(u_matricula, "UTF-8")+"&"
                            +URLEncoder.encode("numbastidor", "UTF-8")+"="+URLEncoder.encode(u_numbastidor, "UTF-8")+"&"
                            +URLEncoder.encode("marca", "UTF-8")+"="+URLEncoder.encode(u_marca, "UTF-8")+"&"
                            +URLEncoder.encode("modelo", "UTF-8")+"="+URLEncoder.encode(u_modelo, "UTF-8")+"&"
                            +URLEncoder.encode("anho", "UTF-8")+"="+URLEncoder.encode(u_anho, "UTF-8");

                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result="";
                    String line = "";
                    while((line = bufferedReader.readLine())!= null){
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }else  if (type.equals("registroseguro")) {
            try {
                String u_dniseg = voids[1];
                String u_numpoliza = voids[2];
                String u_nomcomp = voids[3];
                String u_matriculaseg = voids[4];
                URL url = new URL(registroseguro_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter (new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("dni", "UTF-8")+"="+URLEncoder.encode(u_dniseg, "UTF-8")+"&"
                        +URLEncoder.encode("numpoliza", "UTF-8")+"="+URLEncoder.encode(u_numpoliza, "UTF-8")+"&"
                        +URLEncoder.encode("nomcomp", "UTF-8")+"="+URLEncoder.encode(u_nomcomp, "UTF-8")+"&"
                        +URLEncoder.encode("matricula", "UTF-8")+"="+URLEncoder.encode(u_matriculaseg, "UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result="";
                String line = "";
                while((line = bufferedReader.readLine())!= null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if (type.equals("registrousuario")) {
            try {
                String dni_login = voids[1];
                String password_login = voids[2];
                String name_login = voids[3];
                URL url = new URL(registrousuario_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter (new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data = URLEncoder.encode("dni", "UTF-8")+"="+URLEncoder.encode(dni_login, "UTF-8")+"&"
                        +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password_login, "UTF-8")+"&"
                        +URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name_login, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result="";
                String line = "";
                while((line = bufferedReader.readLine())!= null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Estado de operación:");

    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        alertDialog.show();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

    }
}
