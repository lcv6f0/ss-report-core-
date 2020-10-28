/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Christian
 */
public class Sequences {

    private ArrayList<Pump> pumpList;
    private LocalDate current_date;
    private LocalTime time;
    private String name;
    private ArrayList<Product> productList;

    public Sequences(LocalDate current_date, LocalTime time, String name) {
        pumpList = new ArrayList<>();
        this.current_date = current_date;
        this.time = time;
        this.name = name;
    }

    private void setProducts() {
        productList = new ArrayList<>();
        productList.add(new Product("name", 00));
        productList.add(new Product("name1", 00));
        productList.add(new Product("name2", 00));
        productList.add(new Product("name3", 00));

    }

    public void setProducts(ArrayList<Product> p) {
        productList = p;

    }

    public void addPump(Pump p) {
        pumpList.add(p);
    }

    public void addAllPumps(List<Pump> l) {
        pumpList.addAll(l);
    }

    public ArrayList<Pump> getPumps() {
        return pumpList;
    }

    public LocalDate getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(LocalDate current_date) {
        this.current_date = current_date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-------Sequence------").append("\nSequence Name: ").append(name);
        sb.append("\n\n");
        pumpList.forEach((pump) -> {
            sb.append(pump.toString());

        });

        return sb.toString();
    }

}
