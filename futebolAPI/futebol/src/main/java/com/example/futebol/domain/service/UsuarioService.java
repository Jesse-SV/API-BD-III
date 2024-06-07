package com.example.futebol.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.futebol.domain.dto.usuario.UsuarioRequestDTO;
import com.example.futebol.domain.dto.usuario.UsuarioResponseDTO;
import com.example.futebol.domain.model.Usuario;
import com.example.futebol.domain.repository.UsuarioRepository;
@Service
public class UsuarioService implements ICRUDService<UsuarioRequestDTO, UsuarioResponseDTO> {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public List<UsuarioResponseDTO> obterTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        /*usuario.stream irá criar um fluxo de dados relacionado a lista usuarios,
         o map(usuario-> mapper.map()) irá transformar cada objeto Usuario da lista em um 
         UsuarioResponseDTO, e por fim tudo será coletado e retornado como uma lista.*/
        return usuarios.stream().map(usuario -> mapper.map(usuario, UsuarioResponseDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO obterPorId(Long id) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        if(optUsuario.isEmpty()){
            // lançar exceção 
        }
        return mapper.map(optUsuario.get(), UsuarioResponseDTO.class);
    }

    @Override
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cadastrar'");
    }

    @Override
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }
    
}
