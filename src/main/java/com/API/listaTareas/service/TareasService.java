package com.API.listaTareas.service;

<<<<<<< HEAD
import com.API.listaTareas.dto.TareaDto;
=======
>>>>>>> master
import com.API.listaTareas.model.Tarea;

import java.util.List;

public interface TareasService {


    List <TareaDto> obtenerTareas();

    TareaDto obtenerTareaPorId(Long id) ;

    String crearTarea(TareaDto tareaDto);

    Boolean eliminarTarea(Long id) ;

    String modificarTarea(Long id, TareaDto tareaDto) ;



}
