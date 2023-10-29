package br.com.frederico.desafio.thread.consumidor;

import static java.util.Objects.nonNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import br.com.frederico.desafio.DesafioApplication;
import br.com.frederico.desafio.model.Produtor;
import br.com.frederico.desafio.util.FilaProdutor;

public class MonitorDeConsumidores {
    private static final int CEM_MILISEGUNDOS = 100;
    private static final int UM_SEGUNDO = 1000;
    private static final int DEZ_SEGUNDOS = 10 * 1000;

    private final TarefaConsumir[] tarefasConsumidores;

    private final LocalDateTime tempoParada;

    private Boolean executando = false;

    protected MonitorDeConsumidores() {
        tarefasConsumidores = new TarefaConsumir[Integer.valueOf(DesafioApplication.configuracao.getQtdConsumidor())];
        tempoParada = LocalDateTime.now()
                .plusMinutes(Integer.valueOf(DesafioApplication.configuracao.getTempoRodando()));
    }

    protected void iniciarMonitoracao() {
        if (!executando) {
            executando = true;
            try {
                monitoraFila();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void parar() {
        executando = false;
    }

    private void monitoraFila() {

        while (executando) {
            esperar(UM_SEGUNDO);

            if (!asThreadsEstaoTodasOcupadas()) {

                if (FilaProdutor.isCheia()) {
                    esperar(DEZ_SEGUNDOS);
                    continue;
                }

                processaConsumidores();

                if (LocalDateTime.now().isAfter(tempoParada) && FilaProdutor.isVazia())
                    executando = false;
            }
        }
        System.out.println(FilaProdutor.quantidadeLida());
    }

    private Boolean asThreadsEstaoTodasOcupadas() {
        return pegarIndicesDasThreadsLivres().isEmpty();
    }

    private void esperar(int tempo) {
        try {
            TimeUnit.MILLISECONDS.sleep(tempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private Set<Integer> pegarIndicesDasThreadsLivres() {
        Set<Integer> indicesDasThreadsLivres = new HashSet<>();
        for (int i = 0; i < tarefasConsumidores.length; i++) {
            TarefaConsumir t = tarefasConsumidores[i];

            if (t != null && t.getAcabou())
                tarefasConsumidores[i] = null;

            if (tarefasConsumidores[i] == null)
                indicesDasThreadsLivres.add(i);
        }
        return indicesDasThreadsLivres;
    }

    private void processaConsumidores() {
        Set<Integer> indicesDasThreadsLivres = pegarIndicesDasThreadsLivres();
        for (int indiceParaThreadLivre : indicesDasThreadsLivres) {

            Produtor produtor = FilaProdutor.lerProdutor();

            if (nonNull(produtor)) {
                TarefaConsumir tarefa = new TarefaConsumir(produtor);
                tarefasConsumidores[indiceParaThreadLivre] = tarefa;
                tarefa.start();
            }
            esperar(CEM_MILISEGUNDOS);
        }
    }
}
