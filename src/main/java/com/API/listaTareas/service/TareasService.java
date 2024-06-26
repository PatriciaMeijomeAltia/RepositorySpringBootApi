package com.API.listaTareas.service;

import com.API.listaTareas.dto.TareaDto;
import com.API.listaTareas.model.Tarea;

import java.util.List;

public interface TareasService {

    List <TareaDto> obtenerTareas();

    Tarea obtenerTareaPorId(Long id) ;

    String crearTarea(TareaDto tareaDto);

    Boolean eliminarTarea(Long id) ;

    String modificarTarea(Long id, Tarea tarea) ;


}
