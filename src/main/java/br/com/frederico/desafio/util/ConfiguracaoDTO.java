package br.com.frederico.desafio.util;

import org.springframework.stereotype.Component;

@Component
public class ConfiguracaoDTO {

    private String bdUrl;

    private String bdUsuario;

    private String bdSenha;

    private String qtdProdutor;

    private String qtdConsumidor;

    private String tempoRodando;

    public String getBdUrl() {
        return bdUrl;
    }

    public void setBdUrl(String bdUrl) {
        this.bdUrl = bdUrl;
    }

    public String getBdUsuario() {
        return bdUsuario;
    }

    public void setBdUsuario(String bdUsuario) {
        this.bdUsuario = bdUsuario;
    }

    public String getBdSenha() {
        return bdSenha;
    }

    public void setBdSenha(String bdSenha) {
        this.bdSenha = bdSenha;
    }

    public String getQtdProdutor() {
        return qtdProdutor;
    }

    public void setQtdProdutor(String qtdProdutor) {
        this.qtdProdutor = qtdProdutor;
    }

    public String getQtdConsumidor() {
        return qtdConsumidor;
    }

    public void setQtdConsumidor(String qtdConsumidor) {
        this.qtdConsumidor = qtdConsumidor;
    }

    public String getTempoRodando() {
        return tempoRodando;
    }

    public void setTempoRodando(String tempoRodando) {
        this.tempoRodando = tempoRodando;
    }
}
