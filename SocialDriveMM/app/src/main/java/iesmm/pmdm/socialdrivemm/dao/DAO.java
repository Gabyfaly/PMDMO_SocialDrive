package iesmm.pmdm.socialdrivemm.dao;

import java.util.Date;
import java.util.List;

import iesmm.pmdm.socialdrivemm.model.Marcador;
import iesmm.pmdm.socialdrivemm.model.Usuario;

public interface DAO {
    public List<Marcador> getAllMarcadores();
    public void insertarMarcador(Marcador marcador);
    public void updateMarcador(Marcador marcador);
    public boolean delete(String idMarcador);

}
