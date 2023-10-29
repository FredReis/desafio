package br.com.frederico.desafio.thread.consumidor;

public class GerenciadorDeConsumidores {

    private static MonitorDeConsumidores singletonProducao;
    private static ThreadDoGerenciadorDeConsumidores thread;

    private GerenciadorDeConsumidores() {
    }

    public static synchronized MonitorDeConsumidores getMonitorProducao() {
        if (singletonProducao == null)
            singletonProducao = new MonitorDeConsumidores();
        return singletonProducao;
    }

    public static synchronized void iniciar() {
        if (thread == null) {
            thread = new ThreadDoGerenciadorDeConsumidores(getMonitor(),
                    getNomeDaThread());
            thread.start();
        }
    }

    public static synchronized void parar() {
        getMonitor().parar();
    }

    private static MonitorDeConsumidores getMonitor() {
        return GerenciadorDeConsumidores.getMonitorProducao();
    }

    private static String getNomeDaThread() {
        return ThreadDoGerenciadorDeConsumidores.class.getName();
    }
}
