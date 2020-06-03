package com.example.peluqueriacanina;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.ValueIterator;
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

public class InicioSesion extends AppCompatActivity {
    EditText correoUsuario, contrasenaUsuario;
    Button inicioSesion, registroUsuario;

    //Conventirlos en texto y luego en string
    String str_correoUsuario,str_contraseñaUsuario;

    //Conexion a la BD
    String url = "https://rogdomain.ddns.net:8860/requeteguau/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        correoUsuario = findViewById(R.id.editTextCorreoUsuario);
        contrasenaUsuario = findViewById(R.id.editTextContrasenaUsuario);

        inicioSesion = findViewById(R.id.buttonIniciarSesion);
        registroUsuario = findViewById(R.id.buttonRegistrarUsuario);


    }

    public void iniciarSesion (View view){
        String verificar_correo = correoUsuario.getText().toString();
        String verificar_contraseña = contrasenaUsuario.getText().toString();

        if(verificar_correo.isEmpty()){
            correoUsuario.setError("Ingrese un correo electronico");
        }
        else if(verificar_contraseña.isEmpty()){
            contrasenaUsuario.setError("Ingrese una contraseña");
        }
        else{

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Iniciando sesión espere...");

            progressDialog.show();

            str_correoUsuario = correoUsuario.getText().toString().trim();
            str_contraseñaUsuario = contrasenaUsuario.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if(response.equalsIgnoreCase("Sesion Iniciada")){

                        correoUsuario.setText("");
                        contrasenaUsuario.setText("");

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        intent.putExtra("str_correoUsuario", str_correoUsuario);

                        startActivity(intent);
                        Toast.makeText(InicioSesion.this, response, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        correoUsuario.setError("Correo electronico erroneo");
                    }
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(InicioSesion.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("correo",str_correoUsuario);
                    params.put("contrasena",str_contraseñaUsuario);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);

        }

    }

    public void registrarUsuario (View view){
        Intent registro = new Intent(this, Registro.class);
        startActivity(registro);
    }
}
