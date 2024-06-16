package com.example.futebol.domain.dto.time;

import java.util.Date;

public class TimeResponseDTO {
    private Long id;
    private String cidade;
    private String estado;
    private String pais;
    private String divisao;
    private Date anoFundacao;
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
    
}
