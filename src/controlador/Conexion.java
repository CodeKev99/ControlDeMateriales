package controlador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mauricio
 */
public class Conexion {

    public static Connection getConexion() {
        //el LAPTOP-OA9CQMC3\\SQLEXPRESSg es el de mi maquina uestes agrengen el de las suyas
        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String conexionUrl = "jdbc:sqlserver://LAPTOP-OA9CQMC3\\SQLEXPRESSg:1433;"
                + "databaseName=proyectos;"
                + "user=sa;"
                + "password=1234;"
                + "loginTimeout=30;";
        try {
            Connection con = DriverManager.getConnection(conexionUrl);
            return con;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }

    }
}
