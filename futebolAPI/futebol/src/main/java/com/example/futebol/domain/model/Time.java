package com.example.futebol.domain.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "Time")
public class Time{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTime")
    private Long id;
    private String cidade;
    private String estado;
    private String pais;
    private String divisao;
    @Column(nullable = false)
    private Date anoFundacao;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getDivisao() {
        return divisao;
    }
    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }
    public Date getAnoFundacao() {
        return anoFundacao;
    }
    public void setAnoFundacao(Date anoFundacao) {
        this.anoFundacao = anoFundacao;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}