/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

/**
 *
 * @author Christian
 */
public class Meter {

    private double opening, closing;

    public Meter(double start, double end) {

        this.opening = start;
        this.closing = end;
    }

    public double getOpening() {
        return opening;
    }

    public void setStart(int start) {
        this.opening = start;
    }

    public double getClosing() {
        return closing;
    }

    public void setEnd(int end) {
        this.closing = end;
    }

    public double getDiff() {
        return closing - opening;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("----------Meter----------").append("\n");
        sb.append("Opening: ").append(opening);
        sb.append("\nClosing: ").append(closing);
        sb.append("\nTotal Sold: ").append(getDiff());
        return sb.toString();

    }
}
