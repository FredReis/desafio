Passos rodar o projeto
1-postgresql instalado
2-criar um banco de dados com qualquer nome
3-criar a tabela com o script abaixo
    create table produtor
    (
        id bigserial not null,
        telefone_origem varchar(50),
        telefone_destino varchar(50),
        email_origem varchar(50),
        email_destino varchar(50),
        usuario_origem varchar(50),
        usuario_destino varchar(50),
        data_hora timestamp not null,
        versao integer not null,
        constraint produtor_pkey primary key(id)
    );
4-configuar as variaveis dentro do application.properties
    qtdProdutor - quantidade maxima de produtores
    qtdConsumidor - quantidade maxima de consumidores
    bdUrl - url de conexao com o banco ex: jdbc:postgresql://localhost:5432/desafio_db
    bdUsuario - usuario do postgres
    bdSenha - senha do postgres
    tempoRodando - tempo que a aplicação ficara ativa em minutos
5-fazer o build da aplicação ou rodar dentro de um ambiente de desenvolvimento
6-caso tenha feito o build, precisa executar a aplicação via terminal com o comnado java -jar "caminho + nome da aplicação"