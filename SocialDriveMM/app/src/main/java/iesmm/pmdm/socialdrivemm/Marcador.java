package iesmm.pmdm.socialdrivemm;

import java.util.Date;

public class Marcador {
    String idMarcador;
    private String idUsuario_marcador;
    private String latitud;
    private String longitud;
    private String tipo; // radar, multa, control de alcoholemia
    private Date fecha;

    public Marcador(String idMarcador, String idUsuario_marcador, String latitud, String longitud, String tipo, Date fecha) {
        this.idMarcador = idMarcador;
        this.idUsuario_marcador = idUsuario_marcador;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public Marcador() {
    }

    public Marcador(String latitud, String longitud, String tipo, Date fecha) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipo = tipo;
        this.fecha = fecha;
    }

    public Marcador(String latitud, String longitud, String tipo) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipo = tipo;
    }

    public String getIdMarcador() {
        return idMarcador;
    }

    public void setIdMarcador(String idMarcador) {
        this.idMarcador = idMarcador;
    }

    public String getIdUsuario_marcador() {
        return idUsuario_marcador;
    }

    public void setIdUsuario_marcador(String idUsuario_marcador) {
        this.idUsuario_marcador = idUsuario_marcador;
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
