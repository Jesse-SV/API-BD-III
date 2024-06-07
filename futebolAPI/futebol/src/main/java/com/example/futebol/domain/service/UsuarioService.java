package com.example.futebol.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.futebol.domain.dto.usuario.UsuarioRequestDTO;
import com.example.futebol.domain.dto.usuario.UsuarioResponseDTO;
import com.example.futebol.domain.exception.ResourceBadRequestException;
import com.example.futebol.domain.exception.ResourceNotFoundException;
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
            throw new ResourceNotFoundException("Não foi possível encontrar o usuário com o id: " + id);
        }
        return mapper.map(optUsuario.get(), UsuarioResponseDTO.class);
    }

    @Override
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dto) {
        senhaEmailObrigatorios(dto);
        Usuario usuario = mapper.map(dto, Usuario.class);
        //Encriptografar senha
        usuario = usuarioRepository.save(usuario);
        return mapper.map(usuario,UsuarioResponseDTO.class);
    }
    
    @Override
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        obterPorId(id);//O obter por Id já possui a exceção para caso o usuário não exista.
        senhaEmailObrigatorios(dto);
        Usuario usuario = mapper.map(dto, Usuario.class);
        usuario.setId(id);//Seta o id do usuário como o id referenciado na entrada
        usuario = usuarioRepository.save(usuario);
        return mapper.map(usuario, UsuarioResponseDTO.class);
    }

    @Override
    public void deletar(Long id) {
        Optional<Usuario> optUsuario = usuarioRepository.findById(id);
        if(optUsuario.isEmpty()){
            throw new ResourceNotFoundException("não foi possível encontrar o usuário com id:"+ id);
        }
    
        Usuario usuario = optUsuario.get();
        usuario.setDataInativacao(new Date());
        usuarioRepository.save(usuario);
    }
    public void senhaEmailObrigatorios(UsuarioRequestDTO dto){
        if((dto.getEmail() == null) || (dto.getSenha() == null)){
            throw new ResourceBadRequestException("Email e Senha são obrigatórios.");
        }
    }
    
}
