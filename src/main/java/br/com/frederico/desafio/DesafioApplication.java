package br.com.frederico.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.frederico.desafio.thread.consumidor.GerenciadorDeConsumidores;
import br.com.frederico.desafio.thread.produtor.GerenciadorDeProdutores;
import br.com.frederico.desafio.util.ConfiguracaoDTO;
import br.com.frederico.desafio.util.ConfiguracaoFactory;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class DesafioApplication {

	public static ConfiguracaoDTO configuracao;

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);

	}

	@PostConstruct
	public void postConstruct() {
		configuracao = new ConfiguracaoFactory().lerConfigProperties();
		GerenciadorDeProdutores.iniciar();
		GerenciadorDeConsumidores.iniciar();
	}
}