package com.API.listaTareas.service.impl;


//import com.API.listaTareas.dto.TareaDto;
import com.API.listaTareas.mapper.TareaMapper;
import com.API.listaTareas.model.Tarea;
import com.API.listaTareas.service.TareasService;
import com.API.listaTareas.repository.TareasRepository;
import com.baeldung.openapi.model.TareaDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return TareaMapper.INSTANCE.toDtoList(tareas);

    }

    @Override

    public TareaDto obtenerTareaPorId(Integer id) {

        Optional<Tarea> tareaOptional = tareasRepository.findById(id);

        Tarea tarea = tareaOptional.orElseThrow(() ->
                new NoSuchElementException("Tarea con el ID :" + id+ "no existe"));

        return TareaMapper.INSTANCE.toDTO(tarea);


    }

    @Override
    public TareaDto crearTarea(TareaDto tareaDto) {

        return TareaMapper.INSTANCE.toDTO(tareasRepository.save(TareaMapper.INSTANCE.toEntity(tareaDto)));
        // return "Tarea creada";

    }



    @Override
    public TareaDto eliminarTarea(Integer id) {

        Tarea elimarTarea = tareasRepository.findById(id).get();

        tareasRepository.delete(elimarTarea);
        return TareaMapper.INSTANCE.toDTO(elimarTarea);
    }


    @Override
    public TareaDto modificarTarea(Integer id, TareaDto tareaDto) {

        Tarea tarea = TareaMapper.INSTANCE.toEntity(tareaDto);
        Tarea modTarea = tareasRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No detectado: "+id));


        if (tarea.getCompletada() != null && !tarea.getCompletada())
            modTarea.setCompletada(tarea.getCompletada());

        if (tarea.getDescripcion() != null && !tarea.getDescripcion().isEmpty())
            modTarea.setDescripcion(tarea.getDescripcion());


        return TareaMapper.INSTANCE.toDTO(tareasRepository.save(modTarea));



    }
}