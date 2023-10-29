package br.com.frederico.desafio.util;

import static java.util.Objects.isNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.frederico.desafio.DesafioApplication;

public class DatabaseConnection {

    private static Connection con = null;

    private static void criaConexao() {

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(DesafioApplication.configuracao.getBdUrl(),
                    DesafioApplication.configuracao.getBdUsuario(),
                    DesafioApplication.configuracao.getBdSenha());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (isNull(con) || con.isClosed())
            DatabaseConnection.criaConexao();
        return con;
    }
}
