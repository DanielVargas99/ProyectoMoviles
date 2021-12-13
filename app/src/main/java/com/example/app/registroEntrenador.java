package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class registroEntrenador extends AppCompatActivity {

    String nombre;
    String edad;
    String equipo;
    String añosActivo;
    String telefono;
    EditText name;
    EditText Edad;
    EditText team;
    EditText aActivo;
    EditText nroCel;
    Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        name = findViewById(R.id.campo1);
        name.setHint("Nombre");
        Edad = findViewById(R.id.campo2);
        Edad.setHint("Edad");
        team = findViewById(R.id.campo3);
        team.setHint("Equipo actual que dirige");
        aActivo = findViewById(R.id.campo4);
        aActivo.setHint("Años activo");
        nroCel = findViewById(R.id.campo5);
        nroCel.setHint("Telefono");

        siguiente = findViewById(R.id.boton);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = name.getText().toString();
                edad = Edad.getText().toString();
                equipo = team.getText().toString();
                añosActivo = aActivo.getText().toString();
                telefono = nroCel.getText().toString();
                Intent intent = new Intent(getApplicationContext(), registroEntrenador2.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("edad", edad);
                intent.putExtra("equipo", equipo);
                intent.putExtra("activo", añosActivo);
                intent.putExtra("telefono", telefono);
                startActivity(intent);
            }
        });

    }
}