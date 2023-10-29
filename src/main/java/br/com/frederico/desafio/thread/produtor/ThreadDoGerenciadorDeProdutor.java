package br.com.frederico.desafio.thread.produtor;

public class ThreadDoGerenciadorDeProdutor extends Thread {

    private MonitorDeProdutor monitorDeProdutor;

    protected ThreadDoGerenciadorDeProdutor(MonitorDeProdutor monitorDeProdutor,
            String nomeDestaThread) {
        super(nomeDestaThread);
        this.monitorDeProdutor = monitorDeProdutor;
    }

    @Override
    public void run() {
        monitorDeProdutor.iniciarMonitoracao();
    }
}
