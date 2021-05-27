package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mauricio
 */
public class sqlUsuarios extends Conexion {

    public boolean registrar(usuarios usr) {

        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO usuarios (usuario, contrasena, nombre, correo, id_tipo) VALUES (?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            ps.setString(2, usr.getContrasena());
            ps.setString(3, usr.getNombre());
            ps.setString(4, usr.getCorreo());
            ps.setInt(5, usr.getId_tipo());

            ps.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(sqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public int existeUsuario(String usuario) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT count(id) FROM usuarios WHERE usuario =?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);

            }
            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(sqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }

    }

    public boolean login(usuarios usr) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT id, usuario, contrasena, nombre, id_tipo  FROM usuarios WHERE usuario =?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {
                if (usr.getContrasena().equals(rs.getString(3))) {

                    usr.setId(rs.getInt(1));
                    usr.setNombre(rs.getString(4));
                    usr.setId_tipo(rs.getInt(5));
                    return true;
                } else {
                    return false;
                }
            }
            return false;

        } catch (SQLException ex) {
            Logger.getLogger(sqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean esEmail(String correo) {
        //Patron para validar Email
        Pattern pat = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]\\+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pat.matcher(correo);

        return mather.find();
    }
}
