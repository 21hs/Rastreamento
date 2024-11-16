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

    @Override
    public Encomenda buscarEncomendaPorId(int id) {
        String sql = "SELECT * FROM Encomenda WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Encomenda(
                    resultSet.getString("codigo_rastreio"),
                    resultSet.getDate("data_envio"),
                    resultSet.getInt("id_usuario")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

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

    @Override
    public Status buscarStatusPorId(int id) {
        String sql = "SELECT * FROM Status WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Status(
                    resultSet.getInt("id_encomenda"),
                    resultSet.getString("status"),
                    resultSet.getDate("data_status")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package services;

import entities.Usuario;
import database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioServiceImpl implements UsuarioService {

    @Override
    public void salvarUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nome, email) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Usuario> buscarTodosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
             
            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                    resultSet.getString("nome"),
                    resultSet.getString("email")
                );
                usuario.setId(resultSet.getInt("id"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public void deletarUsuario(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario buscarUsuarioPorId(int id) {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Usuario(
                    resultSet.getString("nome"),
                    resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
