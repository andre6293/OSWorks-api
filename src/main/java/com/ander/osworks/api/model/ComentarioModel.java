package com.ander.osworks.api.model;

import java.time.OffsetDateTime;

public class ComentarioModel {

    private Long id;
    private String descricao;
    private OffsetDateTime dataenvio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public OffsetDateTime getDataenvio() {
        return dataenvio;
    }

    public void setDataenvio(OffsetDateTime dataenvio) {
        this.dataenvio = dataenvio;
    }
}
