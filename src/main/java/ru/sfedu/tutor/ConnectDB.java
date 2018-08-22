/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.tutor;

import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static ru.sfedu.tutor.utils.ConfigurationUtil.getConfigurationEntry;

/**
 *
 * @author BORYAN
 */
public class ConnectDB {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TutorClient.class);
    public ConnectDB(){
    
    }
    public Connection Connect(){
//        try {
//            try {
//                Class.forName(getConfigurationEntry(Constance.JDBC_DRIVER));
//            } catch (IOException ex) {
//                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (ClassNotFoundException ex) {
//            log.info("Driver not found.");
//        }
//        log.debug("Driver successfully connected.");
        
        Connection connection = null;
        try {
            try {
                connection =  (Connection) DriverManager.getConnection(getConfigurationEntry(Constance.DATABASE_URL), getConfigurationEntry(Constance.USER), getConfigurationEntry(Constance.PASSWORD));
            } catch (IOException ex) {
                Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            log.info("Connection faild.");
        }
        if (connection == null) {
                log.info("Failed to connect to database.");
	} else {
                log.info("Successfully connected to database.");
	}
        return connection;
    
    }
    
    
}
