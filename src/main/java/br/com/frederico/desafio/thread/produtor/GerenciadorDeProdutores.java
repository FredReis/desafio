package br.com.frederico.desafio.thread.produtor;

public class GerenciadorDeProdutores {

    private static MonitorDeProdutor singletonProducao;
    private static ThreadDoGerenciadorDeProdutor thread;

    private GerenciadorDeProdutores() {
    }

    public static synchronized MonitorDeProdutor getMonitorProducao() {
        if (singletonProducao == null)
            singletonProducao = new MonitorDeProdutor();
        return singletonProducao;
    }

    public static synchronized void iniciar() {
        if (thread == null) {
            thread = new ThreadDoGerenciadorDeProdutor(getMonitor(),
                    getNomeDaThread());
            thread.start();
        }
    }

    public static synchronized void parar() {
        getMonitor().parar();
    }

    private static MonitorDeProdutor getMonitor() {
        return GerenciadorDeProdutores.getMonitorProducao();
    }

    private static String getNomeDaThread() {
        return ThreadDoGerenciadorDeProdutor.class.getName();
    }
}
