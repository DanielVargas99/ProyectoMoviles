package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class editarPerfil extends AppCompatActivity {

    BottomNavigationView menuJugador;
    Button cerrar;
    Button editar;
    ImageView foto;
    DatabaseReference bd;
    DatabaseReference tablaRef;
    FirebaseAuth auth;
    String img;
    TextView name;
    TextView ed;
    TextView pos;
    TextView ultimoEq;
    TextView tel;
    String nombre;
    String edad;
    String posicion;
    String ultimoEquipo;
    String telefono;
    String altura;
    String peso;
    String numeroEquipos;
    String numeroPartidos;
    String numeroTitulos;
    String correo;
    String contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        menuJugador = findViewById(R.id.boton_navegacion);
        menuJugador.setSelectedItemId(R.id.perfil);
        foto = findViewById(R.id.imagen);
        name = findViewById(R.id.campoNombre);
        ed = findViewById(R.id.campoEdad);
        pos = findViewById(R.id.campoPosicion);
        ultimoEq = findViewById(R.id.campoEquipo);
        tel = findViewById(R.id.campoTelefono);

        bd = FirebaseDatabase.getInstance().getReference();
        tablaRef = bd.child("jugador");

        auth = FirebaseAuth.getInstance();

        cerrar = findViewById(R.id.cerrarSesion);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        menuJugador.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.perfil:
                        return true;
                    case R.id.principal:
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        editar = findViewById(R.id.editar);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tablaRef.child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.exists()){

                            nombre = snapshot.child("nombre").getValue().toString();
                            edad = snapshot.child("edad").getValue().toString();
                            altura = snapshot.child("altura").getValue().toString();;
                            peso = snapshot.child("peso").getValue().toString();;
                            numeroEquipos = snapshot.child("numeroEquipos").getValue().toString();;
                            numeroPartidos = snapshot.child("numeroPartidos").getValue().toString();;
                            numeroTitulos = snapshot.child("numeroTitulos").getValue().toString();;
                            correo = snapshot.child("correo").getValue().toString();;
                            contraseña = snapshot.child("contraseña").getValue().toString();;
                            posicion = snapshot.child("posicion").getValue().toString();
                            ultimoEquipo = snapshot.child("ultimoEquipo").getValue().toString();
                            telefono = snapshot.child("telefono").getValue().toString();
                            img = snapshot.child("foto").getValue().toString();

                            Intent intent = new Intent(getApplicationContext(), registrar.class);
                            intent.putExtra("nombre", nombre);
                            intent.putExtra("edad", edad);
                            intent.putExtra("altura", altura);
                            intent.putExtra("peso", peso);
                            intent.putExtra("telefono", telefono);
                            intent.putExtra("posicion", posicion);
                            intent.putExtra("ultimoEquipo", ultimoEquipo);
                            intent.putExtra("numeroEquipos", numeroEquipos);
                            intent.putExtra("numeroPartidos", numeroPartidos);
                            intent.putExtra("numeroTitulos", numeroTitulos);
                            intent.putExtra("correo", correo);
                            intent.putExtra("contraseña", contraseña);
                            intent.putExtra("foto" , img);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        tablaRef.child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    nombre = snapshot.child("nombre").getValue().toString();
                    edad = snapshot.child("edad").getValue().toString();
                    altura = snapshot.child("altura").getValue().toString();;
                    peso = snapshot.child("peso").getValue().toString();;
                    numeroEquipos = snapshot.child("numeroEquipos").getValue().toString();;
                    numeroPartidos = snapshot.child("numeroPartidos").getValue().toString();;
                    numeroTitulos = snapshot.child("numeroTitulos").getValue().toString();;
                    correo = snapshot.child("correo").getValue().toString();;
                    contraseña = snapshot.child("contraseña").getValue().toString();;
                    posicion = snapshot.child("posicion").getValue().toString();
                    ultimoEquipo = snapshot.child("ultimoEquipo").getValue().toString();
                    telefono = snapshot.child("telefono").getValue().toString();
                    img = snapshot.child("foto").getValue().toString();

                    name.setText(nombre);
                    ed.setText(edad);
                    pos.setText(posicion);
                    ultimoEq.setText(ultimoEquipo);
                    tel.setText(telefono);
                    Glide.with(getApplicationContext())
                            .load(img)
                            .into(foto);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}