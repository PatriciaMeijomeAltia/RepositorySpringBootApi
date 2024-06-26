package com.API.listaTareas.Services;

import com.API.listaTareas.Modelo.Tareas;
import com.API.listaTareas.dto.TareaDto;

import java.util.List;

public interface TareasService {

    List <TareaDto> obtenerTareas();

    Object obtenerTareaPorId(Long id) ;

    String crearTarea(TareaDto tareaDto);

    String eliminarTarea(Long id) ;

    String modificarTarea(Long id,Tareas tarea) ;


}
