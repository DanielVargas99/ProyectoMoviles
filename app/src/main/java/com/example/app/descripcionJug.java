package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class descripcionJug extends AppCompatActivity {

    TextView nombre, edad, posicion, altura, nroEquipos, peso, telefono, ultimoEq, nroPartidos, nroTitulos, correo;
    ImageView jugador;
    String foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_jug);

        listaCartas datos = (listaCartas) getIntent().getSerializableExtra("datos");

        nombre = findViewById(R.id.campoNombre);
        edad = findViewById(R.id.campoEdad);
        posicion = findViewById(R.id.pos);
        altura = findViewById(R.id.alt);
        nroEquipos = findViewById(R.id.numeroEq);
        peso = findViewById(R.id.peso);
        telefono = findViewById(R.id.tel);
        ultimoEq = findViewById(R.id.ultimo);
        nroPartidos = findViewById(R.id.nroPt);
        nroTitulos = findViewById(R.id.nroTit);
        correo = findViewById(R.id.emailJug);
        jugador = findViewById(R.id.imagenJugador);
        foto = datos.getLink();

        nombre.setText(datos.getAtributo1());
        edad.setText(datos.getAtributo2());
        posicion.setText(datos.getAtributo3());
        altura.setText(datos.getAtributo4());
        nroEquipos.setText(datos.getAtributo5());
        peso.setText(datos.getAtributo7());
        telefono.setText(datos.getAtributo8());
        ultimoEq.setText(datos.getAtributo9());
        nroPartidos.setText(datos.getAtributo10());
        nroTitulos.setText(datos.getAtributo11());
        correo.setText(datos.getAtributo12());

        Glide.with(getApplicationContext())
                .load(foto)
                .into(jugador);
    }
}