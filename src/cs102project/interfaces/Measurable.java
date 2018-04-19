/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102project.interfaces;

/**
 *
 * @author user
 */
public interface Measurable extends Comparable<Measurable> {

    public abstract double getValue();

    public abstract void setValue(double value);

    public abstract String getUnit();

    public abstract void setUnit(String unit);

}
