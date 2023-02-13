package iesmm.pmdm.socialdrivemm;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.navigation.NavigationView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import iesmm.pmdm.socialdrivemm.Conexion.ConnectionHelper;
import iesmm.pmdm.socialdrivemm.dao.DAO;
import iesmm.pmdm.socialdrivemm.dao.DAOImpl;
import iesmm.pmdm.socialdrivemm.model.Usuario;

public class LoginActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    List<Usuario> usuarios = new ArrayList();
    Usuario u;
    boolean isValid = false;
    int contadorLinea=0;
    int posicion = 0;
    private DAOImpl myDb;
    private DAO dao;
    private EditText usuarioText,contraseniaText;
    private Button btnInicio;
    private static final String TAG = "GA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Declaración de variables.
        dao = new DAOImpl();
        usuarioText= findViewById(R.id.input_usuario);
        contraseniaText= findViewById(R.id.input_contrasena);
        btnInicio = findViewById(R.id.btn_login);



        //Acción del botón.
        btnInicio.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {


                //myDb.comprobarUsuario(u);
//Comprobación de lo introducido en los campos.
                String username = usuarioText.getText().toString();
                String password = contraseniaText.getText().toString();
                //Usuario usuario = new Usuario("user", "password");
                if(username.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this, "Campos vacios", Toast.LENGTH_SHORT).show();
                }else
                //Comprobación de los campos introducidos en los EditText con los usuarios de la base de datos.
                  new LoginTask().execute(username, password);



                /*if (checkValue(username,password)==true){
                    Toast.makeText(LoginActivity.this, "Usuario correcto", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(LoginActivity.this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
*/
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    //Clase privada para comprobar el usuario y contraseña de la base de datos mediante tareas asíncronas.

    private class LoginTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            String username = strings[0];
            String password = strings[1];
            return checkValue(username, password);

        }


        public boolean checkValue(String usu, String cont) {
            String consulta = "SELECT * FROM usuario";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = ConnectionHelper.getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String id = resultSet.getString("idUsuario");
                    String usuario = resultSet.getString("username");
                    String password = resultSet.getString("contrasenia");
                    if (usuario.equals(usu) && password.equals(cont)) {
                        //Toast.makeText(LoginActivity.this, "Usuario correcto", Toast.LENGTH_SHORT).show();

                        isValid = true;
                        Intent i = new Intent(LoginActivity.this, InterfazUsuario.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("idUsuario",id);
                        i.putExtras(bundle);
                        startActivity(i);
                        //Para de seguir leyendo lineas ya que te devuelve false cnd coinciden

                    }
                        //Toast.makeText(LoginActivity.this, "Incorrecto", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return isValid;
        }


        @Override
        protected void onPostExecute(Boolean res) {
            if(res){
                Toast.makeText(LoginActivity.this,"Iniciando la interfaz de usuario", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(LoginActivity.this,"Error de acceso: Usuario o Contraseña incorrectos", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(res);
        }
    }



    }
