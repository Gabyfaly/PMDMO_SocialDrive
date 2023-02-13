package iesmm.pmdm.socialdrivemm.dao;

import java.util.Date;
import java.util.List;

import iesmm.pmdm.socialdrivemm.model.Marcador;
import iesmm.pmdm.socialdrivemm.model.Usuario;

public interface DAO {
    public List<Marcador> getAllMarcadores();
    public void insertarMarcador(String idUsuario_marcador, String latitud, String longitud, String tipo, Date fecha);
    public void updateMarcador(Marcador marcador);
    public boolean delete(String idMarcador);
    public Usuario comprobarUsuario(Usuario usuario);
}
