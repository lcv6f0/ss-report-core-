/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Utils.Pair;
import java.util.ArrayList;

/**
 *
 * @author Christian
 */
public class Pump {

    private Workers worker1;
    private String name;
    private ArrayList<Pair<Product, Meter>> productList;

    public Pump(Workers worker1) {
        this.worker1 = worker1;
        productList = new ArrayList<>();

    }

    public void addProduct(Product product, Meter meter) {
        productList.add(new Pair<Product, Meter>(product, meter));
    }

    public static void main(String[] args) {
        Pump p = new Pump(new Workers("Jhon", "Doe"));
//        p.addProduct("pump1", new Product("Gazoline", 56, new Meter("pump_1_gazoline", 50.50, 258.005)));
//        p.addProduct("pump1", new Product("Disek", 120, new Meter("pump_1_Disel", 85.3, 250.6)));
        System.out.println(p.toString());
    }

    Pump() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Workers getWorker1() {
        return worker1;
    }

    public void setWorker1(Workers worker1) {
        this.worker1 = worker1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nPump Name: ").append(name).append("\n");
        sb.append(worker1.toString()).append("\n");
        productList.forEach((s) -> {
            sb.append(s.toString()).append("\n");
        });
        return sb.toString();
    }
}
