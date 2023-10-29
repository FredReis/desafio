package br.com.frederico.desafio.model;

import java.time.LocalDateTime;

public class ProdutorEmail extends Produtor {

    private String emailOrigem;

    private String emailDestino;

    public ProdutorEmail() {
    }

    public ProdutorEmail(Long id, String emailOrigem, String emailDestino, LocalDateTime dataHora) {
        this.emailOrigem = emailOrigem;
        this.emailDestino = emailDestino;
        this.setDataHora(dataHora);
        this.setId(id);
    }

    public String getEmailOrigem() {
        return this.emailOrigem;
    }

    public void setEmailOrigem(String emailOrigem) {
        this.emailOrigem = emailOrigem;
    }

    public String getEmailDestino() {
        return this.emailDestino;
    }

    public void setEmailDestino(String emailDestino) {
        this.emailDestino = emailDestino;
    }

    @Override
    public String getTipoProdutor() {
        return "Produtor de e-mail";
    }

}
