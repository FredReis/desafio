package br.com.frederico.desafio.model;

import java.time.LocalDateTime;

public class ProdutorChat extends Produtor {

    private String nomeUsuarioOrigem;

    private String nomeUsuarioDestino;

    public ProdutorChat() {
    }

    public ProdutorChat(Long id, String nomeUsuarioOrigem, String nomeUsuarioDestino, LocalDateTime dataHora) {
        this.nomeUsuarioOrigem = nomeUsuarioOrigem;
        this.nomeUsuarioDestino = nomeUsuarioDestino;
        this.setDataHora(dataHora);
        this.setId(id);
    }

    public String getNomeUsuarioOrigem() {
        return this.nomeUsuarioOrigem;
    }

    public void setNomeUsuarioOrigem(String nomeUsuarioOrigem) {
        this.nomeUsuarioOrigem = nomeUsuarioOrigem;
    }

    public String getNomeUsuarioDestino() {
        return this.nomeUsuarioDestino;
    }

    public void setNomeUsuarioDestino(String nomeUsuarioDestino) {
        this.nomeUsuarioDestino = nomeUsuarioDestino;
    }

    @Override
    public String getTipoProdutor() {
        return "Produtor de chat";
    }

}
