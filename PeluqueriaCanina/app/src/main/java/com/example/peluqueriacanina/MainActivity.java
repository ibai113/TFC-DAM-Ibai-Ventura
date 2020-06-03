package com.example.peluqueriacanina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

Button informacion_personal, precio, horario, recogida_domicilio, cita_previa, informacion_peluqueria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        informacion_personal = findViewById(R.id.buttonInformacionPersonal);
        precio = findViewById(R.id.buttonPrecios);
        horario = findViewById(R.id.buttonPrecios);
        recogida_domicilio = findViewById(R.id.buttonRecogidaDomicilio);
        cita_previa = findViewById(R.id.buttonCitaPrevia);
        informacion_peluqueria = findViewById(R.id.buttonInformacionPeluqueria);

    }

    public void infopersonal (View view){

        Intent intent = getIntent();

        String str_correo = intent.getStringExtra("str_correoUsuario");

        Intent intent1 = new Intent(this, InformacionPersonal.class);

        intent1.putExtra("str_correoUsuario", str_correo);

        startActivity(intent1);


    }

    public void precio (View view){
        Intent intent = getIntent();

        String str_correo = intent.getStringExtra("str_correoUsuario");

        Intent intent2 = new Intent(this, Precios.class);

        intent2.putExtra("str_correoUsuario", str_correo);
        startActivity(intent2);
    }
    public void horario (View view){
        Intent intent = getIntent();

        String str_correo = intent.getStringExtra("str_correoUsuario");

        Intent intent3 = new Intent(this, Horarios.class);
        intent3.putExtra("str_correoUsuario", str_correo);
        startActivity(intent3);
    }
    public void recogidaDomicilio (View view){
        Intent intent = getIntent();

        String str_correo = intent.getStringExtra("str_correoUsuario");

        Intent intent4 = new Intent(this, RecogidaDomicilio.class);
        intent4.putExtra("str_correoUsuario", str_correo);
        startActivity(intent4);
    }
    public void citaPrevia (View view){
        Intent intent = getIntent();

        String str_correo = intent.getStringExtra("str_correoUsuario");

        Intent intent5 = new Intent(this, EnviarCorreo.class);
        intent5.putExtra("str_correoUsuario", str_correo);
        startActivity(intent5);
    }
    public void infoPeluqueria (View view){
        Intent intent = getIntent();

        String str_correo = intent.getStringExtra("str_correoUsuario");

        Intent intent6 = new Intent(this, InformacionRequeteguaupos.class);
        intent6.putExtra("str_correoUsuario", str_correo);
       startActivity(intent6);

    }

}
