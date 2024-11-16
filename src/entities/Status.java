package entities;

import java.util.Date;

public class Status {
    private int id;
    private int idEncomenda;
    private String status;
    private Date dataStatus;

    public Status(int idEncomenda, String status, Date dataStatus) {
        this.idEncomenda = idEncomenda;
        this.status = status;
        this.dataStatus = dataStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(int idEncomenda) {
        this.idEncomenda = idEncomenda;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Date dataStatus) {
        this.dataStatus = dataStatus;
    }
}
