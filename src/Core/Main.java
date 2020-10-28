/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Utils.Util;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author Christian
 */
public class Main {

    private DBConnect db;
    private int rc;
    private ArrayList<Product> productList;
    private ArrayList<Workers> WorkerList;
    private Sequences seg1;

    private void loadProduct() {
        productList = new ArrayList<>();
        productList.add(new Product("name", 00));
        productList.add(new Product("name1", 00));
        productList.add(new Product("name2", 00));
        productList.add(new Product("name3", 00));
    }

    private Product search(String productName) {
        for (Product product : productList) {
            if (product.toString().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    private void loadWorker_List() {
        WorkerList = new ArrayList<>();
        WorkerList.add(new Workers("Jhon", "Dow"));
        WorkerList.add(new Workers("Janne", "Hi"));
        WorkerList.add(new Workers("JK", "Child"));
    }

    private void starter() {
        seg1 = new Sequences(LocalDate.now(), LocalTime.now(), "Seq1");

    }

    private void savePumps() {
// saving pump
        PreparedStatement ps = null;
        while (!db.getConnectionStatus()) {
            System.err.println("Connection error, Trying to reconnect");
            db.run();
            Util.writeToErrorLog("Connection error, Trying to reconnect");
        }
        productList.forEach((n) -> {

        });
        String query = ("insert into pumps (pumpid,seller_name,segment) VALUES(?,?,?)");
        try {

            ps = db.getConnection().prepareStatement(query);

//                System.out.println(visiteID);
            ps.setString(1, Util.addEscape(""));

            rc = ps.executeUpdate();

        } catch (SQLException sqe) {
            String message = sqe.getMessage();
            if (Util.debugOn()) {
                Util.writeToInfoLog("Cannot save record. visit error log fpr more info");
                Util.writeToErrorLog("Cannot save record.\nMessage: " + message);

            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException sqe) {
                String message = sqe.getMessage();
                if (Util.debugOn()) {
                    Util.writeToInfoLog("Cannot save record. visit error log fpr more info");
                    Util.writeToErrorLog("Cannot save record.\nMessage: " + message);

                }
            }
        }
    }

    private void saveProduct() {
// saving pump
        PreparedStatement ps = null;
        while (!db.getConnectionStatus()) {
            System.err.println("Connection error, Trying to reconnect");
            db.run();
            Util.writeToErrorLog("Connection error, Trying to reconnect");
        }

        String query = ("insert into pumps (pumpid,name,price,opening_meter,closing_meter) VALUES(?,?,?,?,?)");
        try {

            ps = db.getConnection().prepareStatement(query);

//                System.out.println(visiteID);
            ps.setString(1, Util.addEscape(""));

            rc = ps.executeUpdate();

        } catch (SQLException sqe) {
            String message = sqe.getMessage();
            if (Util.debugOn()) {
                Util.writeToInfoLog("Cannot save record. visit error log fpr more info");
                Util.writeToErrorLog("Cannot save record.\nMessage: " + message);

            }
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException sqe) {
                String message = sqe.getMessage();
                if (Util.debugOn()) {
                    Util.writeToInfoLog("Cannot save record. visit error log fpr more info");
                    Util.writeToErrorLog("Cannot save record.\nMessage: " + message);

                }
            }
        }
    }

    private void test() {
        Sequences sc = new Sequences(null, null, "Hello World");
        sc.setProducts(productList);
        Pump p = new Pump(WorkerList.get(0));
        p.addProduct(search("jhon"), new Meter(150, 132));
        p.addProduct(search("Janne"), new Meter(150, 132));
        p.addProduct(search("JK"), new Meter(150, 132));
        sc.addPump(p);

    }
}
