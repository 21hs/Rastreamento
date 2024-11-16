package entities;

import java.util.Date;

public class Encomenda {
    private int id;
    private String codigoRastreio;
    private Date dataEnvio;
    private int idUsuario;

    public Encomenda(String codigoRastreio, Date dataEnvio, int idUsuario) {
        this.codigoRastreio = codigoRastreio;
        this.dataEnvio = dataEnvio;
        this.idUsuario = idUsuario;
    }

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
}
