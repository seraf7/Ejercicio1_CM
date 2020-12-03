package com.example.ejercicio1.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment{

    //Definir un Listener
    private DatePickerDialog.OnDateSetListener listener;

    //Definicion de Fragment que usa el listener
    public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setListener(listener);
        return  fragment;
    }

    //Metodo para establecer el listner
    public void setListener(DatePickerDialog.OnDateSetListener listener){
        this.listener = listener;
    }

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Seleccionar fecha actual en DatePicker
        final Calendar c = Calendar.getInstance();
        //Variables para la fecha
        int anio = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        //Crear instancia del DatePicker y devolverla
        return new DatePickerDialog(getActivity(), listener, anio, mes, dia);
    }

}
