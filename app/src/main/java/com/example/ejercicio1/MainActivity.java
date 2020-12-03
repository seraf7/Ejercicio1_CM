package com.example.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicio1.dialog.DatePickerFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText etFecha;
    TextView tvCarrera;
    Spinner spCarreras;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asociar edit text
        etFecha = findViewById(R.id.etFecha);
        //Asociar un metodo listener a etFecha
        etFecha.setOnClickListener(this);
        //Asociar un metodo listener a spCarreras
        spCarreras = findViewById(R.id.spCarreras);
        spCarreras.setOnItemSelectedListener(this);
        tvCarrera = findViewById(R.id.tvCarrera);
    }

    @Override
    public void onClick(View v) {
        //Reconocer elemento donde se dio clic
        switch (v.getId()){
            //Para etFecha
            case R.id.etFecha:
                //Muestra el DatePicker
                showDatePickerDialog();
        }
    }

    //Metodo para capturar la fecha y mostrar DatePicker
    private void showDatePickerDialog(){
        DatePickerFragment nuevoFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final String fecha = dayOfMonth + "/" + month + "/" + year;
                etFecha.setText(fecha);
            }
        });{
            nuevoFragment.show(getSupportFragmentManager(), "view");
        }
    }

    //Metodo para manejar seleccion del Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] carreras = getResources().getStringArray(R.array.carreras);
        tvCarrera.setText(carreras[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}