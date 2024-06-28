package com.API.listaTareas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarea;
    private String nombreTarea;
    private String descripcionTarea;
    private String estado;
    private String personaAsignada;
    private String fechaInicio;
    private String fechaFin;



    @ManyToOne
    @JoinColumn(name = "idUSer") // Nombre de la columna en la tabla Tarea que es clave externa
    private Usuario usuario;

}
