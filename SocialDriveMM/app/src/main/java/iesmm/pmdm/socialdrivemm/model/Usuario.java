package iesmm.pmdm.socialdrivemm.model;

public class Usuario {
    private String idUsuario;
    private String username;
    private String contrasenia;

    public Usuario(String username, String contrasenia) {
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

    public boolean checkUser(String usuarioEditText, String contraseniaEditText) {

        if (usuarioEditText.equals(username) && contraseniaEditText.equals(contrasenia)) {
            return true;
        } else {
            return false;
        }
    }
}
