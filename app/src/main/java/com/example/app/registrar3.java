package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;


public class registrar3 extends AppCompatActivity {

    ImageView fotoJug;
    String nombre;
    String edad;
    String altura;
    String peso;
    String telefono;
    String posicion;
    String ultimoEq;
    String nroEquipos;
    String nroPartidos;
    String nroTitulos;
    String correo;
    String contraseña;
    String recontraseña;
    EditText email;
    EditText password;
    EditText repassword;
    ProgressBar barra;
    Button boton;
    Button foto;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    DatabaseReference jugadorRef;
    StorageReference storageReference;
    StorageReference carpetaFotos;
    ProgressDialog progressDialog;
    private static final int galeria = 1;
    Uri uri;
    Uri descargarUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar2);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        jugadorRef = databaseReference.child("jugador");

        email = findViewById(R.id.campo1);
        password = findViewById(R.id.campo2);
        repassword = findViewById(R.id.campo3);

        barra = findViewById(R.id.barra);
        barra.setProgress(100);

        nombre = getIntent().getStringExtra("nombre");
        edad = getIntent().getStringExtra("edad");
        altura = getIntent().getStringExtra("altura");
        peso = getIntent().getStringExtra("peso");
        telefono = getIntent().getStringExtra("telefono");
        posicion = getIntent().getStringExtra("posicion");
        ultimoEq = getIntent().getStringExtra("ultimoEq");
        nroEquipos = getIntent().getStringExtra("nroEquipos");
        nroPartidos = getIntent().getStringExtra("nroPartidos");
        nroTitulos = getIntent().getStringExtra("nroTitulos");

        storageReference = FirebaseStorage.getInstance().getReference();
        fotoJug = findViewById(R.id.cargarFoto);

        foto = findViewById(R.id.subirFoto);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, galeria);
            }
        });

        boton = findViewById(R.id.botonFin);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correo = email.getText().toString();
                contraseña = password.getText().toString();
                recontraseña = repassword.getText().toString();
                if (correo.equals("") || contraseña.equals("") || recontraseña.equals("")){
                    Toast.makeText(getApplicationContext(), "Debes rellenar todos los campos!", Toast.LENGTH_SHORT).show();
                }
                if (contraseña.equals(recontraseña)){
                    crearInicioSesion(correo, contraseña);
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == galeria && resultCode == RESULT_OK){

            uri = data.getData();
            fotoJug.setImageURI(uri);

            carpetaFotos = storageReference.child("fotosJugador").child(uri.getLastPathSegment());
            carpetaFotos.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();

                    while (!uriTask.isSuccessful());

                    descargarUri = uriTask.getResult();
                }
            });
        }
    }

    public void crearInicioSesion(String correo, String contraseña){
        auth.createUserWithEmailAndPassword(correo, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "USUARIO CREADO", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = auth.getCurrentUser();
                    iniciarSesion(user, correo, contraseña);
                } else {
                    Toast.makeText(getApplicationContext(), "USUARIO NO CREADO", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void iniciarSesion(FirebaseUser usuario, String correo, String contraseña){
        auth.signInWithEmailAndPassword(correo, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    registroJugador(jugadorRef, usuario, correo, contraseña);
                }
            }
        });
    }

    public void registroJugador(DatabaseReference tablaRef, FirebaseUser usuario, String correo, String contraseña){


        Map<String, String> jugadores = new HashMap<>();

        jugadores.put("nombre", nombre);
        jugadores.put("edad", edad);
        jugadores.put("altura", altura);
        jugadores.put("peso", peso);
        jugadores.put("telefono", telefono);
        jugadores.put("posicion", posicion);
        jugadores.put("ultimoEquipo", ultimoEq);
        jugadores.put("numeroEquipos", nroEquipos);
        jugadores.put("numeroPartidos", nroPartidos);
        jugadores.put("numeroTitulos", nroTitulos);
        jugadores.put("correo", correo);
        jugadores.put("contraseña", contraseña);
        jugadores.put("foto" , descargarUri.toString());


        tablaRef.child(usuario.getUid()).setValue(jugadores);
    }
}