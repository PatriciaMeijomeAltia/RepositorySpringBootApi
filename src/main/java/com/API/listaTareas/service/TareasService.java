package com.API.listaTareas.service;


import com.API.listaTareas.dto.TareaDto;


import java.util.List;

public interface TareasService {


    List <TareaDto> obtenerTareas();

    TareaDto obtenerTareaPorId(Long id) ;

    TareaDto crearTarea(TareaDto tareaDto);

    TareaDto eliminarTarea(Long id) ;

    TareaDto modificarTarea(Long id, TareaDto tareaDto) ;



}
