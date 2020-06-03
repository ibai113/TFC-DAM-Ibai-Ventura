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

public class Horarios extends AppCompatActivity {

    ImageView logo;
    ListView listado;
    Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);

        logo = findViewById(R.id.logoHorario);
        listado = findViewById(R.id.listviewHorario);
        volver = findViewById(R.id.buttonVolverHorario);
    }

    public void  VOLVER (View view){

        Intent intent = getIntent();

        String str_correo = intent.getStringExtra("str_correoUsuario");

        Intent volver = new Intent(this, MainActivity.class);
        volver.putExtra("str_correoUsuario", str_correo);

        startActivity(volver);
    }
}
