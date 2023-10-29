package br.com.frederico.desafio.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import br.com.frederico.desafio.model.Produtor;
import br.com.frederico.desafio.model.ProdutorChat;
import br.com.frederico.desafio.model.ProdutorEmail;
import br.com.frederico.desafio.model.ProdutorFactory;
import br.com.frederico.desafio.model.ProdutorVoz;
import br.com.frederico.desafio.util.DatabaseConnection;

public class ProdutorDAO {

    public List<Produtor> listarProdutores() {
        List<Produtor> produtores = new ArrayList<>();
        String sql = "SELECT id, usuario_origem, usuario_destino, email_origem, email_destino, telefone_origem, telefone_destino, data_hora FROM produtor";

        try (PreparedStatement pstm = DatabaseConnection.getConnection()
                .prepareStatement(sql);
                ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Produtor produtor = ProdutorFactory.instanciarProdutor(rs.getLong(0), rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                        LocalDateTime.parse(rs.getString(7)));

                produtores.add(produtor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtores;
    }

    public Produtor getProdutorPorId(Long id) {
        Produtor produtor = null;
        String sql = "SELECT id, usuario_origem, usuario_destino, email_origem, email_destino, telefone_origem, telefone_destino, data_hora FROM produtor WHERE id = ?";

        try (PreparedStatement pstm = DatabaseConnection.getConnection()
                .prepareStatement(sql);) {
            pstm.setLong(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    produtor = ProdutorFactory.instanciarProdutor(rs.getLong(0), rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                            LocalDateTime.parse(rs.getString(7)));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtor;
    }

    public Produtor gravaProdutor(Produtor produtor) {
        String sql = "INSERT INTO produtor(usuario_origem, usuario_destino, email_origem, email_destino, telefone_origem, telefone_destino, data_hora) VALUES (?, ?, ?, ?, ?, ?, ?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        try (PreparedStatement pstm = DatabaseConnection.getConnection()
                .prepareStatement(sql);) {
            if (produtor instanceof ProdutorChat) {
                pstm.setString(1, ((ProdutorChat) produtor).getNomeUsuarioOrigem());
                pstm.setString(2, ((ProdutorChat) produtor).getNomeUsuarioDestino());
            } else {
                pstm.setString(1, null);
                pstm.setString(2, null);
            }

            if (produtor instanceof ProdutorEmail) {
                pstm.setString(3, ((ProdutorEmail) produtor).getEmailOrigem());
                pstm.setString(4, ((ProdutorEmail) produtor).getEmailDestino());
            } else {
                pstm.setString(3, null);
                pstm.setString(4, null);
            }

            if (produtor instanceof ProdutorVoz) {
                pstm.setString(5, ((ProdutorVoz) produtor).getTelefoneOrigem());
                pstm.setString(6, ((ProdutorVoz) produtor).getTelefoneDestino());
            } else {
                pstm.setString(5, null);
                pstm.setString(6, null);
            }

            pstm.setString(7, formatter.format(produtor.getDataHora()));
            int linhasAfetadas = pstm.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new SQLException("Criação do registro falhou.");
            }

            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    produtor.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Não foi possivel recuperar o ID do registro.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtor;
    }

    public Produtor atualizaProdutorPorId(Long id, Produtor produtor) {
        String sql = "UPDATE produtor set usuario_origem = ?, usuario_destino = ?, email_origem = ?, email_destino = ?, telefone_origem = ?, telefone_destino = ?, data_hora = ? WHERE id = ?";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        try (PreparedStatement pstm = DatabaseConnection.getConnection()
                .prepareStatement(sql);) {
            if (produtor instanceof ProdutorChat) {
                pstm.setString(1, ((ProdutorChat) produtor).getNomeUsuarioOrigem());
                pstm.setString(2, ((ProdutorChat) produtor).getNomeUsuarioDestino());
            } else {
                pstm.setString(1, null);
                pstm.setString(2, null);
            }

            if (produtor instanceof ProdutorEmail) {
                pstm.setString(3, ((ProdutorEmail) produtor).getEmailOrigem());
                pstm.setString(4, ((ProdutorEmail) produtor).getEmailDestino());
            } else {
                pstm.setString(3, null);
                pstm.setString(4, null);
            }

            if (produtor instanceof ProdutorVoz) {
                pstm.setString(5, ((ProdutorVoz) produtor).getTelefoneOrigem());
                pstm.setString(6, ((ProdutorVoz) produtor).getTelefoneDestino());
            } else {
                pstm.setString(5, null);
                pstm.setString(6, null);
            }

            pstm.setString(7, formatter.format(produtor.getDataHora()));
            pstm.setLong(8, produtor.getId());

            int linhasAfetadas = pstm.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new SQLException("Atualização do registro falhou.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return produtor;
    }

    public void removerProdutorPorId(Long id) {
        String sql = "DELETE FROM produtor WHERE id = ?";

        try (PreparedStatement pstm = DatabaseConnection.getConnection()
                .prepareStatement(sql);) {

            pstm.setLong(1, id);

            int linhasAfetadas = pstm.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new SQLException("Exclusão do registro falhou.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
