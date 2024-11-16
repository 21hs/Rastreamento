package interfaces;

import entities.Usuario;
import java.util.List;

public interface UsuarioService {
    void criarUsuario(Usuario usuario);
    Usuario buscarUsuario(int id);
    List<Usuario> listarUsuarios();
}
