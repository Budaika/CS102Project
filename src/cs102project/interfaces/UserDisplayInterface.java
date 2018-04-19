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
public interface UserDisplayInterface {

    public abstract void mainMenu();

    public abstract void displayHottestTemperature();

    public abstract void displayAverageMeasurements();

    public abstract void displayCitiesByTemperature();

    public abstract void displayAlert();

    public abstract void confirmation();

    public abstract void close();

}
