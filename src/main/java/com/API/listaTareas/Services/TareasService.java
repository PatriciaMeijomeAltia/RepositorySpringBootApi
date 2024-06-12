package com.API.listaTareas.Services;

import com.API.listaTareas.Modelo.Tareas;

import java.util.List;

public interface TareasService {

    List <Tareas> obtenerTareas();

    String obtenerTareaPorId(Long id) ;

    String crearTarea(Tareas tarea);

    String eliminarTarea(Long id) ;

    String modificarTarea(Long id,Tareas tarea) ;


}
