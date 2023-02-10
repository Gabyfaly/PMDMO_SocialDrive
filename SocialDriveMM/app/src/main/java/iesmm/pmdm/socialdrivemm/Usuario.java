package iesmm.pmdm.socialdrivemm;

public class Usuario {
    private String idUsuario;
    private String username;
    private String contrasenia;

    public Usuario(String idUsuario, String username, String contrasenia) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.contrasenia = contrasenia;
    }

    public Usuario() {
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
