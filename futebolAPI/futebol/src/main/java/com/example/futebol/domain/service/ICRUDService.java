package com.example.futebol.domain.service;

import java.util.List;

public interface ICRUDService<Request, Response>{
    List<Response> obterTodos();
    Response obterPorId();
    Response cadastrar(Request dto);
    Response atualizar(Long id, Request dto);
    void deletar(Long id);
}
