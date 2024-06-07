package com.example.futebol.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.futebol.common.ConversorData;
import com.example.futebol.domain.exception.ResourceBadRequestException;
import com.example.futebol.domain.exception.ResourceNotFoundException;
import com.example.futebol.domain.model.ErroResposta;

public class RestExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroResposta> handlerResourceNotFoundException(ResourceNotFoundException ex){
        String dataHora = ConversorData.converterParaFormatoDataHora(new Date());
        ErroResposta erro =  new ErroResposta(dataHora, 
        HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage());
        return new ResponseEntity<>(erro,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ErroResposta> handlerBadRequestResourceNotFoundException
    (ResourceBadRequestException ex){
        String dataHora = ConversorData.converterParaFormatoDataHora(new Date());
        ErroResposta erro =  new ErroResposta(dataHora, 
        HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> handlerRequestException(Exception ex){
        String dataHora = ConversorData.converterParaFormatoDataHora(new Date());
        ErroResposta erro = new ErroResposta(dataHora, HttpStatus.INTERNAL_SERVER_ERROR.value(), 
        "Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(erro,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
