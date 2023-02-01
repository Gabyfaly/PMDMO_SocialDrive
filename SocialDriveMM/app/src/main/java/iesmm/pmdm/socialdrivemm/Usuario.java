package iesmm.pmdm.socialdrivemm;

public class Usuario {

    public String usuario;
    public String contrasenia;

    public Usuario(String usuario,String contrasenia) {
        this.usuario=usuario;
        this.contrasenia=contrasenia;

    }

    public String getusuario() {
        return usuario;
    }

    public void setusuario(String usuario) {
        this.usuario = usuario;
    }

    public String getcontrasenia() {
        return contrasenia;
    }

    public void setcontrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

}
