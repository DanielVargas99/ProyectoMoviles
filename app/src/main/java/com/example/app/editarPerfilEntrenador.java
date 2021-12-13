package com.example.app;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class editarPerfilEntrenador extends AppCompatActivity {

    BottomNavigationView menuEntrenador;
    Button cerrar;
    ImageView foto;
    DatabaseReference bd;
    DatabaseReference tablaRef;
    FirebaseAuth auth;
    String img;
    TextView name;
    TextView team;
    String nombre;
    String equipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil_entrenador);

        menuEntrenador = findViewById(R.id.boton_navegacion);
        menuEntrenador.setSelectedItemId(R.id.perfil);
        foto = findViewById(R.id.imagen);
        name = findViewById(R.id.campoNombre);
        team = findViewById(R.id.campoEquipo);

        bd = FirebaseDatabase.getInstance().getReference();
        tablaRef = bd.child("entrenador");

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

        menuEntrenador.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.perfil:
                        return true;
                    case R.id.principal:
                        Intent intent = new Intent(getApplicationContext(), HomeEntrenador.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.recomendado:
                        Intent intent2 = new Intent(getApplicationContext(), recomendados_entrenador.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent2);
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        tablaRef.child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    nombre = snapshot.child("nombre").getValue().toString();
                    equipo = snapshot.child("equipoActual").getValue().toString();
                    img = snapshot.child("foto").getValue().toString();

                    name.setText(nombre);
                    team.setText(equipo);
                    Glide.with(getApplicationContext())
                            .load(img)
                            .fitCenter()
                            .centerCrop()
                            .into(foto);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}