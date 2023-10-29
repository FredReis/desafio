package br.com.frederico.desafio.thread.consumidor;

public class ThreadDoGerenciadorDeConsumidores extends Thread {

    private MonitorDeConsumidores monitorDeConsumidores;

    protected ThreadDoGerenciadorDeConsumidores(MonitorDeConsumidores monitorDeConsumidores,
            String nomeDestaThread) {
        super(nomeDestaThread);
        this.monitorDeConsumidores = monitorDeConsumidores;
    }

    @Override
    public void run() {
        monitorDeConsumidores.iniciarMonitoracao();
    }
}
