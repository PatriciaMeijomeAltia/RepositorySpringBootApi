package com.API.listaTareas.service.impl;


//import com.API.listaTareas.dto.TareaDto;
import com.API.listaTareas.exception.MensajeError;
import com.API.listaTareas.mapper.TareaMapper;
import com.API.listaTareas.model.Tarea;
import com.API.listaTareas.service.TareasService;
import com.API.listaTareas.repository.TareasRepository;
import com.baeldung.openapi.model.TareaDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TareasServiceImpl implements TareasService {

    @Autowired
    private TareasRepository tareasRepository;


    @Override
    public List<TareaDto> obtenerTareas() {

        List<Tarea> tareas = tareasRepository.findAll();

        if (tareas == null || tareas.isEmpty()) {
            throw new MensajeError("No se encontraron tareas");
        }
        return TareaMapper.INSTANCE.toDtoList(tareas);

    }

    @Override
    public TareaDto obtenerTareaPorId(Integer id) {

        Tarea tarea = tareasRepository.findById(id).orElseThrow(() -> new MensajeError("Tarea con el ID :" + id+ " no existe"));

        return TareaMapper.INSTANCE.toDTO(tarea);


    }

    @Override
    public TareaDto crearTarea(TareaDto tareaDto) {

        return TareaMapper.INSTANCE.toDTO(tareasRepository.save(TareaMapper.INSTANCE.toEntity(tareaDto)));


    }



    @Override
    public TareaDto eliminarTarea(Integer id) {

        Tarea eliminarTarea = tareasRepository.findById(id)
                .orElseThrow(() -> new MensajeError("Tarea no encontrada"));

        //Tarea elimarTarea = tareasRepository.findById(id).get();

        tareasRepository.delete(eliminarTarea);
        return TareaMapper.INSTANCE.toDTO(eliminarTarea);
    }


    @Override
    public TareaDto modificarTarea(Integer id, TareaDto tareaDto) {

        Tarea tarea = TareaMapper.INSTANCE.toEntity(tareaDto);
        Tarea modTarea = tareasRepository.findById(id).orElseThrow(() -> new MensajeError("No detectado: "+id));


        if (tarea.getCompletada() != null )
            modTarea.setCompletada(tarea.getCompletada());

        if (tarea.getDescripcion() != null && !tarea.getDescripcion().isEmpty())
            modTarea.setDescripcion(tarea.getDescripcion());


        return TareaMapper.INSTANCE.toDTO(tareasRepository.save(modTarea));

    }
}