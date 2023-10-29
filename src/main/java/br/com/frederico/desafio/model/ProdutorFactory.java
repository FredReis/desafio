package br.com.frederico.desafio.model;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.time.LocalDateTime;

public class ProdutorFactory {

    public static Produtor instanciarProdutor(TipoProdutor tipo, String origem, String destino,
            LocalDateTime dataHora) {
        switch (tipo) {
            case CHAT:
                return new ProdutorChat(null, origem, destino, dataHora);
            case VOZ:
                return new ProdutorVoz(null, origem, destino, dataHora);
            case EMAIL:
                return new ProdutorEmail(null, origem, destino, dataHora);
            default:
                throw new RuntimeException("Tipo de produtor inválido");
        }
    }

    public static Produtor instanciarProdutor(Long id, String nomeUsuarioOrigem, String nomeUsuarioDestino,
            String emailOrigem, String emailDestino, String telefoneOrigem, String telefoneDestino,
            LocalDateTime dataHora) {

        if (nonNull(nomeUsuarioOrigem) && nonNull(nomeUsuarioDestino) && isNull(emailOrigem) && isNull(emailOrigem)
                && isNull(telefoneOrigem) && isNull(telefoneDestino))
            return new ProdutorChat(id, nomeUsuarioOrigem, nomeUsuarioDestino, dataHora);

        else if (nonNull(telefoneOrigem) && nonNull(telefoneDestino) && isNull(nomeUsuarioOrigem)
                && isNull(nomeUsuarioDestino) && isNull(emailOrigem) && isNull(emailOrigem))
            return new ProdutorVoz(id, telefoneOrigem, telefoneDestino, dataHora);

        else if (nonNull(emailOrigem) && nonNull(emailOrigem) && isNull(nomeUsuarioOrigem) && isNull(nomeUsuarioDestino)
                && isNull(telefoneOrigem) && isNull(telefoneDestino))
            return new ProdutorEmail(id, emailOrigem, emailDestino, dataHora);

        else
            throw new RuntimeException("Tipo de produtor inválido");

    }
}
