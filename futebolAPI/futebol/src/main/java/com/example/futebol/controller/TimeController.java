package com.example.futebol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.futebol.domain.dto.time.TimeRequestDTO;
import com.example.futebol.domain.dto.time.TimeResponseDTO;
import com.example.futebol.domain.service.TimeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/futebol/times")
public class TimeController {
    @Autowired
    private TimeService timeService;

    @GetMapping
    public ResponseEntity<List<TimeResponseDTO>> obterTodos(){
        return ResponseEntity.ok(timeService.obterTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeResponseDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(timeService.obterPorId(id));
    }
    @PostMapping
    public ResponseEntity<TimeResponseDTO> cadastrar(@RequestBody TimeRequestDTO dto){
        TimeResponseDTO time = timeService.cadastrar(dto);
        return new ResponseEntity<>(time,HttpStatus.CREATED);
    }
    @PutMapping("{id}")
    public ResponseEntity<TimeResponseDTO> atualizar(@PathVariable Long id,@RequestBody TimeRequestDTO dto){
        TimeResponseDTO time = timeService.atualizar(id, dto);
        return new ResponseEntity<TimeResponseDTO>(time,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        timeService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
