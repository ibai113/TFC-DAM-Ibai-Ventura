package com.example.peluqueriacanina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecogidaDomicilio extends AppCompatActivity {

    Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recogida_domicilio);

        volver = findViewById(R.id.buttonVolver);
    }

    public void Volver (View view){
        Intent intent = getIntent();

        String str_correo = intent.getStringExtra("str_correoUsuario");

        Intent volver = new Intent(this, MainActivity.class);
        volver.putExtra("str_correoUsuario", str_correo);

        startActivity(volver);
    }
}
