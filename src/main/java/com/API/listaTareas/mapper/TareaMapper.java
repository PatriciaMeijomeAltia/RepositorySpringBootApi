package com.API.listaTareas.mapper;

import com.API.listaTareas.Modelo.Tareas;
import com.API.listaTareas.dto.TareaDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TareaMapper {

    TareaMapper INSTANCE = Mappers.getMapper(TareaMapper.class);

    Tareas toEntity(TareaDto tareaDto);

    TareaDto toDTO(Tareas tareas);

    List<TareaDto> toDtoList(List<Tareas> tareas);
}
