package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;

public class sensor extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int derecha = 0;
    int izquierda = 0;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        layout = findViewById(R.id.personajes);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (sensor == null){
            Intent intent = new Intent(getApplicationContext(), editarPerfil.class);
            startActivity(intent);
        }

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                if (x<-5 && derecha == 0){
                    derecha++;
                    layout.setBackgroundResource(R.drawable.foto1);
                } else if (x>5 && derecha==1){
                    izquierda++;
                    layout.setBackgroundResource(R.drawable.foto2);
                }

                if (derecha == 1 && izquierda == 1){
                    derecha = 0;
                    izquierda = 0;
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        iniciar();
    }

    public void iniciar(){
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}