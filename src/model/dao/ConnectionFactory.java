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
    
    public String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    
    public Connection getConnection() {
        try {
            
            return DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/ev", "root", "root");
//            Class.forName(driver);
//            return DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
}