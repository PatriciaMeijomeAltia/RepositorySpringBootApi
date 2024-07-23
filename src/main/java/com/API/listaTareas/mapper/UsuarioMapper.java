package com.API.listaTareas.mapper;

//import com.API.listaTareas.dto.UsuarioDTO;
import com.API.listaTareas.model.Usuario;
import com.baeldung.openapi.model.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO toDto(Usuario usuario);

    Usuario toEntity(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> toDtoList(List<Usuario> usuarios);




}
