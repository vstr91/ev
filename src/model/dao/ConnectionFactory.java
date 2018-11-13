/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class ConnectionFactory {
    
//    public String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    public String driver = "org.h2.Driver";
    
    public Connection getConnection() {
        try {
            
//            return DriverManager.getConnection(
//                    "jdbc:h2://localhost:1527/ev", "root", "root");
            Class.forName(driver);
//            return DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            return DriverManager.getConnection("jdbc:h2:./eventos;IFEXISTS=TRUE", "ev", "ev");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
}