package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class descripcionEnt extends AppCompatActivity {

    TextView nombre, edad, equipo, años, interes, telefono, correo;
    ImageView entrenador;
    String foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_ent);

        listaCartas datos = (listaCartas) getIntent().getSerializableExtra("datos");

        nombre = findViewById(R.id.campoNombre);
        edad = findViewById(R.id.campoEdad);
        equipo = findViewById(R.id.equipo);
        años = findViewById(R.id.activo);
        interes = findViewById(R.id.interesado);
        telefono = findViewById(R.id.tel);
        correo = findViewById(R.id.em);
        entrenador = findViewById(R.id.imagenEntrenador);
        foto = datos.getLink();

        nombre.setText(datos.getAtributo1());
        edad.setText(datos.getAtributo2());
        equipo.setText(datos.getAtributo3());
        años.setText(datos.getAtributo4());
        interes.setText(datos.getAtributo5());
        telefono.setText(datos.getAtributo7());
        correo.setText(datos.getAtributo8());

        Glide.with(getApplicationContext())
                .load(foto)
                .into(entrenador);
    }
}