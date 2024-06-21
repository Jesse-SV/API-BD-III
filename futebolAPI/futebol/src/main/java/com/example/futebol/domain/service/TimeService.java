package com.example.futebol.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.futebol.domain.dto.time.TimeRequestDTO;
import com.example.futebol.domain.dto.time.TimeResponseDTO;
import com.example.futebol.domain.exception.ResourceBadRequestException;
import com.example.futebol.domain.exception.ResourceNotFoundException;
import com.example.futebol.domain.model.Time;
import com.example.futebol.domain.model.Usuario;
import com.example.futebol.domain.repository.TimeRepository;

@Service
public class TimeService implements ICRUDService<TimeRequestDTO,TimeResponseDTO> {
    @Autowired
    private TimeRepository timeRepository;
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public List<TimeResponseDTO> obterTodos() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Time> times = timeRepository.findByUsuario(usuario); 
        return times.stream().map(time -> modelMapper.map(time,TimeResponseDTO.class)).collect(Collectors.toList());
    }
    @Override
    public TimeResponseDTO obterPorId(Long id) {
        Optional<Time> optTime = timeRepository.findById(id);
        if(optTime.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível encontrar o Time com o id especificado");
        }
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(optTime.get().getUsuario().getId() != usuario.getId()){
            throw new ResourceNotFoundException("O id de usuário: " + id + " não pertence a estes times.");
        }
        return modelMapper.map(optTime.get(),TimeResponseDTO.class);
    }
    @Override
    public TimeResponseDTO cadastrar(TimeRequestDTO dto) {
        validarTime(dto);
        Time time = modelMapper.map(dto, Time.class);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        time.setId(null);
        time.setNome(dto.getNome());
        time.setCidade(dto.getCidade());
        time.setDivisao(dto.getDivisao());
        time.setEstado(dto.getEstado());
        time.setPais(dto.getPais());
        time.setAnoFundacao(dto.getAnoFundacao());
        time.setUsuario(usuario);
        time = timeRepository.save(time);
        return modelMapper.map(time, TimeResponseDTO.class);
    }
    @Override
    public TimeResponseDTO atualizar(Long id, TimeRequestDTO dto) {
        TimeResponseDTO timeBanco = obterPorId(id);
        validarTime(dto);
        Time time = modelMapper.map(dto,Time.class);
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        time.setId(null);
        time.setNome(dto.getNome());
        time.setCidade(dto.getCidade());
        time.setDivisao(dto.getDivisao());
        time.setEstado(dto.getEstado());
        time.setPais(dto.getPais());
        time.setUsuario(usuario);
        time.setAnoFundacao(time.getAnoFundacao());
        time = timeRepository.save(time);
        return modelMapper.map(time, TimeResponseDTO.class);
    }
    @Override
    public void deletar(Long id) {
        obterPorId(id);
        timeRepository.deleteById(id);
    }
    public void validarTime(TimeRequestDTO dto){
        if((dto.getNome() == null) || (dto.getCidade() == null) 
        || (dto.getDivisao() == null) ||  (dto.getPais() == null) || (dto.getAnoFundacao() == null)){
            throw new ResourceBadRequestException("Cadastro ou alteração de Time invalido!");
        }
    }


}
