package com.example.ejercicio1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.ejercicio1.models.Alumno;

import java.text.SimpleDateFormat;

public class Resultados extends AppCompatActivity {

    Alumno alumno;
    TextView tvNombre;
    TextView tvCuenta;
    TextView tvAnios;
    TextView tvCarrera;
    ImageView ivCarrera;
    ImageView ivAvatar;
    String[] carreras;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        //Recuperar arreglo de carreras
        carreras = getResources().getStringArray(R.array.carreras);

        //Asociar elementos de la vista
        tvNombre = findViewById(R.id.tvNombre);
        tvCuenta = findViewById(R.id.tvCuenta);
        tvAnios = findViewById(R.id.tvAnios);
        tvCarrera = findViewById(R.id.tvCarrera);
        ivCarrera = findViewById(R.id.ivCarrera);
        ivAvatar = findViewById(R.id.ivAvatar);

        //Recuperar alumno del Activity anterior
        Bundle bundle = getIntent().getExtras();

        //Verifica que bundle no este vacio
        if(bundle != null){
            //Instancia alumno con objeto almacenado en bundle
            alumno = (Alumno) bundle.getSerializable("alumno");

            //Mostrar datos del alumno en Activity
            tvNombre.setText(alumno.getNombCompleto());
            tvCuenta.setText(alumno.getNumCta());
            tvCarrera.setText(carreras[alumno.getCarrera()]);
            tvAnios.setText(alumno.getEdad()+getResources().getString(R.string.anios));

            //Mostrar avatar de genero
            cambiarAvatar(alumno.getGenero());

            //Mostrar imagen asociada a la carrera
            cambiarImagen(alumno.getCarrera());
        }
    }

    public void cambiarAvatar(char genero){
        switch (genero){
            case 'F':
                ivAvatar.setImageResource(R.drawable.avatar_f);
                break;
            case 'M':
                ivAvatar.setImageResource(R.drawable.avatar_m);
                break;
            default:
                break;
        }
    }

    public void cambiarImagen(int carrera){
        //Se usa la posicion de acuerdo al array de carreras
        switch (carrera){
            case 0:
                ivCarrera.setImageResource(R.drawable.aeroespacial);
                break;
            case 1:
                ivCarrera.setImageResource(R.drawable.ambiental);
                break;
            case 2:
                ivCarrera.setImageResource(R.drawable.civil);
                break;
            case 3:
                ivCarrera.setImageResource(R.drawable.minas);
                break;
            case 4:
                ivCarrera.setImageResource(R.drawable.electrica);
                break;
            case 5:
                ivCarrera.setImageResource(R.drawable.computacion);
                break;
            case 6:
                ivCarrera.setImageResource(R.drawable.biomedicos);
                break;
            case 7:
                ivCarrera.setImageResource(R.drawable.telecom);
                break;
            case 8:
                ivCarrera.setImageResource(R.drawable.geofisica);
                break;
            case 9:
                ivCarrera.setImageResource(R.drawable.geologica);
                break;
            case 10:
                ivCarrera.setImageResource(R.drawable.geomatica);
                break;
            case 11:
                ivCarrera.setImageResource(R.drawable.industrial);
                break;
            case 12:
                ivCarrera.setImageResource(R.drawable.mecanica);
                break;
            case 13:
                ivCarrera.setImageResource(R.drawable.mecatronica);
                break;
            case 14:
                ivCarrera.setImageResource(R.drawable.petrolera);
                break;
            default:
                break;
        }
    }

    public void salir(View view) {
        //Intent para indicar el siguiente activity
        Intent intent = new Intent(this, MainActivity.class);
        //Limpiar pila de activies
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Iniciar Activity de formulario
        startActivity(intent);
        //Animacion para regresar al formulario
        Animatoo.animateSwipeRight(this);
    }
}