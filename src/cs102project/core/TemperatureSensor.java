package cs102project.core;

import cs102project.interfaces.Measurable;

public class TemperatureSensor extends SensorParent {

    private final String name = "Temperature Sensor";
    private double averageValue;

    public TemperatureSensor(double value, String unit) {
        setValue(value);
        setUnit(unit);
    }
    
    public TemperatureSensor(double averageTemperature) {
        setAverageValue(averageValue);
    }
    
    public double getAverageValue() {
        return this.averageValue;
    }
    
    public void setAverageValue(double averageValue) {
        this.averageValue = averageValue;
    }

    @Override
    public double getValue() {
        return super.value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String getUnit() {
        return super.unit;
    }

    @Override
    public void setUnit(String unit) {
        super.unit = unit;
    }

    @Override
    public int compareTo(Measurable t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        return this.name;
    }

}
