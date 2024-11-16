package repositories;

import entities.Status;
import java.util.ArrayList;
import java.util.List;

public class StatusRepository {
    private List<Status> statusList = new ArrayList<>();
    private int idCounter = 1;

    public void salvar(Status status) {
        status.setId(idCounter++);
        statusList.add(status);
    }

    public List<Status> buscarPorEncomenda(int encomendaId) {
        List<Status> result = new ArrayList<>();
        for (Status status : statusList) {
            if (status.getEncomendaId() == encomendaId) {
                result.add(status);
            }
        }
        return result;
    }
}
