package br.com.frederico.desafio.model;

import java.time.LocalDateTime;

public abstract class Produtor {

    private Long id;

    private LocalDateTime dataHora;

    public abstract String getTipoProdutor();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return this.dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}