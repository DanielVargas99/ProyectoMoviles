package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class registrar extends AppCompatActivity {

    String nombre;
    String edad;
    String altura;
    String peso;
    String telefono;
    String posicion;
    String ultimoEquipo;
    String numeroEquipos;
    String numeroPartidos;
    String numeroTitulos;
    String correo;
    String contraseña;
    String foto;
    EditText name;
    EditText Edad;
    EditText talla;
    EditText Peso;
    EditText nroCel;
    Button siguiente;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

            nombre = getIntent().getStringExtra("nombre");
            edad = getIntent().getStringExtra("edad");
            altura = getIntent().getStringExtra("altura");
            peso = getIntent().getStringExtra("peso");
            telefono = getIntent().getStringExtra("telefono");
            posicion = getIntent().getStringExtra("posicion");
            ultimoEquipo = getIntent().getStringExtra("ultimoEquipo");
            numeroEquipos = getIntent().getStringExtra("numeroEquipos");
            numeroPartidos = getIntent().getStringExtra("numeroPartidos");
            numeroTitulos = getIntent().getStringExtra("numeroTitulos");
            correo = getIntent().getStringExtra("correo");
            contraseña = getIntent().getStringExtra("contraseña");
            foto = getIntent().getStringExtra("foto");
            
            name = findViewById(R.id.campo1);
            name.setHint("Nombre");
            Edad = findViewById(R.id.campo2);
            Edad.setHint("Edad");
            Edad.setInputType(InputType.TYPE_CLASS_NUMBER);
            talla = findViewById(R.id.campo3);
            talla.setHint("Altura");
            talla.setInputType(InputType.TYPE_CLASS_PHONE);
            Peso = findViewById(R.id.campo4);
            Peso.setHint("Peso(En KG)");
            Peso.setInputType(InputType.TYPE_CLASS_PHONE);
            nroCel = findViewById(R.id.campo5);
            nroCel.setHint("Telefono");
            nroCel.setInputType(InputType.TYPE_CLASS_NUMBER);
        //}

        name.setText(nombre);
        Edad.setText(edad);
        talla.setText(altura);
        Peso.setText(peso);
        nroCel.setText(telefono);

        siguiente = findViewById(R.id.boton);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = name.getText().toString();
                edad = Edad.getText().toString();
                altura = talla.getText().toString();
                peso = Peso.getText().toString();
                telefono = nroCel.getText().toString();
                if (nombre.equals("") || edad.equals("") || altura.equals("") || peso.equals("") || telefono.equals("")){
                    Toast.makeText(getApplicationContext(), "Debes rellenar todos los campos!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), registrar2.class);
                    intent.putExtra("nombre", nombre);
                    intent.putExtra("edad", edad);
                    intent.putExtra("altura", altura);
                    intent.putExtra("peso", peso);
                    intent.putExtra("telefono", telefono);
                    startActivity(intent);
                }
            }
        });
    }

}