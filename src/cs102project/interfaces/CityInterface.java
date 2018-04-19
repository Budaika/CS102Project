/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102project.interfaces;

import cs102project.core.DistanceSensor;
import cs102project.core.HumiditySensor;
import cs102project.core.PressureSensor;
import cs102project.core.TemperatureSensor;
import java.util.Date;

/**
 *
 * @author user
 */
public interface CityInterface {

    public abstract String getCityName();

    public abstract void setCityName(String cityName);

    public abstract double getLatitude();

    public abstract void setLatitude(double latitude);

    public abstract double getLongitude();

    public abstract void setLongitude(double longitude);

    public abstract TemperatureSensor getTemperatureSensor();

    public abstract void setTemperatureSensor(TemperatureSensor temperatureSensor);

    public abstract HumiditySensor getHumiditySensor();
    
    public abstract void setHumiditySensor(HumiditySensor humiditySensor);

    public abstract PressureSensor getPressureSensor();
    
    public abstract void setPressureSensor(PressureSensor pressureSensor);

    public abstract DistanceSensor getDistanceSensor();
    
    public abstract void setDistanceSensor(DistanceSensor distanceSensor);

    public abstract Date getDate();

    public abstract void setDate(Date date);

}
