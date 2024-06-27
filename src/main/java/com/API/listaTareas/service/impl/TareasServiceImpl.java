package com.API.listaTareas.service.impl;


import com.API.listaTareas.dto.TareaDto;
import com.API.listaTareas.mapper.TareaMapper;
import com.API.listaTareas.model.Tarea;
import com.API.listaTareas.service.TareasService;
import com.API.listaTareas.repository.TareasRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
//import java.util.Optional;

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

    public TareaDto obtenerTareaPorId(Long id) {

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
    public Boolean eliminarTarea(Long id) {

       return tareasRepository.findById(id).map(tarea -> {
            tareasRepository.delete(tarea);
            return true;
        }).orElse(false);

    }


    @Override

    public TareaDto modificarTarea(Long id, TareaDto tareaDto) {

        Tarea tarea = TareaMapper.INSTANCE.toEntity(tareaDto);
        Tarea modTarea = tareasRepository.findById(id).orElseThrow(() -> new NoSuchElementException("error "+id));


         if (tarea.getNombreTarea() != null && !tarea.getNombreTarea().isEmpty())
             modTarea.setNombreTarea(tarea.getNombreTarea());

         if (tarea.getDescripcionTarea() != null && !tarea.getDescripcionTarea().isEmpty())
             modTarea.setDescripcionTarea(tarea.getDescripcionTarea());

         if (tarea.getEstado() != null && !tarea.getEstado().isEmpty())
             modTarea.setEstado(tarea.getEstado());

         if (tarea.getFechaFin() != null && !tarea.getFechaFin().isEmpty())
             modTarea.setFechaFin(tarea.getFechaFin());

         if (tarea.getFechaInicio() != null && !tarea.getFechaInicio().isEmpty())
             modTarea.setFechaInicio(tarea.getFechaInicio());

         if (tarea.getPersonaAsignada() != null && !tarea.getPersonaAsignada().isEmpty())
             modTarea.setPersonaAsignada(tarea.getPersonaAsignada());


         return TareaMapper.INSTANCE.toDTO(tareasRepository.save(modTarea));



    }
}
