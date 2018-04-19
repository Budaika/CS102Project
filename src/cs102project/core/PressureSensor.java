package cs102project.core;

import cs102project.interfaces.Measurable;

public class PressureSensor extends SensorParent {

    private final String name = "Pressure Sensor";
    
    public PressureSensor(double value, String unit) {
        setValue(value);
        setUnit(unit);
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
