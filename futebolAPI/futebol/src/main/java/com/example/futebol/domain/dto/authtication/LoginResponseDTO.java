package com.example.futebol.domain.dto.authtication;

import com.example.futebol.domain.dto.usuario.UsuarioResponseDTO;

public class LoginResponseDTO {
    private String token;
    private UsuarioResponseDTO usuario;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioResponseDTO usuario) {
        this.usuario = usuario;
    }

}
