package interfaces;

import entities.Encomenda;
import java.util.List;

public interface EncomendaService {
    void criarEncomenda(Encomenda encomenda);
    Encomenda buscarEncomenda(int id);
    List<Encomenda> listarEncomendas();
}
