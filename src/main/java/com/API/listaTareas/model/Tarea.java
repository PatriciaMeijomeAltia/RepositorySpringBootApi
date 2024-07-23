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
    private Integer id;
    private String descripcion;
    private Boolean completada;



    @ManyToOne
    @JoinColumn(name = "idUSer") // Nombre de la columna en la tabla Tarea que es clave externa
    private Usuario usuario;

}
