package com.example.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.ejercicio1.dialog.DatePickerFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asociar edit text
        etFecha = (EditText) findViewById(R.id.etFecha);
        //Asociar un metodo listener
        etFecha.setOnClickListener(this);
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
}