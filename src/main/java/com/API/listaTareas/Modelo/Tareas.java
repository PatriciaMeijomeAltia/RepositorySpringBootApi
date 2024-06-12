package com.API.listaTareas.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tareas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarea;
    private String nombreTarea;
    private String descripcionTarea;
    private String estado;
    private String personaAsignada;
    private String fechaInicio;
    private String fechaFin;


    // Getters y setters

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public void setDescripcionTarea(String descripcionTarea) {
        this.descripcionTarea = descripcionTarea;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPersonaAsignada() {
        return personaAsignada;
    }

    public void setPersonaAsignada(String personaAsignada) {
        this.personaAsignada = personaAsignada;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Tareas() {

    }

    public Tareas(String nombreTarea, String descripcionTarea, String estado, String personaAsignada, String fechaInicio, String fechaFin) {
        this.nombreTarea = nombreTarea;
        this.descripcionTarea = descripcionTarea;
        this.estado = estado;
        this.personaAsignada = personaAsignada;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
}
