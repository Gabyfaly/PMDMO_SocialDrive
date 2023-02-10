package iesmm.pmdm.socialdrivemm;

import java.sql.Date;

public class Marcador {
    private String idUsuario;
    private String latitud;
    private String longitud;
    private String tipo; // radar, multa, control de alcoholemia
    private Date fecha;

    public Marcador(String idUsuario, String latitud, String longitud, String tipo, Date fecha) {
        this.idUsuario = idUsuario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public Marcador() {
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
