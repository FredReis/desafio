package br.com.frederico.desafio.model;

public enum TipoProdutor {
    CHAT,
    EMAIL,
    VOZ;

    public static TipoProdutor geTipoPorOrdem(int ordem) {
        if (ordem == 0)
            return CHAT;
        else if (ordem == 1)
            return EMAIL;
        else if (ordem == 2)
            return VOZ;
        else
            throw new RuntimeException("Tipo de produtor não encontrado.");
    }
}
