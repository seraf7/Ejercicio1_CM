package com.example.ejercicio1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicio1.models.Alumno;

import java.text.SimpleDateFormat;

public class Resultados extends AppCompatActivity {

    Alumno alumno;
    TextView tvNombre;
    TextView tvCuenta;
    TextView tvAnios;
    TextView tvCarrera;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        //Asociar elementos de la vista
        tvNombre = findViewById(R.id.tvNombre);
        tvCuenta = findViewById(R.id.tvCuenta);
        tvAnios = findViewById(R.id.tvAnios);
        tvCarrera = findViewById(R.id.tvCarrera);

        //Recuperar alumno del Activity anterior
        Bundle bundle = getIntent().getExtras();

        //Verifica que bundle no este vacio
        if(bundle != null){
            //Instancia alumno con objeto almacenado en bundle
            alumno = (Alumno) bundle.getSerializable("alumno");

            //Mostrar datos del alumno en Activity
            tvNombre.setText(alumno.getNombCompleto());
            tvCuenta.setText(alumno.getNumCta());
            tvCarrera.setText(alumno.getCarrera());
            tvAnios.setText(alumno.getEdad()+getResources().getString(R.string.anios));
        }
    }
}