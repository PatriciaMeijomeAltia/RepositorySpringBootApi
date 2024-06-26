package com.API.listaTareas.Services.Impl;

import com.API.listaTareas.Modelo.Tareas;
import com.API.listaTareas.Services.TareasService;
import com.API.listaTareas.dto.TareaDto;
import com.API.listaTareas.mapper.TareaMapper;
import com.API.listaTareas.repositorio.TareasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareasServiceImpl implements TareasService {

    @Autowired
    private TareasRepository tareasRepository;


    @Override
    public List<TareaDto> obtenerTareas() {

        List<Tareas> tareas = tareasRepository.findAll();
        return TareaMapper.INSTANCE.toDtoList(tareas);

    }

    @Override
    public Object obtenerTareaPorId(Long id) {

        Optional<Tareas> tareaOptional = tareasRepository.findById(id);

        if (tareaOptional.isPresent()) {
           // Tareas tarea = tareaOptional.get();
            return tareaOptional.get();
        } else {
            return "Tarea no encontrada";
        }
    }

    @Override
    public String crearTarea(TareaDto tareaDto) {

        Tareas tarea = TareaMapper.INSTANCE.toEntity(tareaDto);
        tareasRepository.save(tarea);
        return "Tarea creada";

    }

    @Override
    public String eliminarTarea(Long id) {

        if (tareasRepository.existsById(id)) {

            tareasRepository.deleteById(id);
            return "Tarea eliminada";
        }
        else {
            return "Id no esxit";
        }
    }

    @Override
    public String modificarTarea(Long id,Tareas tarea) {

        Tareas modTarea = tareasRepository.findById(id).get();

     if (modTarea!= null)
      {

          if (tarea.getNombreTarea()!= null && !tarea.getNombreTarea().isEmpty())
            modTarea.setNombreTarea(tarea.getNombreTarea());

          if (tarea.getDescripcionTarea()!= null && !tarea.getDescripcionTarea().isEmpty())
              modTarea.setDescripcionTarea(tarea.getDescripcionTarea());

          if (tarea.getEstado()!= null && !tarea.getEstado().isEmpty())
              modTarea.setEstado(tarea.getEstado());

          if (tarea.getFechaFin()!= null && !tarea.getFechaFin().isEmpty())
              modTarea.setFechaFin(tarea.getFechaFin());

          if (tarea.getFechaInicio()!= null && !tarea.getFechaInicio().isEmpty())
              modTarea.setFechaInicio(tarea.getFechaInicio());

          if (tarea.getPersonaAsignada()!= null && !tarea.getPersonaAsignada().isEmpty())
              modTarea.setPersonaAsignada(tarea.getPersonaAsignada());


          tareasRepository.save(modTarea);
          return  "Tarea actualizada";

      }
      else {
          return "No se ha actualizado";
      }


    }
}
