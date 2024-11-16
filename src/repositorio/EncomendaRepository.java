package repositories;

import entities.Encomenda;
import java.util.ArrayList;
import java.util.List;

public class EncomendaRepository {
    private List<Encomenda> encomendas = new ArrayList<>();
    private int idCounter = 1;

    public void salvar(Encomenda encomenda) {
        encomenda.setId(idCounter++);
        encomendas.add(encomenda);
    }

    public Encomenda buscarPorId(int id) {
        return encomendas.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Encomenda> listar() {
        return encomendas;
    }
}
