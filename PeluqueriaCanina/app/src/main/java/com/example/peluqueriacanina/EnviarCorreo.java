package com.example.peluqueriacanina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnviarCorreo extends AppCompatActivity {

    Button btnVolver, btnEnviar;
    EditText informacioncita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_correo);

        informacioncita = findViewById(R.id.ETinformacion);

        btnVolver = findViewById(R.id.btnVolver);
        btnEnviar = findViewById(R.id.btnEnviar);
    }
    public void volver(View view) {
        Intent intent = getIntent();

        String str_correo = intent.getStringExtra("str_correoUsuario");

        Intent intent1 = new Intent(this, MainActivity.class);
        intent1.putExtra("str_correoUsuario", str_correo);

        startActivity(intent1);
    }

    public void enviarCorreo(View view){
        Intent email = new Intent(Intent.ACTION_SEND);
        //Decimos que sera para que lo envie
        email.setData(Uri.parse("mailto:"));



        //Metemos el email a donde tiene que enviar
        String[] destinatario = {"peluqueriarequeteguaupos@gmail.com", };
        email.putExtra(Intent.EXTRA_EMAIL, destinatario);

        //Creamos el asunto
        email.putExtra(Intent.EXTRA_SUBJECT, "Cita Previa");



        //Ahora que recoja el texto

        email.putExtra(Intent.EXTRA_TEXT, informacioncita.getText().toString());

        email.setType("message/rfc822");

        //Iniciamos el intent
        startActivity(Intent.createChooser(email, "Enviando Correo"));

    }


}
