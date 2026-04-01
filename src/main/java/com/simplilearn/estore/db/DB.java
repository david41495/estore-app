package com.simplilearn.estore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    private static final DB INSTANCE = new DB();
    private Connection connection;

    private DB() {
    	  try {
    	    Class.forName("com.mysql.cj.jdbc.Driver");
    	    initialize();                         // ← keep this call
    	    System.out.println("[DB] Connected OK");
    	  } catch (Throwable t) {
    	    System.err.println("[DB] INIT FAILED: " + t.getClass().getName() + " - " + t.getMessage());
    	    t.printStackTrace();
    	    throw new RuntimeException("DB.initialize failed", t);
    	  }
    	}


    private void initialize() {
        try {
            String url = "jdbc:mysql://localhost:3306/estore"
                       + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String user = "root";                 // <— change if you created an app user
            String password = "root";

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("[DB] Connected.");
        } catch (SQLException e) {
            throw new RuntimeException("DB.initialize failed", e);
        }
    }

    private void ensureOpen() throws SQLException {
        if (connection == null || connection.isClosed()) {
            initialize();
        }
    }


    public ResultSet executeQuery(String sql) throws SQLException {
        ensureOpen();

        Statement st = connection.createStatement(
                ResultSet.TYPE_FORWARD_ONLY,
                ResultSet.CONCUR_READ_ONLY);
        return st.executeQuery(sql);
    }


    public int executeUpdate(String sql) throws SQLException {
        ensureOpen();
        try (Statement st = connection.createStatement()) {
            return st.executeUpdate(sql);
        }
    }


    public int executeInsertReturnId(String sql) throws SQLException {
        ensureOpen();
        try (Statement st = connection.createStatement()) {
            int affected = st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            if (affected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public static DB getDB() {
        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            ensureOpen();
        } catch (SQLException e) {
            throw new RuntimeException("DB.getConnection check failed", e);
        }
        return connection;
    }
}
