package br.com.frederico.desafio.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfiguracaoFactory {

    private static final String CAMPO_BD_URL = "bdUrl";
    private static final String CAMPO_BD_LOGIN = "bdUsuario";
    private static final String CAMPO_BD_SENHA = "bdSenha";
    private static final String CAMPO_QTD_PRODUTOR = "qtdProdutor";
    private static final String CAMPO_QTD_CONSUMIDOR = "qtdConsumidor";
    private static final String CAMPO_TEMPO_RODANDO = "tempoRodando";

    private static ConfiguracaoDTO configuracoes;

    public ConfiguracaoDTO lerConfigProperties() {
        if (configuracoes == null) {
            Properties props = carregarProperties();
            configuracoes = carregarParametros(props);
        }
        return configuracoes;
    }

    private ConfiguracaoDTO carregarParametros(Properties props) {
        ConfiguracaoDTO conf = new ConfiguracaoDTO();

        conf.setBdUrl(carregarParametroObrigatorio(props, CAMPO_BD_URL));
        conf.setBdUsuario(carregarParametroObrigatorio(props, CAMPO_BD_LOGIN));
        conf.setBdSenha(carregarParametroObrigatorio(props, CAMPO_BD_SENHA));
        conf.setQtdProdutor(carregarParametroObrigatorio(props, CAMPO_QTD_PRODUTOR));
        conf.setQtdConsumidor(carregarParametroObrigatorio(props, CAMPO_QTD_CONSUMIDOR));
        conf.setTempoRodando(carregarParametroObrigatorio(props, CAMPO_TEMPO_RODANDO));

        return conf;
    }

    private String carregarParametroObrigatorio(Properties props, String nomeDaPropriedade) {
        try {
            return props.getProperty(nomeDaPropriedade).replace("\\", "/");
        } catch (Exception e) {
            throw new RuntimeException("Falta configurar a propriedade: " + nomeDaPropriedade
                    + "\nPor Favor, preencha o application.properties");
        }
    }

    private Properties carregarProperties() {
        Properties props = new Properties();
        try {
            ClassLoader classLoader = getDefaultClassLoader();
            if (classLoader == null)
                throw new RuntimeException(
                        "Erro: Não foi possível carregar o classLoader para ler application.properties.");

            InputStream in = classLoader.getResourceAsStream("application.properties");
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Erro: Não foi possível ler application.properties \nVerifique se ele existe e está acessível.");
        }

        return props;
    }

    private static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        try {
            classLoader = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back...
        }
        if (classLoader == null) {
            // No thread context class loader -> use class loader of this class.
            classLoader = ConfiguracaoFactory.class.getClassLoader();
            if (classLoader == null) {
                // getClassLoader() returning null indicates the bootstrap ClassLoader
                try {
                    classLoader = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with
                    // null...
                }
            }
        }
        return classLoader;
    }
}
