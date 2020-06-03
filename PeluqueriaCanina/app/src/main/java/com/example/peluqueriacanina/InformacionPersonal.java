package com.example.peluqueriacanina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
public class InformacionPersonal extends AppCompatActivity {

    ImageView logo;
    ListView listado;
    Button RecuperarInformacion, volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_personal);

        logo = findViewById(R.id.logoHorario);
        listado = findViewById(R.id.listviewHorario);
        RecuperarInformacion = findViewById(R.id.buttonConsultarHorario);
        volver = findViewById(R.id.buttonVolverHorario);

        RecuperarInformacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }
        private void getData() {

            Intent intent = getIntent();

            String str_correo = intent.getStringExtra("str_correoUsuario");


            String url = RecuperarDatosPersonales.DATA_URL + str_correo;

            StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    showJSON(response);
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(InformacionPersonal.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }

        private void showJSON(String response){
            ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray result = jsonObject.getJSONArray(RecuperarDatosPersonales.JSON_ARRAY);

                for (int i = 0; i < result.length(); i++) {

                    JSONObject jo = result.getJSONObject(i);



                    String nombre = jo.getString(RecuperarDatosPersonales.KEY_nombre);
                    String correo = jo.getString(RecuperarDatosPersonales.KEY_correo);
                    String telefono = jo.getString(RecuperarDatosPersonales.KEY_telefono);
                    String contrasena = jo.getString(RecuperarDatosPersonales.KEY_contrasena);
                    String nombreMascota = jo.getString(RecuperarDatosPersonales.KEY_nombreMascota);
                    String pesoMascota = jo.getString(RecuperarDatosPersonales.KEY_pesoMascota);
                    String razaMascota = jo.getString(RecuperarDatosPersonales.KEY_razaMascota);
                    String generoMascota = jo.getString(RecuperarDatosPersonales.KEY_generoMascota);


                    final HashMap<String, String> employees = new HashMap<>();

                    employees.put(RecuperarDatosPersonales.KEY_nombre, nombre);
                    employees.put(RecuperarDatosPersonales.KEY_correo, correo);
                    employees.put(RecuperarDatosPersonales.KEY_telefono, telefono);
                    employees.put(RecuperarDatosPersonales.KEY_contrasena, contrasena);
                    employees.put(RecuperarDatosPersonales.KEY_nombreMascota, nombreMascota);
                    employees.put(RecuperarDatosPersonales.KEY_pesoMascota, pesoMascota);
                    employees.put(RecuperarDatosPersonales.KEY_razaMascota, razaMascota);
                    employees.put(RecuperarDatosPersonales.KEY_generoMascota, generoMascota);

                    list.add(employees);

                }

            } catch (JSONException e) {

                e.printStackTrace();

            }
            ListAdapter adapter = new SimpleAdapter(

                    InformacionPersonal.this, list, R.layout.activity_mi_lista,

                    new String[]{RecuperarDatosPersonales.KEY_nombre ,RecuperarDatosPersonales.KEY_correo, RecuperarDatosPersonales.KEY_telefono, RecuperarDatosPersonales.KEY_contrasena, RecuperarDatosPersonales.KEY_nombreMascota,RecuperarDatosPersonales.KEY_pesoMascota, RecuperarDatosPersonales.KEY_razaMascota, RecuperarDatosPersonales.KEY_generoMascota},

                    new int[]{R.id.usuario, R.id.correo, R.id.telefono, R.id.contrasena, R.id.nombreMascota, R.id.pesoMascota, R.id.razaMascota, R.id.generoMascota});
            listado.setAdapter(adapter);
    }
    public void  VOLVER (View view){

        Intent intent = getIntent();

        String str_correo = intent.getStringExtra("str_correoUsuario");

        Intent volver = new Intent(this, MainActivity.class);

        volver.putExtra("str_correoUsuario", str_correo);


        startActivity(volver);
    }
}
