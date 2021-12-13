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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView;
    BottomNavigationView menuJugador;
    List<listaCartas> cartas;
    DatabaseReference bd;
    DatabaseReference tablaRef;
    String nombre;
    String edad;
    String equipoActual;
    String añosActivo;
    String interes;
    String link;
    String telefono;
    String correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        menuJugador = findViewById(R.id.boton_navegacion);
        menuJugador.setSelectedItemId(R.id.principal);

        menuJugador.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.perfil:
                        Intent intent = new Intent(getApplicationContext(), editarPerfil.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.principal:
                        return true;
                }
                return false;
            }
        });

        bd = FirebaseDatabase.getInstance().getReference();
        tablaRef = bd.child("entrenador");

        recyclerView = findViewById(R.id.listaCartasRecycler);
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
                        equipoActual = dataSnapshot.child("equipoActual").getValue().toString();
                        añosActivo = dataSnapshot.child("añosActivo").getValue().toString();
                        telefono = dataSnapshot.child("telefono").getValue().toString();
                        correo = dataSnapshot.child("correo").getValue().toString();
                        interes = dataSnapshot.child("interes1").getValue().toString();
                        link = dataSnapshot.child("foto").getValue().toString();

                        cartas.add(new listaCartas(nombre, edad, equipoActual, añosActivo, interes, "e",
                                link, telefono, correo, "", "", "", ""));
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void verDatos(listaCartas item){
        Intent intent = new Intent(getApplicationContext(), descripcionEnt.class);
        intent.putExtra("datos", item);
        startActivity(intent);
    }
}