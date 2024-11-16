package services;

import entities.Encomenda;
import database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EncomendaServiceImpl implements EncomendaService {
    
    @Override
    public void salvarEncomenda(Encomenda encomenda) {
        String sql = "INSERT INTO Encomenda (codigo_rastreio, data_envio, id_usuario) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setString(1, encomenda.getCodigoRastreio());
            java.util.Date utilDate = encomenda.getDataEnvio();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            preparedStatement.setDate(2, sqlDate);
            preparedStatement.setInt(3, encomenda.getIdUsuario());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Encomenda> buscarTodasEncomendas() {
        List<Encomenda> encomendas = new ArrayList<>();
        String sql = "SELECT * FROM Encomenda";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
             
            while (resultSet.next()) {
                Encomenda encomenda = new Encomenda(
                    resultSet.getString("codigo_rastreio"),
                    resultSet.getDate("data_envio"),
                    resultSet.getInt("id_usuario")
                );
                encomenda.setId(resultSet.getInt("id"));
                encomendas.add(encomenda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return encomendas;
    }

    @Override
    public void deletarEncomenda(int id) {
        String sql = "DELETE FROM Encomenda WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void criarEncomenda(Encomenda novaEncomenda) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'criarEncomenda'");
    }
}
