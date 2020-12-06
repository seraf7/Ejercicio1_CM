package com.example.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.Timer;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Nuevo thread
        Thread timer = new Thread(){
            //Definicion del metodo run
            public void run(){
                try{
                    //Mantener el Splash 1s
                    sleep(1000);
                }catch (Exception e){

                }finally {
                    //Intent para indicar el Activity siguiente
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    //Iniciar la nueva Activity
                    startActivity(intent);
                    //Finalizar Splash y sacarla de memoria
                    finish();
                }
            }
        };
        //Ejecutar el hilo definido
        timer.start();
    }
}