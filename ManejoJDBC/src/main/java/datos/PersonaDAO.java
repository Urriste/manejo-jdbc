package datos;

import domain.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    //Esta clase aplica el patrón de diseño DAO, y se usa para interactuar con la base de datos

    private static final String SQL_SELECT = "SELECT id_persona,nombre,apellido,email,telefono from test.persona";
    private static final String SQL_INSERT = "INSERT INTO test.persona(nombre,apellido,email,telefono) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE test.persona SET nombre = ?,apellido = ? ,email = ?,telefono = ? WHERE id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM test.persona WHERE id_persona = ?";


    public List<Persona> seleccionar(){

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while(rs.next()){

                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");

                persona = new Persona(idPersona,nombre,apellido,telefono,email);

                personas.add(persona);

            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally{
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return personas;

    }

    public int insertar(Persona persona){

        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1,persona.getNombre());
            stmt.setString(2,persona.getApellido());
            stmt.setString(3,persona.getEmail());
            stmt.setString(4,persona.getTelefono());

            registros = stmt.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {

            try {
                Conexion.close(stmt);
                Conexion.close(conn);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return registros;


    }

    public int actualizar(Persona persona){

        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1,persona.getNombre());
            stmt.setString(2,persona.getApellido());
            stmt.setString(3,persona.getEmail());
            stmt.setString(4,persona.getTelefono());
            stmt.setInt(5,persona.getIdPersona());

            registros = stmt.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {

            try {
                Conexion.close(stmt);
                Conexion.close(conn);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return registros;

    }

    public int eliminar(Persona persona){

        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);

            stmt.setInt(1,persona.getIdPersona());

            registros = stmt.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }finally {

            try {
                Conexion.close(stmt);
                Conexion.close(conn);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return registros;

    }


}
