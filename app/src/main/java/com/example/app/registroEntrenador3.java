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

public class registroEntrenador3 extends AppCompatActivity {

    ImageView fotoEnt;
    String nombre;
    String edad;
    String equipoActual;
    String añosActivo;
    String telefono;
    String interes1;
    String interes2;
    String interes3;
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
    DatabaseReference entrenadorRef;
    StorageReference storageReference;
    StorageReference carpetaFotos;
    StorageReference fotosJugadores;
    private static final int galeria = 1;
    Uri uri;
    Uri descargarUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar2);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        entrenadorRef = databaseReference.child("entrenador");

        email = findViewById(R.id.campo1);
        password = findViewById(R.id.campo2);
        repassword = findViewById(R.id.campo3);

        barra = findViewById(R.id.barra);
        barra.setProgress(100);

        nombre = getIntent().getStringExtra("nombre");
        edad = getIntent().getStringExtra("edad");
        equipoActual = getIntent().getStringExtra("equipo");
        añosActivo = getIntent().getStringExtra("activo");
        telefono = getIntent().getStringExtra("telefono");
        interes1 = getIntent().getStringExtra("int1");
        interes2 = getIntent().getStringExtra("int2");
        interes3 = getIntent().getStringExtra("int3");

        storageReference = FirebaseStorage.getInstance().getReference();
        fotoEnt = findViewById(R.id.cargarFoto);

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
                } else {
                    crearInicioSesion(correo, contraseña);
                    Intent intent = new Intent(getApplicationContext(), HomeEntrenador.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == galeria && resultCode == RESULT_OK){

            uri = data.getData();
            fotoEnt.setImageURI(uri);

            carpetaFotos = storageReference.child("fotosEntrenador").child(uri.getLastPathSegment());
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
                    registrarEntrenador(entrenadorRef, usuario, correo, contraseña);
                }
            }
        });
    }

    public void registrarEntrenador(DatabaseReference tablaRef, FirebaseUser usuario, String correo, String contraseña){


        Map<String, String> entrenadores = new HashMap<>();

        entrenadores.put("nombre", nombre);
        entrenadores.put("edad", edad);
        entrenadores.put("equipoActual", equipoActual);
        entrenadores.put("añosActivo", añosActivo);
        entrenadores.put("telefono", telefono);
        entrenadores.put("interes1", interes1);
        entrenadores.put("interes2", interes2);
        entrenadores.put("interes3", interes3);
        entrenadores.put("correo", correo);
        entrenadores.put("contraseña", contraseña);
        entrenadores.put("foto" , descargarUri.toString());


        tablaRef.child(usuario.getUid()).setValue(entrenadores);
    }
}