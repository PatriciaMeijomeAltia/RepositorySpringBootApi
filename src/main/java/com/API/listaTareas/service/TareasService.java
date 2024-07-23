package com.API.listaTareas.service;


//import com.API.listaTareas.dto.TareaDto;


import com.baeldung.openapi.model.TareaDto;

import java.util.List;

public interface TareasService {


    List <TareaDto> obtenerTareas();

    TareaDto obtenerTareaPorId(Integer id) ;

    TareaDto crearTarea(TareaDto tareaDto);

    TareaDto eliminarTarea(Integer id) ;

    TareaDto modificarTarea(Integer id, TareaDto tareaDto) ;



}
