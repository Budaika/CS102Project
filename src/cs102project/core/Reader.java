package cs102project.core;

import cs102project.interfaces.DataAnalytics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader implements DataAnalytics<City, Date> {
    Map<String, Double> methodMap;
    private File bigData;
    private Scanner inputFile;
    private ArrayList<City> savedData;

    public Reader() {
    }

    public void read() {
        try {
            bigData = new File("sensor-data.txt");
            inputFile = new Scanner(bigData);
            savedData = new ArrayList<>();

            while (inputFile.hasNext()) {
                String[] splitter = inputFile.nextLine().split(";");
                String[] dateSplitter = splitter[11].split("/");
                savedData.add(new City(splitter[0], Double.parseDouble(splitter[1]), Double.parseDouble(splitter[2]),
                        new TemperatureSensor(Double.parseDouble(splitter[3]), splitter[4]),
                        new HumiditySensor(Double.parseDouble(splitter[5]), splitter[6]),
                        new PressureSensor(Double.parseDouble(splitter[7]), splitter[8]),
                        new DistanceSensor(Double.parseDouble(splitter[9]), splitter[10]),
                        new Date(Integer.parseInt(dateSplitter[0]), Integer.parseInt(dateSplitter[1]), Integer.parseInt(dateSplitter[2]))));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<City> getSavedData() {
        return savedData;
    }

    @Override
    public HashMap hottestTemperature(Date d1, Date d2) {
        methodMap = new HashMap<>();
        double riyadh = 0;
        double makkah = 0;
        double madinah = 0;
        double jeddah = 0;
        double abha = 0;
        for (City city : savedData) {
            if (city.getDate().after(d1) && city.getDate().before(d2)) {
                if (city.getCityName().equalsIgnoreCase("riyadh")) {
                    if (city.getTemperatureSensor().getValue() > riyadh) {
                        riyadh = city.getTemperatureSensor().getValue();
                    }
                } else if (city.getCityName().equalsIgnoreCase("makkah")) {
                    if (city.getTemperatureSensor().getValue() > makkah) {
                        makkah = city.getTemperatureSensor().getValue();
                    }
                } else if (city.getCityName().equalsIgnoreCase("madinah")) {
                    if (city.getTemperatureSensor().getValue() > madinah) {
                        madinah = city.getTemperatureSensor().getValue();
                    }
                } else if (city.getCityName().equalsIgnoreCase("jeddah")) {
                    if (city.getTemperatureSensor().getValue() > jeddah) {
                        jeddah = city.getTemperatureSensor().getValue();
                    }
                } else if (city.getCityName().equalsIgnoreCase("abha")) {
                    if (city.getTemperatureSensor().getValue() > abha) {
                        abha = city.getTemperatureSensor().getValue();
                    }
                }
            }
        }
        City Riyadh = new City("Riyadh", new TemperatureSensor(riyadh));
        City Makkah = new City("Makkah", new TemperatureSensor(makkah));
        City Madinah = new City("Madinah", new TemperatureSensor(madinah));
        City Jeddah = new City("Jeddah", new TemperatureSensor(jeddah));
        City Abha = new City("Abha", new TemperatureSensor(abha));
        methodMap.put(Riyadh.getCityName(), riyadh);
        methodMap.put(Makkah.getCityName(), makkah);
        methodMap.put(Madinah.getCityName(), madinah);
        methodMap.put(Jeddah.getCityName(), jeddah);
        methodMap.put(Abha.getCityName(), abha);
        return (HashMap)methodMap;
    }

    @Override
    public HashMap averageMeasurements(City city, Date d1, Date d2) {

        methodMap = new HashMap<>();
        int i = 0;
        double temperature = 0;
        double humidity = 0;
        double pressure = 0;
        double distance = 0;
        for (City cityStored : savedData) {
            if (cityStored.getCityName().equalsIgnoreCase(city.getCityName())
                    && cityStored.getDate().after(d1) && cityStored.getDate().before(d2)) {
                i++;
                temperature += cityStored.getTemperatureSensor().getValue();
                humidity += cityStored.getHumiditySensor().getValue();
                pressure += cityStored.getPressureSensor().getValue();
                distance += cityStored.getDistanceSensor().getValue();
            }
        }
        temperature /= i;
        humidity /= i;
        pressure /= i;
        distance /= i;

        methodMap.put("Average Temperature", temperature);
        methodMap.put("Average Humidity", humidity);
        methodMap.put("Average Pressure", pressure);
        methodMap.put("Average Distance", distance);

        return (HashMap)methodMap;

    }

    @Override
    public ArrayList citiesByTemperature(Date d1, Date d2) {
        ArrayList<City> citiesByTemp = new ArrayList<>();
        double riyadhCount = 0;
        double makkahCount = 0;
        double madinahCount = 0;
        double jeddahCount = 0;
        double abhaCount = 0;
        double riyadh = 0;
        double makkah = 0;
        double madinah = 0;
        double jeddah = 0;
        double abha = 0;
        for (City city : savedData) {
            if (city.getDate().after(d1) && city.getDate().before(d2)) {
                if (city.getCityName().equalsIgnoreCase("riyadh")) {
                    riyadh += city.getTemperatureSensor().getValue();
                    riyadhCount++;
                } else if (city.getCityName().equalsIgnoreCase("makkah")) {
                    makkah += city.getTemperatureSensor().getValue();
                    makkahCount++;
                } else if (city.getCityName().equalsIgnoreCase("madinah")) {
                    madinah += city.getTemperatureSensor().getValue();
                    madinahCount++;
                } else if (city.getCityName().equalsIgnoreCase("jeddah")) {
                    jeddah += city.getTemperatureSensor().getValue();
                    jeddahCount++;
                } else if (city.getCityName().equalsIgnoreCase("abha")) {
                    abha += city.getTemperatureSensor().getValue();
                    abhaCount++;
                }
            }
        }
        riyadh /= riyadhCount;
        makkah /= makkahCount;
        madinah /= madinahCount;
        jeddah /= jeddahCount;
        abha /= abhaCount;
        
                
        citiesByTemp.add(new City("Riyadh", new TemperatureSensor(riyadh)));
        citiesByTemp.add(new City("Makkah", new TemperatureSensor(makkah)));
        citiesByTemp.add(new City("Madinah", new TemperatureSensor(madinah)));
        citiesByTemp.add(new City("Jeddah", new TemperatureSensor(jeddah)));
        citiesByTemp.add(new City("Abha", new TemperatureSensor(abha)));
        Collections.sort(citiesByTemp);
        return citiesByTemp;

    }

    @Override
    public HashMap alert(City city, Date d1, Date d2) {
        methodMap = new HashMap<>();
        int distanceAlert = 0;
        int temperatureAlert = 0;
        int pressureAlert = 0;
        int humidityAlert = 0;
        for (City cityStored : savedData) {
            if (cityStored.getCityName().equalsIgnoreCase(city.getCityName())) {
                if (cityStored.getDate().after(d1) && cityStored.getDate().before(d2)) {
                    if (cityStored.getDistanceSensor().getValue() < 21) {
                        distanceAlert += 1;
                    }
                    if (cityStored.getTemperatureSensor().getValue() > 45) {
                        temperatureAlert += 1;
                    }
                    if (cityStored.getPressureSensor().getValue() > 2050 || cityStored.getPressureSensor().getValue() < 1010) {
                        pressureAlert += 1;
                    }
                    if (cityStored.getHumiditySensor().getValue() > 35) {
                        humidityAlert += 1;
                    }
                }
            }
        }
        methodMap.put("Distance Alert", (double)distanceAlert);
        methodMap.put("Temperature Alert", (double)temperatureAlert);
        methodMap.put("Pressure Alert", (double)pressureAlert);
        methodMap.put("Humidity Alert", (double)humidityAlert);
        return (HashMap)methodMap;
    }

}
