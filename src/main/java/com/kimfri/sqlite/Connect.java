package com.kimfri.sqlite;

import java.sql.*;

/**
 *
 * @author Kim
 */
public class Connect {

    public static Connection conn = null;

    public Connection getConnection() {

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:d:\\sqlite-data\\firstdb.sqlite");
            //JOptionPane.showConfirmDialog(null, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        Connect c = new Connect();
        c.getConnection();
        c.doIt();
        //c.add();
        //c.add1000();
        conn.close();
    }

    public void doIt() {
        try {
            Statement statement = conn.createStatement();
            try (ResultSet rs = statement.executeQuery("select * from users")) {
                ResultSetMetaData rsm = rs.getMetaData();
                int columns = rsm.getColumnCount();
                System.err.println("columns: " + columns);
                while (rs.next()) {

                    for (int i = 1; i <= columns; i++) {
                        System.err.println("Column[" + i + "]: " + rsm.getColumnName(i) + ": " + rs.getString(rsm.getColumnName(i)));
                    }

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void add() {
        try {
            String sql = "INSERT INTO users(username, firstname, lastname) VALUES('Maria', 'Maria', 'Fritzon-Ölander')";
            Statement stat = conn.createStatement();
            int retval = stat.executeUpdate(sql);
            System.err.println("inserted: " + retval + " rows");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void add1000() {
        try {
            Statement stat = conn.createStatement();
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                String sql = "INSERT INTO users(username, firstname, lastname) VALUES('A-" + i + "', 'AA', 'BB')";
                int retval = stat.executeUpdate(sql);
                if (retval < 1) {
                    System.err.println("inserted: " + retval + " rows");
                }
                if (i % 10 == 0) {
                    System.err.println("i: " + i);
                }
            }
            long stop = System.currentTimeMillis();
            System.err.println("It took: " + (stop - start) / 1000);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
