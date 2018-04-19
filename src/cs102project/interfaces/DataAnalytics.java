/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102project.interfaces;

import cs102project.core.City;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;

/**
 *
 * @author user
 */
public interface DataAnalytics {

    public abstract HashMap hottestTemperature(Date d1, Date d2);

    public abstract HashMap averageMeasurements(City city, Date d1, Date d2);

    public abstract TreeSet citiesByTemperature(Date d1, Date d2);

    public abstract HashMap alert(City city, Date d1, Date d2);

}
