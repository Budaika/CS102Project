package cs102project.core;

import cs102project.interfaces.CityInterface;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;

public class City implements CityInterface, Comparable<City> {

    private Date date;
    private String cityName;
    private double latitude;
    private double longitude;
    private TemperatureSensor temperatureSensor;
    private HumiditySensor humiditySensor;
    private PressureSensor pressureSensor;
    private DistanceSensor distanceSensor;

    public City(String cityName, double latitude, double longitude, TemperatureSensor temperatureSensor,
            HumiditySensor humiditySensor, PressureSensor pressureSensor, DistanceSensor distanceSensor, Date date) {
        setCityName(cityName);
        setLatitude(latitude);
        setLongitude(longitude);
        setTemperatureSensor(temperatureSensor);
        setHumiditySensor(humiditySensor);
        setPressureSensor(pressureSensor);
        setDistanceSensor(distanceSensor);
        setDate(date);
        
    }
    
    public City(String cityName) {
        setCityName(cityName);
    }
    
    public City(String cityName, TemperatureSensor temperatureSensor) {
        setCityName(cityName);
        setTemperatureSensor(temperatureSensor);
        
    }


    @Override
    public String getCityName() {
        return cityName;
    }

    @Override
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public double getLatitude() {
        return this.latitude;
    }

    @Override
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public double getLongitude() {
        return this.longitude;
    }

    @Override
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public TemperatureSensor getTemperatureSensor() {
        return this.temperatureSensor;
    }

    @Override
    public void setTemperatureSensor(TemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }

    @Override
    public HumiditySensor getHumiditySensor() {
        return this.humiditySensor;
    }

    @Override
    public void setHumiditySensor(HumiditySensor humiditySensor) {
        this.humiditySensor = humiditySensor;
    }

    @Override
    public PressureSensor getPressureSensor() {
        return this.pressureSensor;
    }

    @Override
    public void setPressureSensor(PressureSensor pressureSensor) {
        this.pressureSensor = pressureSensor;
    }

    @Override
    public DistanceSensor getDistanceSensor() {
        return this.distanceSensor;
    }

    @Override
    public void setDistanceSensor(DistanceSensor distanceSensor) {
        this.distanceSensor = distanceSensor;
    }

    @Override
    public int compareTo(City city) {
        if(this.temperatureSensor.getAverageValue() - city.temperatureSensor.getAverageValue() > 0) {
            return 1;
        } 
        else if(this.temperatureSensor.getAverageValue() - city.temperatureSensor.getAverageValue() < 0) {
            return -1;
        }
        else {
            return 0;
        }
    }

}
