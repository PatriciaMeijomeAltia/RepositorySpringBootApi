package com.API.listaTareas.service.impl;

//import com.API.listaTareas.dto.UsuarioDTO;
import com.API.listaTareas.exception.MensajeError;
import com.API.listaTareas.mapper.UsuarioMapper;
import com.API.listaTareas.model.Usuario;
import com.API.listaTareas.repository.UsuarioRepository;
import com.API.listaTareas.service.UsuarioService;
import com.baeldung.openapi.model.UsuarioDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {


    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UsuarioDTO crearUser(UsuarioDTO usuarioDTO) {

        // Convertir el DTO en entidad
        Usuario usuario = UsuarioMapper.INSTANCE.toEntity(usuarioDTO);

        // Guardar la entidad en la base de datos
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Convertir la entidad guardada de vuelta en DTO
        UsuarioDTO usuarioDTOResultado = UsuarioMapper.INSTANCE.toDto(usuarioGuardado);

        // Devolver el DTO como resultado
        return usuarioDTOResultado;
        //return UsuarioMapper.INSTANCE.toDto(usuarioRepository.save(UsuarioMapper.INSTANCE.toEntity(usuarioDTO)));
    }

    @Override
    public UsuarioDTO eliminarUser(Integer id) {

        Usuario buscarusuario=usuarioRepository.findById(id).get();

        usuarioRepository.delete(buscarusuario);

        UsuarioDTO resDTO=UsuarioMapper.INSTANCE.toDto(buscarusuario);

        return resDTO;
    }

    @Override
    public UsuarioDTO modificarUser(Integer Id, UsuarioDTO usuarioDTO) {

        // Mapear el DTO a la entidad
        Usuario usuario = UsuarioMapper.INSTANCE.toEntity(usuarioDTO);

        Usuario modUser = usuarioRepository.findById(Id).orElseThrow(() -> new MensajeError("No detectado: "+Id));

        if (usuario.getNombre() != null && !usuario.getNombre() .isEmpty())
            modUser.setNombre(usuario.getNombre());

        if (usuario.getTelefono()!= null && !usuario.getTelefono().isEmpty())
            modUser.setTelefono(usuario.getTelefono());

        // Guardar la entidad en el repositorio
        Usuario savedUsuario = usuarioRepository.save (modUser);

        // Mapear la entidad guardada de vuelta al DTO
        UsuarioDTO resultDto = UsuarioMapper.INSTANCE.toDto(savedUsuario);

        // Devuelve el DTO
        return resultDto;
    }

    @Override
    public List<UsuarioDTO> mostrarUser() {


        List<Usuario> listausers = usuarioRepository.findAll();

        if (listausers.isEmpty()) {
            throw new MensajeError("No hay usuarios disponibles");
        }

        // Mapear la entidad guardada de vuelta al DTO
        List<UsuarioDTO> resultDto = UsuarioMapper.INSTANCE.toDtoList(listausers);

        // Devuelve el DTO
        return resultDto;
    }
}
