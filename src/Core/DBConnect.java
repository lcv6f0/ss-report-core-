package Core;

import Utils.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect extends Thread {

    Connection conn = null;
    Statement stmt = null;
    boolean connection;

    public DBConnect() {
    }

    public void run(String userName, String password) {
        String ip = Util.getProperty("ip");
        String driver = Util.getProperty("driver");
        String port = Util.getProperty("port");
        String dbName = Util.getProperty("database");

        StringBuilder url = new StringBuilder();
        url.append(driver).append(ip).append(":").append(port).append("/").append(dbName);
        System.out.println(url.toString());
        try {
            conn = DriverManager.getConnection(url.toString(), userName, password);
            stmt = conn.createStatement();
            connection = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            Util.writeToErrorLog(e.getMessage());

            connection = false;
        }
    }

    @Override
    public void run() {
        String ip = Util.getProperty("ip");
        String driver = Util.getProperty("driver");
        String port = Util.getProperty("port");
        String dbName = Util.getProperty("database");
        String userName = Util.getProperty("userId");
        String password = Util.getProperty("password");
        StringBuilder url = new StringBuilder();
        url.append(driver).append(ip).append(":").append(port).append("/").append(dbName);
        System.out.println(url.toString());
        try {
            conn = DriverManager.getConnection(url.toString(), userName, password);
            stmt = conn.createStatement();
            connection = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            Util.writeToErrorLog(e.getMessage());

            connection = false;
        }
    }

    public DBConnect(String nul) {
        String ip = Util.getProperty("ip");
        String driver = Util.getProperty("driver");
        String port = Util.getProperty("port");
        String dbName = Util.getProperty("database");
        String userName = Util.getProperty("userId");
        String password = Util.getProperty("password");
        StringBuilder url = new StringBuilder();
        url.append(driver).append(ip).append(":").append(port).append("/").append(dbName);
        System.out.println(url.toString());
        try {
            conn = DriverManager.getConnection(url.toString(), userName, password);
            connection = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            connection = false;
        }
    }

    public boolean getConnectionStatus() {

        try {
            return conn.isValid(1);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }catch (NullPointerException  n){
           return false;
        }
    }
//    public boolean getConnectionStatus() {
//        
//        boolean test = false;
//        try {
//            test = conn.isValid(1);
//            while (!test) {
//                run();
//                if (Util.debugOn()) {
//                    Util.writeToErrorLog("Connection to Database lost, trying to reconnect");
//                }
//                 test = conn.isValid(1);
//            }
//            return test;
//        } catch (Exception ex) {
//            //   Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("connection dropped");
//            System.err.println(ex.getMessage());
//            Util.writeToErrorLog(ex.getMessage());
//            return false;
//        }
//    }

    public void close() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            Util.writeToErrorLog("Could not close the current connection");
            System.out.println("Could not close the current connection.");
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public Statement getStatement() {
        return stmt;
    }

//    public static void main(String[] args) {
//        System.out.println(DBConnect.ping("192.168.1.1"));
//    }
//    public static boolean ping(String ip) {
//
//        try {
//            return InetAddress.getByName(ip).isReachable(500);
//
//        } catch (IOException ex) {
//            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        }
//
//    }
}
