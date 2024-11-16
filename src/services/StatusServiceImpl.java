package services;

import entities.Status;
import database.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusServiceImpl implements StatusService {
    
    @Override
    public void salvarStatus(Status status) {
        String sql = "INSERT INTO Status (id_encomenda, status, data_status) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, status.getIdEncomenda());
            preparedStatement.setString(2, status.getStatus());
            java.util.Date utilDate = status.getDataStatus();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Status> buscarTodosStatus() {
        List<Status> statuses = new ArrayList<>();
        String sql = "SELECT * FROM Status";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
             
            while (resultSet.next()) {
                Status status = new Status(
                    resultSet.getInt("id_encomenda"),
                    resultSet.getString("status"),
                    resultSet.getDate("data_status")
                );
                status.setId(resultSet.getInt("id"));
                statuses.add(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }

    @Override
    public void deletarStatus(int id) {
        String sql = "DELETE FROM Status WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Status> buscarStatusPorEncomenda(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarStatusPorEncomenda'");
    }
}
