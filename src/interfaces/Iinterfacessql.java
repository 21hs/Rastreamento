import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    // Conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        try {
            // Carrega o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Substitua os valores abaixo pelas suas configurações do banco
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/nome_do_banco", "usuario", "senha");
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver não encontrado.", e);
        }
    }

    // Classe Encomenda
    public static class Encomenda {
        private int id;
        private String codigoRastreio;
        private Date dataEnvio;
        private int idUsuario;

        public Encomenda(String codigoRastreio, Date dataEnvio, int idUsuario) {
            this.codigoRastreio = codigoRastreio;
            this.dataEnvio = dataEnvio;
            this.idUsuario = idUsuario;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getCodigoRastreio() { return codigoRastreio; }
        public void setCodigoRastreio(String codigoRastreio) { this.codigoRastreio = codigoRastreio; }
        public Date getDataEnvio() { return dataEnvio; }
        public void setDataEnvio(Date dataEnvio) { this.dataEnvio = dataEnvio; }
        public int getIdUsuario() { return idUsuario; }
        public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    }

    // Classe Status
    public static class Status {
        private int id;
        private int idEncomenda;
        private String status;
        private Date dataStatus;

        public Status(int idEncomenda, String status, Date dataStatus) {
            this.idEncomenda = idEncomenda;
            this.status = status;
            this.dataStatus = dataStatus;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public int getIdEncomenda() { return idEncomenda; }
        public void setIdEncomenda(int idEncomenda) { this.idEncomenda = idEncomenda; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public Date getDataStatus() { return dataStatus; }
        public void setDataStatus(Date dataStatus) { this.dataStatus = dataStatus; }
    }

    // Classe Usuario
    public static class Usuario {
        private int id;
        private String nome;
        private String email;
        private String telefone;

        public Usuario(String nome, String email, String telefone) {
            this.nome = nome;
            this.email = email;
            this.telefone = telefone;
        }

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getTelefone() { return telefone; }
        public void setTelefone(String telefone) { this.telefone = telefone; }
    }

    // Interface EncomendaService
    public interface EncomendaService {
        void criarEncomenda(Encomenda encomenda, Connection conn) throws SQLException;
        Encomenda buscarEncomenda(int id, Connection conn) throws SQLException;
        List<Encomenda> listarEncomendas(Connection conn) throws SQLException;
    }

    // Interface StatusService
    public interface StatusService {
        void criarStatus(Status status, Connection conn) throws SQLException;
        List<Status> buscarStatusPorEncomenda(int idEncomenda, Connection conn) throws SQLException;
    }

    // Interface UsuarioService
    public interface UsuarioService {
        void criarUsuario(Usuario usuario, Connection conn) throws SQLException;
        Usuario buscarUsuario(int id, Connection conn) throws SQLException;
        List<Usuario> listarUsuarios(Connection conn) throws SQLException;
    }

    // Implementação do EncomendaService
    public static class EncomendaServiceImpl implements EncomendaService {
        @Override
        public void criarEncomenda(Encomenda encomenda, Connection conn) throws SQLException {
            String sql = "INSERT INTO encomenda (codigo_rastreio, data_envio, id_usuario) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, encomenda.getCodigoRastreio());
                stmt.setDate(2, new java.sql.Date(encomenda.getDataEnvio().getTime()));
                stmt.setInt(3, encomenda.getIdUsuario());
                stmt.executeUpdate();
            }
        }

        @Override
        public Encomenda buscarEncomenda(int id, Connection conn) throws SQLException {
            String sql = "SELECT * FROM encomenda WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Encomenda(
                        rs.getString("codigo_rastreio"),
                        rs.getDate("data_envio"),
                        rs.getInt("id_usuario")
                    );
                }
            }
            return null;
        }

        @Override
        public List<Encomenda> listarEncomendas(Connection conn) throws SQLException {
            List<Encomenda> encomendas = new ArrayList<>();
            String sql = "SELECT * FROM encomenda";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    encomendas.add(new Encomenda(
                        rs.getString("codigo_rastreio"),
                        rs.getDate("data_envio"),
                        rs.getInt("id_usuario")
                    ));
                }
            }
            return encomendas;
        }
    }

    // Implementação do StatusService
    public static class StatusServiceImpl implements StatusService {
        @Override
        public void criarStatus(Status status, Connection conn) throws SQLException {
            String sql = "INSERT INTO status (id_encomenda, status, data_status) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, status.getIdEncomenda());
                stmt.setString(2, status.getStatus());
                stmt.setDate(3, new java.sql.Date(status.getDataStatus().getTime()));
                stmt.executeUpdate();
            }
        }

        @Override
        public List<Status> buscarStatusPorEncomenda(int idEncomenda, Connection conn) throws SQLException {
            List<Status> statusList = new ArrayList<>();
            String sql = "SELECT * FROM status WHERE id_encomenda = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idEncomenda);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    statusList.add(new Status(
                        rs.getInt("id_encomenda"),
                        rs.getString("status"),
                        rs.getDate("data_status")
                    ));
                }
            }
            return statusList;
        }
    }

    // Implementação do UsuarioService
    public static class UsuarioServiceImpl implements UsuarioService {
        @Override
        public void criarUsuario(Usuario usuario, Connection conn) throws SQLException {
            String sql = "INSERT INTO usuario (nome, email, telefone) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getEmail());
                stmt.setString(3, usuario.getTelefone());
                stmt.executeUpdate();
            }
        }

        @Override
        public Usuario buscarUsuario(int id, Connection conn) throws SQLException {
            String sql = "SELECT * FROM usuario WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Usuario(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone")
                    );
                }
            }
            return null;
        }

        @Override
        public List<Usuario> listarUsuarios(Connection conn) throws SQLException {
            List<Usuario> usuarios = new ArrayList<>();
            String sql = "SELECT * FROM usuario";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    usuarios.add(new Usuario(
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("telefone")
                    ));
                }
            }
            return usuarios;
        }
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            // Criação dos serviços
            UsuarioService usuarioService = new UsuarioServiceImpl();
            EncomendaService encomendaService = new EncomendaServiceImpl();
            StatusService statusService = new StatusServiceImpl();

            // Exemplo de criação de usuário
            Usuario usuario = new Usuario("João", "joao@example.com", "123456789");
            usuarioService.criarUsuario(usuario, conn);

            // Exemplo de criação de encomenda
            Encomenda encomenda = new Encomenda("ABC123", new Date(), usuario.getId());
            encomendaService.criarEncomenda(encomenda, conn);

            // Exemplo de criação de status
            Status status = new Status(encomenda.getId(), "Em trânsito", new Date());
            statusService.criarStatus(status, conn);

            // Exemplo de busca e listagem
            Usuario usuarioBuscado = usuarioService.buscarUsuario(usuario.getId(), conn);
            System.out.println("Usuário: " + usuarioBuscado.getNome());

            Encomenda encomendaBuscada = encomendaService.buscarEncomenda(encomenda.getId(), conn);
            System.out.println("Encomenda: " + encomendaBuscada.getCodigoRastreio());

            List<Status> statusBuscado = statusService.buscarStatusPorEncomenda(encomenda.getId(), conn);
            System.out.println("Status: ");
            for (Status st : statusBuscado) {
                System.out.println(st.getStatus());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
