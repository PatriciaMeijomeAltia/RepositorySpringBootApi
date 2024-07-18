package com.API.listaTareas.service.impl;

import com.API.listaTareas.dto.UsuarioDTO;
import com.API.listaTareas.mapper.UsuarioMapper;
import com.API.listaTareas.model.Usuario;
import com.API.listaTareas.repository.UsuarioRepository;
import com.API.listaTareas.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioDTO crearUser(UsuarioDTO usuarioDTO) {

        return UsuarioMapper.INSTANCE.toDto(usuarioRepository.save(UsuarioMapper.INSTANCE.toEntity(usuarioDTO)));
    }

    @Override
    public UsuarioDTO eliminarUser(Long id) {

        Usuario buscarusuario=usuarioRepository.findById(id).get();

        usuarioRepository.delete(buscarusuario);

        UsuarioDTO resDTO=UsuarioMapper.INSTANCE.toDto(buscarusuario);

        return resDTO;
    }

    @Override
    public UsuarioDTO modificarUser(Long Id, UsuarioDTO usuarioDTO) {

        // Mapear el DTO a la entidad
        Usuario usuario = UsuarioMapper.INSTANCE.toEntity(usuarioDTO);

        Usuario modUser = usuarioRepository.findById(Id).orElseThrow(() -> new NoSuchElementException("No detectado: "+Id));

        if (usuario.getNombreUser() != null && !usuario.getNombreUser() .isEmpty())
            modUser.setNombreUser(usuario.getNombreUser());

        if (usuario.getTelefonoUser()!= null && !usuario.getTelefonoUser().isEmpty())
            modUser.setTelefonoUser(usuario.getTelefonoUser());

        // Guardar la entidad en el repositorio
        Usuario savedUsuario = usuarioRepository.save (usuario);

        // Mapear la entidad guardada de vuelta al DTO
        UsuarioDTO resultDto = UsuarioMapper.INSTANCE.toDto(savedUsuario);

        // Devuelve el DTO
        return resultDto;
    }

    @Override
    public List<UsuarioDTO> mostrarUser() {


        List<Usuario> listausers = usuarioRepository.findAll();

        // Mapear la entidad guardada de vuelta al DTO
        List<UsuarioDTO> resultDto = UsuarioMapper.INSTANCE.toDtoList(listausers);

        // Devuelve el DTO
        return resultDto;
    }
}
