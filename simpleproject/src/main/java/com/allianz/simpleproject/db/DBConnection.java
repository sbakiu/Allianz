package com.allianz.simpleproject.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
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
			// Read properties file
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream file = classLoader.getResourceAsStream("WEB-INF/db.properties");
			//InputStream file = new FileInputStream(new File("db.properties"));
			Properties properties = new Properties();
			properties.load(file);
			driver = properties.getProperty("jdbc.driverClassName");
			host = properties.getProperty("jdbc.host");
			port = properties.getProperty("jdbc.port");
			database = properties.getProperty("jdbc.database");
			username = properties.getProperty("jdbc.username");
			password = properties.getProperty("jdbc.password");
			url = properties.getProperty("jdbc.url");

			// Create database connection
			Class.forName(driver);
			String connectionURL = url + host + ":" + port + "/" + database;
			connection = DriverManager.getConnection(connectionURL, username, password);

		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} catch (Exception e) {
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
