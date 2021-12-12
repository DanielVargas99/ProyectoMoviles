package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class registroEntrenador2 extends AppCompatActivity {

    String interes1;
    String interes2;
    String interes3;
    String nombre;
    String edad;
    String equipo;
    String añosActivo;
    String telefono;
    EditText int1;
    EditText int2;
    EditText int3;
    EditText c1;
    EditText c2;
    Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        int1 = findViewById(R.id.campo1);
        int1.setHint("Posicion de interes 1");
        c1 = findViewById(R.id.campo2);
        c1.setVisibility(View.INVISIBLE);
        int2 = findViewById(R.id.campo3);
        int2.setHint("Posicion de interes 2");
        c2 = findViewById(R.id.campo4);
        c2.setVisibility(View.INVISIBLE);
        int3 = findViewById(R.id.campo5);
        int3.setHint("Posicion de interes 3");

        nombre = getIntent().getStringExtra("nombre");
        edad = getIntent().getStringExtra("edad");
        equipo = getIntent().getStringExtra("equipo");
        añosActivo = getIntent().getStringExtra("activo");
        telefono = getIntent().getStringExtra("telefono");

        siguiente = findViewById(R.id.boton);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interes1 = int1.getText().toString();
                interes2 = int2.getText().toString();
                interes3 = int3.getText().toString();
                Intent intent = new Intent(getApplicationContext(), registroEntrenador3.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("edad", edad);
                intent.putExtra("equipo", equipo);
                intent.putExtra("activo", añosActivo);
                intent.putExtra("telefono", telefono);
                intent.putExtra("int1", interes1);
                intent.putExtra("int2", interes2);
                intent.putExtra("int3", interes3);
                startActivity(intent);
            }
        });
    }
}