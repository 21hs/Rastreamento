package interfaces;

import entities.Status;
import java.util.List;

public interface StatusService {
    void criarStatus(Status status);
    List<Status> buscarStatusPorEncomenda(int idEncomenda);
}
