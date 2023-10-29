package br.com.frederico.desafio.thread.consumidor;

import br.com.frederico.desafio.model.Produtor;

public class TarefaConsumir extends Thread {

    private Produtor produtor;
    private Boolean acabou = false;

    public TarefaConsumir(Produtor produtor) {
        super(TarefaConsumir.class.getName());
        this.produtor = produtor;
    }

    public Boolean getAcabou() {
        return acabou;
    }

    @Override
    public void run() {
        System.out.println(produtor.getTipoProdutor());
        this.acabou = true;
    }
}
