package com.example.futebol.domain.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Time{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTime")
    private String id;
    private String cidade;
    private String estado;
    private String pais;
    private String divisao;
    @Column(nullable = false)
    private Date anoFundacao;
    @OneToMany(mappedBy = "jogador")
    private List<Jogador> jogadores;
    public String getId() {
        return id;
    }
    public void setId(String id) {
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
    public List<Jogador> getJogadores() {
        return jogadores;
    }
    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
    
}