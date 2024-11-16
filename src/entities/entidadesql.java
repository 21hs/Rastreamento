import java.sql.*;
import java.util.Date;

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

        public void salvar(Connection conn) throws SQLException {
            String sql = "INSERT INTO encomenda (codigo_rastreio, data_envio, id_usuario) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, this.codigoRastreio);
                stmt.setDate(2, new java.sql.Date(this.dataEnvio.getTime()));
                stmt.setInt(3, this.idUsuario);
                stmt.executeUpdate();
            }
        }

        public static Encomenda buscarPorId(Connection conn, int id) throws SQLException {
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

        public void salvar(Connection conn) throws SQLException {
            String sql = "INSERT INTO status (id_encomenda, status, data_status) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, this.idEncomenda);
                stmt.setString(2, this.status);
                stmt.setDate(3, new java.sql.Date(this.dataStatus.getTime()));
                stmt.executeUpdate();
            }
        }

        public static Status buscarPorId(Connection conn, int id) throws SQLException {
            String sql = "SELECT * FROM status WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return new Status(
                        rs.getInt("id_encomenda"),
                        rs.getString("status"),
                        rs.getDate("data_status")
                    );
                }
            }
            return null;
        }
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

        public void salvar(Connection conn) throws SQLException {
            String sql = "INSERT INTO usuario (nome, email, telefone) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, this.nome);
                stmt.setString(2, this.email);
                stmt.setString(3, this.telefone);
                stmt.executeUpdate();
            }
        }

        public static Usuario buscarPorId(Connection conn, int id) throws SQLException {
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
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            // Exemplo de inserção e busca
            Usuario usuario = new Usuario("João", "joao@example.com", "123456789");
            usuario.salvar(conn);

            Encomenda encomenda = new Encomenda("ABC123", new Date(), usuario.getId());
            encomenda.salvar(conn);

            Status status = new Status(encomenda.getId(), "Em trânsito", new Date());
            status.salvar(conn);

            // Buscando dados
            Usuario usuarioBuscado = Usuario.buscarPorId(conn, usuario.getId());
            System.out.println("Usuário: " + usuarioBuscado.getNome());

            Encomenda encomendaBuscada = Encomenda.buscarPorId(conn, encomenda.getId());
            System.out.println("Encomenda: " + encomendaBuscada.getCodigoRastreio());

            Status statusBuscado = Status.buscarPorId(conn, status.getId());
            System.out.println("Status: " + statusBuscado.getStatus());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
