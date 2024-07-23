package com.API.listaTareas.mapper;


//import com.API.listaTareas.dto.TareaDto;
import com.API.listaTareas.model.Tarea;
import com.baeldung.openapi.model.TareaDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TareaMapper {

    TareaMapper INSTANCE = Mappers.getMapper(TareaMapper.class);

    Tarea toEntity(TareaDto tareaDto);

    TareaDto toDTO(Tarea tareas);

    List<TareaDto> toDtoList(List<Tarea> tareas);
}
