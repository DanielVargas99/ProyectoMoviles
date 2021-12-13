package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    String correo;
    String contraseña;
    String email;
    String pass;
    EditText correoElectronico;
    EditText password;
    Button iniciarSesion;
    Button registrarse;
    DatabaseReference bd;
    DatabaseReference tablaRef;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser usuarioActual = auth.getCurrentUser();

        if(usuarioActual != null){
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        bd = FirebaseDatabase.getInstance().getReference();
        tablaRef = bd.child("jugador");

        correoElectronico = findViewById(R.id.correo);
        password = findViewById(R.id.contraseña);
        iniciarSesion = findViewById(R.id.iniciarSesion);
        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = correoElectronico.getText().toString();
                contraseña = password.getText().toString();
                if (correo.equals("") || contraseña.equals("")){
                    Toast.makeText(getApplicationContext(), "Debes rellenar todos los campos!", Toast.LENGTH_SHORT).show();
                } else {
                    iniciarSesion(correo, contraseña);
                }
            }
        });

        registrarse = findViewById(R.id.registrarse);
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correoElectronico.setText("");
                password.setText("");
                Intent intent = new Intent(getApplicationContext(), tipoRegistro.class);
                startActivity(intent);
            }
        });
    }

    public void iniciarSesion(String correo, String contraseña){
        auth.signInWithEmailAndPassword(correo, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    tablaRef.child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            email = snapshot.child("correo").getValue().toString();
                            pass = snapshot.child("contraseña").getValue().toString();

                            if (email.equals(correo) && pass.equals(contraseña)) {
                                Intent intent = new Intent(getApplicationContext(), Home.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), "Correo o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "EL USUARIO NO ESTA REGISTRADO", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}