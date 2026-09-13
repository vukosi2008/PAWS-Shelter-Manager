/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author vukos
 */
public class AccessDAL {
    protected static final String DATABASE_URL = "jdbc:ucanaccess://src/Persistence/ShelterManager.accdb";
    
    protected  Connection con;
    protected  PreparedStatement preparedStatement;
    protected  ResultSet rs;
    
    public AccessDAL() {
    try {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        con = DriverManager.getConnection(DATABASE_URL);
        System.out.println("Connection successful to: " + DATABASE_URL);
    } catch (ClassNotFoundException ex) {
        System.err.println("AccessDAL: UCanAccess driver not found on classpath!");
        ex.printStackTrace();
    } catch (SQLException ex) {
        System.err.println("AccessDAL: Failed to connect to database.");
        ex.printStackTrace();
   }
}
}
