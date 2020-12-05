package com.example.ejercicio1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ejercicio1.dialog.DatePickerFragment;
import com.example.ejercicio1.models.Alumno;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText etNombre;
    EditText etApellido;
    EditText etCuenta;
    EditText etFecha;
    String carrera;
    Spinner spCarreras;
    Date fecha;
    Date fechaActual = new Date();      //Inicilizacion con fecha actual

    Alumno alumno;

    //Formateador para la fecha
    SimpleDateFormat formatoFecha;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Objeto para dar formato a la fecha
        formatoFecha = new SimpleDateFormat(getResources().getString(R.string.fecha).toString());

        //Asociacion de campos de texto
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCuenta = findViewById(R.id.etCuenta);
        etFecha = findViewById(R.id.etFecha);
        spCarreras = findViewById(R.id.spCarreras);

        //Asociar un metodo listener a etFecha
        etFecha.setOnClickListener(this);
        //Asociar un metodo listener al spinner de carreras
        spCarreras.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        //Reconocer elemento donde se dio clic
        switch (v.getId()){
            //Para clic sobre etFecha
            case R.id.etFecha:
                //Muestra el DatePicker
                showDatePickerDialog();
        }
    }

    //Metodo para capturar la fecha y mostrar DatePicker
    private void showDatePickerDialog(){
        //Declaracion de un nuevo Fragment
        DatePickerFragment nuevoFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Formateo de la cadena de fecha
                String f = "%02d/%02d/%d";
                final String format = String.format(f, dayOfMonth, month+1, year);
                //Coloca fecha seleccionada en editText
                etFecha.setText(format);
            }
        });{
            nuevoFragment.show(getSupportFragmentManager(), "view");
        }
    }

    //Metodo para manejar seleccion del Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String[] carreras = getResources().getStringArray(R.array.carreras);
        carrera = carreras[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void enviar(View view) throws ClassNotFoundException {
        if(this.validar()){
            //Intent para indicar la siguiente Activity
            Intent intent = new Intent(this, Resultados.class);

            //Creacion de alumno con datos de formulario
            alumno = new Alumno(etNombre.getText().toString(), etApellido.getText().toString(),
                    etCuenta.getText().toString(), fecha, carrera);

            //Instancia de un Bundle
            Bundle bundle = new Bundle();
            //Almacenar alumno en bundle
            bundle.putSerializable("alumno", alumno);
            //Enviar bundle al inten
            intent.putExtras(bundle);

            //Iniciar Activity de resultados
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Revisa tus datos", Toast.LENGTH_LONG).show();
        }
    }

    //Metodo para validar datos del formulario
    public boolean validar() throws ClassNotFoundException {
        if(etNombre.getText().toString().equals("")){
            etNombre.setError(getResources().getString(R.string.errorNombre));
            return false;
        }
        if(etApellido.getText().toString().equals("")){
            etApellido.setError(getResources().getString(R.string.errorApellido));
            return false;
        }
        if(etCuenta.getText().toString().equals("") || etCuenta.length() < 9){
            etCuenta.setError(getResources().getString(R.string.errorCuenta));
            return false;
        }
        if(etFecha.getText().toString().equals("")){
            etFecha.setError(getResources().getString(R.string.fechaVacia));
            return false;
        }
        //Creacion de objeto fecha
        try {
            fecha = formatoFecha.parse(etFecha.getText().toString());
            //Validar que feacha ingresada sea anterior a fecha actual
            if(fecha.after(fechaActual)){
                Toast.makeText(this, getResources().getString(R.string.errorFecha), Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}