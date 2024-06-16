package com.example.futebol.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.futebol.domain.model.Time;
import com.example.futebol.domain.model.Usuario;

public interface TimeRepository extends JpaRepository<Time,Long>{
    List<Time> findByUsuario(Usuario usuario);
}
