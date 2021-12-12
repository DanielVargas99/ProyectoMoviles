package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class recomendados_entrenador extends AppCompatActivity {

    RecyclerView recyclerView;
    BottomNavigationView menuEntrenador;
    List<listaCartas> cartas;
    DatabaseReference bd;
    DatabaseReference tablaRef;
    DatabaseReference tablaUser;
    String nombre;
    String edad;
    String posicion;
    String altura;
    String nroEquipos;
    String link;
    String interes1;
    String interes2;
    String interes3;
    FirebaseAuth auth;
    String peso;
    String ultimoEquipo;
    String nroPartidos;
    String nroTitulos;
    String correo;
    String telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_entrenador);

        menuEntrenador = findViewById(R.id.boton_navegacion);
        menuEntrenador.setSelectedItemId(R.id.recomendado);

        menuEntrenador.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.principal:
                        Intent intent = new Intent(getApplicationContext(), HomeEntrenador.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.perfil:
                        Intent intent2 = new Intent(getApplicationContext(), editarPerfilEntrenador.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.recomendado:
                        return true;
                }
                return false;
            }
        });

        bd = FirebaseDatabase.getInstance().getReference();
        tablaRef = bd.child("jugador");
        tablaUser = bd.child("entrenador");
        auth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.listaJug);
        listarCartas();
    }

    public void listarCartas(){

        cartas = new ArrayList<>();
        tablaUser.child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                interes1 = snapshot.child("interes1").getValue().toString();
                interes2 = snapshot.child("interes2").getValue().toString();
                interes3 = snapshot.child("interes3").getValue().toString();

                crearRecomendados(interes1, interes2, interes3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void crearRecomendados(String interes1, String interes2, String interes3){

        tablaRef.orderByChild("posicion").equalTo(interes1).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                        nombre = dataSnapshot.child("nombre").getValue().toString();
                        edad = dataSnapshot.child("edad").getValue().toString();
                        posicion = dataSnapshot.child("posicion").getValue().toString();
                        altura = dataSnapshot.child("altura").getValue().toString();
                        nroEquipos = dataSnapshot.child("numeroEquipos").getValue().toString();
                        peso = dataSnapshot.child("peso").getValue().toString();
                        telefono = dataSnapshot.child("telefono").getValue().toString();
                        ultimoEquipo = dataSnapshot.child("ultimoEquipo").getValue().toString();
                        nroPartidos = dataSnapshot.child("numeroPartidos").getValue().toString();
                        nroTitulos = dataSnapshot.child("numeroTitulos").getValue().toString();
                        correo = dataSnapshot.child("correo").getValue().toString();
                        link = dataSnapshot.child("foto").getValue().toString();


                        cartas.add(0, new listaCartas(nombre, edad, posicion, altura, nroEquipos, "j",
                                link, peso, telefono, ultimoEquipo, nroPartidos, nroTitulos, correo));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        tablaRef.orderByChild("posicion").equalTo(interes2).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                        nombre = dataSnapshot.child("nombre").getValue().toString();
                        edad = dataSnapshot.child("edad").getValue().toString();
                        posicion = dataSnapshot.child("posicion").getValue().toString();
                        altura = dataSnapshot.child("altura").getValue().toString();
                        nroEquipos = dataSnapshot.child("numeroEquipos").getValue().toString();
                        peso = dataSnapshot.child("peso").getValue().toString();
                        telefono = dataSnapshot.child("telefono").getValue().toString();
                        ultimoEquipo = dataSnapshot.child("ultimoEquipo").getValue().toString();
                        nroPartidos = dataSnapshot.child("numeroPartidos").getValue().toString();
                        nroTitulos = dataSnapshot.child("numeroTitulos").getValue().toString();
                        correo = dataSnapshot.child("correo").getValue().toString();
                        link = dataSnapshot.child("foto").getValue().toString();


                        cartas.add(new listaCartas(nombre, edad, posicion, altura, nroEquipos, "j",
                                link, peso, telefono, ultimoEquipo, nroPartidos, nroTitulos, correo));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        tablaRef.orderByChild("posicion").equalTo(interes3).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                        nombre = dataSnapshot.child("nombre").getValue().toString();
                        edad = dataSnapshot.child("edad").getValue().toString();
                        posicion = dataSnapshot.child("posicion").getValue().toString();
                        altura = dataSnapshot.child("altura").getValue().toString();
                        nroEquipos = dataSnapshot.child("numeroEquipos").getValue().toString();
                        peso = dataSnapshot.child("peso").getValue().toString();
                        telefono = dataSnapshot.child("telefono").getValue().toString();
                        ultimoEquipo = dataSnapshot.child("ultimoEquipo").getValue().toString();
                        nroPartidos = dataSnapshot.child("numeroPartidos").getValue().toString();
                        nroTitulos = dataSnapshot.child("numeroTitulos").getValue().toString();
                        correo = dataSnapshot.child("correo").getValue().toString();
                        link = dataSnapshot.child("foto").getValue().toString();


                        cartas.add(new listaCartas(nombre, edad, posicion, altura, nroEquipos, "j",
                                link, peso, telefono, ultimoEquipo, nroPartidos, nroTitulos, correo));
                    }
                }

                listAdapter Adapter = new listAdapter(cartas, getApplicationContext(), new listAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(listaCartas item) {
                        verDatos(item);
                    }
                });

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(Adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void verDatos(listaCartas item){
        Intent intent = new Intent(getApplicationContext(), descripcionJug.class);
        intent.putExtra("datos", item);
        startActivity(intent);
    }
}

