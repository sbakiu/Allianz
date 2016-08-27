package com.allianz.simpleproject.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.allianz.simpleproject.dao.StudentDao;
import com.allianz.simpleproject.service.ApplicationConfig;

public class DBConnection {
	private static Logger logger = LogManager.getLogger(DBConnection.class);
	
	static private DBConnection _instance = null;
	static private Connection connection = null;
	static public String driver = null;
	static public String host = null;
	static public String port = null;
	static public String database = null;
	static public String username = null;
	static public String password = null;
	static public String url = null;

	protected DBConnection() {
		
		try {
			
			driver = ApplicationConfig.properties.getProperty("jdbc.driverClassName");
			host = ApplicationConfig.properties.getProperty("jdbc.host");
			port = ApplicationConfig.properties.getProperty("jdbc.port");
			database = ApplicationConfig.properties.getProperty("jdbc.database");
			username = ApplicationConfig.properties.getProperty("jdbc.username");
			password = ApplicationConfig.properties.getProperty("jdbc.password");
			url = ApplicationConfig.properties.getProperty("jdbc.url");

			// Create database connection
			Class.forName(driver);
			String connectionURL = url + host + ":" + port + "/" + database;
			connection = DriverManager.getConnection(connectionURL, username, password);

		} catch (SQLException ex) {
		    // handle any errors
			logger.error(ex, ex.getCause());
		} catch (Exception e) {
			logger.error(e, e.getCause());
			System.out.println("error" + e);
		}
	}

	static public DBConnection getInstance() {
		if (_instance == null) {
			_instance = new DBConnection();
		}
		return _instance;
	}
	
	public Connection getConnection() {
        return connection; 
    }


}
