package br.com.frederico.desafio.model;

import java.time.LocalDateTime;

public class ProdutorVoz extends Produtor {

    private String telefoneOrigem;

    private String telefoneDestino;

    public ProdutorVoz() {
    }

    public ProdutorVoz(Long id, String telefoneOrigem, String telefoneDestino, LocalDateTime dataHora) {
        this.telefoneOrigem = telefoneOrigem;
        this.telefoneDestino = telefoneDestino;
        this.setDataHora(dataHora);
        this.setId(id);
    }

    public String getTelefoneOrigem() {
        return this.telefoneOrigem;
    }

    public void setTelefoneOrigem(String telefoneOrigem) {
        this.telefoneOrigem = telefoneOrigem;
    }

    public String getTelefoneDestino() {
        return this.telefoneDestino;
    }

    public void setTelefoneDestino(String telefoneDestino) {
        this.telefoneDestino = telefoneDestino;
    }

    @Override
    public String getTipoProdutor() {
        return "Produtor de voz";
    }
}
