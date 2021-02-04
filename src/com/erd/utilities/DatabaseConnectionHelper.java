package com.erd.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DatabaseConnectionHelper {
	private static Connection mainSource = null;
	public static Connection getSOConnection() throws SQLException, ClassNotFoundException {
			  Class.forName("com.mysql.cj.jdbc.Driver"); // mysql and jar file and setup
			  
			  mainSource=DriverManager.getConnection("jdbc:mysql://localhost:3306/foodorder","root","root");
    	return mainSource;
    }
}