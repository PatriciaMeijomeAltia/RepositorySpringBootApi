package com.API.listaTareas.service;

import com.API.listaTareas.model.Tarea;

import java.util.List;

public interface TareasService {

    List <Tarea> obtenerTareas();

    Tarea obtenerTareaPorId(Long id) ;

    String crearTarea(Tarea tarea);

    Boolean eliminarTarea(Long id) ;

    String modificarTarea(Long id, Tarea tarea) ;


}
