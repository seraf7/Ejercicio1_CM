package com.example.ejercicio1.models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.util.Date;

public class Alumno implements Serializable {
    private String nombre;
    private String apellido;
    private String numCta;
    private Date fechaNac;
    private int carrera;
    private char genero;

    public Alumno(String nombre, String apellido, String numCta, Date fechaNac, int carrera, char genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numCta = numCta;
        this.fechaNac = fechaNac;
        this.carrera = carrera;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumCta() {
        return numCta;
    }

    public void setNumCta(String numCta) {
        this.numCta = numCta;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getCarrera() {
        return carrera;
    }

    public void setCarrera(int carrera) {
        this.carrera = carrera;
    }

    public char getGenero() {return genero;}

    public void setGenero(char genero) {this.genero = genero;}

    public String getNombCompleto(){
        return this.getNombre() + " " +this.getApellido();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getEdad(){
        LocalDate fechaActual = LocalDate.now();
        //Calcular años entre dos fechas
        Period edad = Period.between(this.getFechaNac().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), fechaActual);
        return edad.getYears();
    }
}
