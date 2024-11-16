package entities;

import java.util.Date;

public class mainsql {
    private int id;
    private String codigoRastreio;
    private Date dataEnvio;
    private int idUsuario;

    // Construtor corrigido para a classe mainsql
    public mainsql(String codigoRastreio, Date dataEnvio, int idUsuario) {
        this.codigoRastreio = codigoRastreio;
        this.dataEnvio = dataEnvio;
        this.idUsuario = idUsuario;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "mainsql{" +
                "id=" + id +
                ", codigoRastreio='" + codigoRastreio + '\'' +
                ", dataEnvio=" + dataEnvio +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
