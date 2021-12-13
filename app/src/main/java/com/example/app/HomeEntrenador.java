package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeEntrenador extends AppCompatActivity {

    RecyclerView recyclerView;
    BottomNavigationView menuEntrenador;
    List<listaCartas> cartas;
    DatabaseReference bd;
    DatabaseReference tablaRef;
    String nombre;
    String edad;
    String posicion;
    String altura;
    String nroEquipos;
    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_entrenador);

        menuEntrenador = findViewById(R.id.boton_navegacion);
        menuEntrenador.setSelectedItemId(R.id.principal);

        menuEntrenador.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.recomendado:
                        return true;
                    case R.id.perfil:
                        Intent intent2 = new Intent(getApplicationContext(), editarPerfilEntrenador.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.principal:
                        return true;
                }
                return false;
            }
        });

        bd = FirebaseDatabase.getInstance().getReference();
        tablaRef = bd.child("jugador");

        recyclerView = findViewById(R.id.listaJug);
        listarCartas();
    }

    public void listarCartas(){

        cartas = new ArrayList<>();
        tablaRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                        nombre = dataSnapshot.child("nombre").getValue().toString();
                        edad = dataSnapshot.child("edad").getValue().toString();
                        posicion = dataSnapshot.child("posicion").getValue().toString();
                        altura = dataSnapshot.child("altura").getValue().toString();
                        nroEquipos = dataSnapshot.child("numeroEquipos").getValue().toString();
                        link = dataSnapshot.child("foto").getValue().toString();


                        cartas.add(new listaCartas(nombre, edad, posicion, altura, nroEquipos, link));
                    }

                    listAdapter Adapter = new listAdapter(cartas, getApplicationContext());
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(Adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}