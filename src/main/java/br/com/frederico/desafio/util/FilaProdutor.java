package br.com.frederico.desafio.util;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.frederico.desafio.DesafioApplication;
import br.com.frederico.desafio.model.Produtor;

public class FilaProdutor {

    private static List<Produtor> produtores = new ArrayList<>();;

    private static Integer criados = 0;

    private static Integer lidos = 0;

    public static boolean isCheia() {
        return FilaProdutor.produtores.size() == Integer.valueOf(DesafioApplication.configuracao.getQtdProdutor());
    }

    public static boolean isVazia() {
        return FilaProdutor.produtores.size() == 0;
    }

    public static void adicionar(Produtor produtor) {
        if (!isCheia()) {
            FilaProdutor.produtores.add(produtor);
            FilaProdutor.criados += 1;
        }
    }

    public static Produtor lerProdutor() {
        Produtor produtor = null;
        if (nonNull(FilaProdutor.produtores) && FilaProdutor.produtores.size() > 0) {
            produtor = FilaProdutor.produtores.get(0);
            FilaProdutor.produtores.remove(0);
            FilaProdutor.lidos += 1;
        }
        return produtor;
    }

    public static String quantidadeCriada() {
        return "Quantidade de produtores criados foi de " + FilaProdutor.criados;
    }

    public static String quantidadeLida() {
        return "Quantidade de produtores lidos foi de " + FilaProdutor.lidos;
    }
}
