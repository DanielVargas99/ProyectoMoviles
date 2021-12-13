package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
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

public class registroEntrenador2 extends AppCompatActivity {

    String interes1;
    String interes2;
    String interes3;
    String nombre;
    String edad;
    String equipo;
    String añosActivo;
    String telefono;
    EditText int1;
    EditText int2;
    EditText int3;
    Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar2);

        int1 = findViewById(R.id.campo1);
        int1.setHint("Posicion de interes 1");
        int2 = findViewById(R.id.campo3);
        int2.setHint("Posicion de interes 2");
        int3 = findViewById(R.id.campo5);
        int3.setHint("Posicion de interes 3");

        nombre = getIntent().getStringExtra("nombre");
        edad = getIntent().getStringExtra("edad");
        equipo = getIntent().getStringExtra("equipo");
        añosActivo = getIntent().getStringExtra("activo");
        telefono = getIntent().getStringExtra("telefono");

        siguiente = findViewById(R.id.boton);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interes1 = int1.getText().toString();
                interes2 = int2.getText().toString();
                interes3 = int3.getText().toString();
                Intent intent = new Intent(getApplicationContext(), registroEntrenador3.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("edad", edad);
                intent.putExtra("equipo", equipo);
                intent.putExtra("activo", añosActivo);
                intent.putExtra("telefono", telefono);
                intent.putExtra("int1", interes1);
                intent.putExtra("int2", interes2);
                intent.putExtra("int3", interes3);
                startActivity(intent);
            }
        });
    }
}