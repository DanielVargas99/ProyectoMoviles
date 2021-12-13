package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class registrar3 extends AppCompatActivity {

    TextView informacion;
    EditText vel;
    EditText fue;
    EditText reg;
    EditText tir;
    EditText pas;
    String nombre;
    String edad;
    String altura;
    String peso;
    String telefono;
    String posicion;
    String ultimoEq;
    String nroTitulos;
    String nroPartidos;
    String nroEquipos;
    String velocidad;
    String fuerza;
    String regate;
    String tiro;
    String pase;
    Button boton;
    ProgressBar barra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        informacion = findViewById(R.id.info);
        informacion.setText("Informacion adicional");
        vel = findViewById(R.id.campo1);
        vel.setHint("Velocidad");
        fue = findViewById(R.id.campo2);
        fue.setHint("Fuerza");
        reg = findViewById(R.id.campo3);
        reg.setHint("Regate");
        tir = findViewById(R.id.campo4);
        tir.setHint("Tiro");
        pas = findViewById(R.id.campo5);
        pas.setHint("Pase");

        barra = findViewById(R.id.barra);
        barra.setProgress(66);

        nombre = getIntent().getStringExtra("nombre");
        edad = getIntent().getStringExtra("edad");
        altura = getIntent().getStringExtra("altura");
        peso = getIntent().getStringExtra("peso");
        telefono = getIntent().getStringExtra("telefono");
        posicion = getIntent().getStringExtra("posicion");
        ultimoEq = getIntent().getStringExtra("ultimoEq");
        nroEquipos = getIntent().getStringExtra("nroEquipos");
        nroPartidos = getIntent().getStringExtra("nroPartidos");
        nroTitulos = getIntent().getStringExtra("nroTitulos");

        boton = findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                velocidad = vel.getText().toString();
                fuerza = fue.getText().toString();
                regate = reg.getText().toString();
                tiro = tir.getText().toString();
                pase = pas.getText().toString();

                Intent intent = new Intent(getApplicationContext(), registrar4.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("edad", edad);
                intent.putExtra("altura", altura);
                intent.putExtra("peso", peso);
                intent.putExtra("telefono", telefono);
                intent.putExtra("posicion", posicion);
                intent.putExtra("ultimoEq", ultimoEq);
                intent.putExtra("nroEquipos", nroEquipos);
                intent.putExtra("nroPartidos", nroPartidos);
                intent.putExtra("nroTitulos", nroTitulos);
                intent.putExtra("velocidad", velocidad);
                intent.putExtra("fuerza", fuerza);
                intent.putExtra("regate", regate);
                intent.putExtra("tiro", tiro);
                intent.putExtra("pase", pase);
                startActivity(intent);
            }
        });
    }
}