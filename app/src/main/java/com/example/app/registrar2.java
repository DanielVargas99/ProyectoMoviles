package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class registrar2 extends AppCompatActivity {

    TextView informacion;
    EditText position;
    EditText ultEq;
    EditText nroTit;
    EditText nroEq;
    EditText ctdPartidos;
    String nombre;
    String edad;
    String altura;
    String peso;
    String telefono;
    String posicion;
    String ultimoEq;
    String nroTitulos;
    String nroEquipos;
    String nroPartidos;
    Button boton;
    ProgressBar barra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        informacion = findViewById(R.id.info);
        informacion.setText("Informacion profesional");
        position = findViewById(R.id.campo1);
        position.setHint("Posicion donde juega");
        ultEq = findViewById(R.id.campo2);
        ultEq.setHint("Ultimo equipo/academia donde jugo");
        nroEq = findViewById(R.id.campo3);
        nroEq.setHint("Cantidad de equipos donde ha jugado");
        nroEq.setInputType(InputType.TYPE_CLASS_NUMBER);
        ctdPartidos = findViewById(R.id.campo4);
        ctdPartidos.setHint("Cantidad de partidos jugados");
        ctdPartidos.setInputType(InputType.TYPE_CLASS_NUMBER);
        nroTit = findViewById(R.id.campo5);
        nroTit.setHint("Cantidad de titulos conseguidos");
        nroTit.setInputType(InputType.TYPE_CLASS_NUMBER);

        barra = findViewById(R.id.barra);
        barra.setProgress(50);

        nombre = getIntent().getStringExtra("nombre");
        edad = getIntent().getStringExtra("edad");
        altura = getIntent().getStringExtra("altura");
        peso = getIntent().getStringExtra("peso");
        telefono = getIntent().getStringExtra("telefono");

        boton = findViewById(R.id.boton);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicion = position.getText().toString();
                ultimoEq = ultEq.getText().toString();
                nroEquipos = nroEq.getText().toString();
                nroPartidos = ctdPartidos.getText().toString();
                nroTitulos = nroTit.getText().toString();
                if (posicion.equals("") || ultimoEq.equals("") || nroEquipos.equals("") || nroPartidos.equals("") || nroTitulos.equals("")){
                    Toast.makeText(getApplicationContext(), "Debes rellenar todos los campos!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), registrar3.class);
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
                    startActivity(intent);
                }
            }
        });
    }
}