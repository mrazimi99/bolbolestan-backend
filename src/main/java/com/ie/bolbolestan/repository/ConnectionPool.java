package com.ie.bolbolestan.repository;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPool {
    private static BasicDataSource ds = new BasicDataSource();
    private final static String dockerMysqlPath = "jdbc:mysql://docker-mysql:3306/";
    private final static String dbName = "BolbolestanDatabase";
    private final static String dbURL = "jdbc:mysql://bolbolestan-mysql.amin-baqershahi-ns:3306/" + dbName;
    private final static String dbUserName = "root";
    private final static String dbPassword = "password";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL + "?user=" + System.getenv("DB_USERNAME") + "&" + "password=" + System.getenv("DB_PASSWORD"));
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        ds.setUsername(System.getenv("DB_USERNAME"));
        ds.setPassword(System.getenv("DB_PASSWORD"));
        ds.setUrl(dbURL);
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
        setEncoding();
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void setEncoding(){
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute("ALTER DATABASE BolbolestanDatabase CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;");
            connection.close();
            statement.close();
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
