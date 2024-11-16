package repositories;

import entities.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private List<Usuario> usuarios = new ArrayList<>();
    private int idCounter = 1;

    public void salvar(Usuario usuario) {
        usuario.setId(idCounter++);
        usuarios.add(usuario);
    }

    public Usuario buscarPorId(int id) {
        return usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> listar() {
        return usuarios;
    }
}
