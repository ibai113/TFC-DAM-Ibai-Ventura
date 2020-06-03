package com.example.peluqueriacanina;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

   EditText nomUsuario, correoUsuario, telefonoUsuario, contrasenaUsuario, nombreMascota, pesoMascota,razaMascota,generoMascota;
   Button registrar;

   //Conventirlos en texto y luego en string
   String str_nomUsuario, str_correoUsuario, str_telefonoUsuario, str_contrasenaUsuario, str_generoMascota, str_razaMascota, str_pesoMascota, str_nombreMascota;

   //Conexion a la BD
    String url = "https://rogdomain.ddns.net:8860/requeteguau/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nomUsuario = findViewById(R.id.editTextNomUsuario);
        correoUsuario = findViewById(R.id.editTextCorreoElec);
        telefonoUsuario = findViewById(R.id.editTextTelefono);
        contrasenaUsuario = findViewById(R.id.editTextContrasena);
        nombreMascota = findViewById(R.id.editTextNomMascota);
        pesoMascota = findViewById(R.id.editTextPesoMascota);
        razaMascota = findViewById(R.id.editTextRazaMascota);
        generoMascota = findViewById(R.id.editTextGeneroMascota);


        registrar = findViewById(R.id.buttonRegistrarme);
    }

    public void Registro (View view){

        //Cuando le damos al boton sale una ventana para que esperemos
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Por favor espere...");

        //------------------------------

        String verificar_nombre = nomUsuario.getText().toString();
        String verificar_correo = correoUsuario.getText().toString();
        String verificar_telefono = telefonoUsuario.getText().toString();
        String verificar_contrasena = contrasenaUsuario.getText().toString();
        String verificar_nombremascota = nombreMascota.getText().toString();
        String verificar_pesomascota = pesoMascota.getText().toString();
        String verificar_razamascota= razaMascota.getText().toString();
        String verificar_generomascota = generoMascota.getText().toString();

        if(verificar_nombre.isEmpty()){
            nomUsuario.setError("Ingrese un nombre de usuario");
        }
        else if(verificar_correo.isEmpty()){
            correoUsuario.setError("Ingrese un correo electronico");
        }
        else if(verificar_telefono.isEmpty()){
            telefonoUsuario.setError("Ingrese su telefono");
        }
        else if(verificar_contrasena.isEmpty()){
            contrasenaUsuario.setError("Ingrese una contraseña");
        }
        else if(verificar_nombremascota.isEmpty()){
            nombreMascota.setError("Ingrese el nombre de su mascota");
        }
        else if(verificar_pesomascota.isEmpty()){
            pesoMascota.setError("Ingrese el peso de su mascota");
        }
        else if(verificar_razamascota.isEmpty()){
            razaMascota.setError("Ingrese la raza de su mascota");
        }
        else if(verificar_generomascota.isEmpty()){
            generoMascota.setError("Ingrese el genero de su mascota");
        }
        else{


            progressDialog.show();
            str_nomUsuario = nomUsuario.getText().toString().trim();
            str_correoUsuario = correoUsuario.getText().toString().trim();
            str_telefonoUsuario = telefonoUsuario.getText().toString().trim();
            str_contrasenaUsuario = contrasenaUsuario.getText().toString().trim();
            str_generoMascota = generoMascota.getText().toString().trim();
            str_razaMascota = razaMascota.getText().toString().trim();
            str_pesoMascota = pesoMascota.getText().toString().trim();
            str_nombreMascota = nombreMascota.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    nomUsuario.setText("");
                    correoUsuario.setText("");
                    telefonoUsuario.setText("");
                    contrasenaUsuario.setText("");
                    nombreMascota.setText("");
                    pesoMascota.setText("");
                    razaMascota.setText("");
                    generoMascota.setText("");




                    if(response.equalsIgnoreCase("Registro guardado")){

                        startActivity(new Intent(getApplicationContext(),InicioSesion.class));
                        Toast.makeText(Registro.this, response, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Registro.this, response, Toast.LENGTH_SHORT).show();
                    }
                }

            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(Registro.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();

                    //BD parametros
                    params.put("nombre",str_nomUsuario);
                    params.put("correo",str_correoUsuario);
                    params.put("telefono",str_telefonoUsuario);
                    params.put("contrasena",str_contrasenaUsuario);
                    params.put("nombre_mascota",str_nombreMascota);
                    params.put("peso_mascota",str_pesoMascota);
                    params.put("raza_mascota",str_razaMascota);
                    params.put("genero_mascota",str_generoMascota);

                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(Registro.this);
            requestQueue.add(request);

        }


    }
}
