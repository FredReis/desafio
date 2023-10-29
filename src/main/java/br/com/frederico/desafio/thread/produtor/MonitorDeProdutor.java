package br.com.frederico.desafio.thread.produtor;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import br.com.frederico.desafio.DesafioApplication;
import br.com.frederico.desafio.model.Produtor;
import br.com.frederico.desafio.model.ProdutorFactory;
import br.com.frederico.desafio.model.TipoProdutor;
import br.com.frederico.desafio.util.FilaProdutor;

public class MonitorDeProdutor {
    private static final int UM_SEGUNDO = 1000;
    private static final int DEZ_SEGUNDOS = 10 * 1000;

    private Boolean executando = false;

    private final LocalDateTime tempoParada;

    protected MonitorDeProdutor() {
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

            if (FilaProdutor.isCheia()) {
                esperar(DEZ_SEGUNDOS);
                continue;
            } else {
                Produtor produtor;
                int ordem = new Random().nextInt(3);
                TipoProdutor tipo = TipoProdutor.geTipoPorOrdem(ordem);

                produtor = ProdutorFactory.instanciarProdutor(tipo, "Nome origem", "Nome destino",
                        LocalDateTime.now());

                FilaProdutor.adicionar(produtor);
            }

            if (LocalDateTime.now().isAfter(tempoParada))
                executando = false;
        }
        System.out.println(FilaProdutor.quantidadeCriada());
    }

    private void esperar(int tempo) {
        try {
            TimeUnit.MILLISECONDS.sleep(tempo);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
