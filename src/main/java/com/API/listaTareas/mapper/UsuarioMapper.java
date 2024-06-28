package com.API.listaTareas.mapper;

import com.API.listaTareas.dto.TareaDto;
import com.API.listaTareas.dto.UsuarioDTO;
import com.API.listaTareas.model.Tarea;
import com.API.listaTareas.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO toDto(Usuario usuario);

    Usuario toEntity(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> toDtoList(List<Usuario> usuarios);


}
