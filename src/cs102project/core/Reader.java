package cs102project.core;

import cs102project.interfaces.DataAnalytics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader implements DataAnalytics {

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
        double riyadh = 0;
        double makkah = 0;
        double madinah = 0;
        double jeddah = 0;
        double abha = 0;
        HashMap<String, Double> hottest = new HashMap<>();
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
        hottest.put(Riyadh.getCityName(), riyadh);
        hottest.put(Makkah.getCityName(), makkah);
        hottest.put(Madinah.getCityName(), madinah);
        hottest.put(Jeddah.getCityName(), jeddah);
        hottest.put(Abha.getCityName(), abha);
        return hottest;
    }

    @Override
    public HashMap averageMeasurements(City city, Date d1, Date d2) {

        HashMap<String, Double> avgMeasurement = new HashMap<>();
        int i = 0;
        double temperature = 0;
        double humidity = 0;
        double pressure = 0;
        double distance = 0;
        for (City cityStored : savedData) {
            if (cityStored.getCityName().equalsIgnoreCase(city.getCityName())
                    && city.getDate().after(d1) && city.getDate().before(d2)) {
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

        avgMeasurement.put("Average Temperature", temperature);
        avgMeasurement.put("Average Humidity", humidity);
        avgMeasurement.put("Average Pressure", pressure);
        avgMeasurement.put("Average Distance", distance);

        return avgMeasurement;

    }

    @Override
    public TreeSet citiesByTemperature(Date d1, Date d2) {
        TreeSet<City> citiesByTemp = new TreeSet<>();
        int riyadhCount = 0;
        int makkahCount = 0;
        int madinaCount = 0;
        int jeddahCount = 0;
        int abhaCount = 0;
        double riyadh = 0;
        double makkah = 0;
        double madina = 0;
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
                } else if (city.getCityName().equalsIgnoreCase("madina")) {
                    madina += city.getTemperatureSensor().getValue();
                    madinaCount++;
                } else if (city.getCityName().equalsIgnoreCase("jeddah")) {
                    jeddah += city.getTemperatureSensor().getValue();
                    jeddahCount++;
                } else if (city.getCityName().equalsIgnoreCase("abha")) {
                    abha += city.getTemperatureSensor().getValue();
                    abhaCount++;
                }
            }
        }
        citiesByTemp.add(new City("Riyadh", new TemperatureSensor(riyadh / riyadhCount)));
        citiesByTemp.add(new City("Makkah", new TemperatureSensor(makkah / makkahCount)));
        citiesByTemp.add(new City("Madina", new TemperatureSensor(madina / madinaCount)));
        citiesByTemp.add(new City("Jeddah", new TemperatureSensor(jeddah / jeddahCount)));
        citiesByTemp.add(new City("Abha", new TemperatureSensor(abha / abhaCount)));
        return citiesByTemp;

    }

    @Override
    public HashMap alert(City city, Date d1, Date d2) {
        HashMap<String, Integer> cityAlerts = new HashMap<>();
        int distanceAlert = 0;
        int temperatureAlert = 0;
        int pressureAlert = 0;
        int humidityAlert = 0;
        for (City cityStored : savedData) {
            if (city.getCityName().equalsIgnoreCase(cityStored.getCityName())) {
                if (city.getDate().after(d1) && city.getDate().before(d2)) {
                    if (city.getDistanceSensor().getValue() < 21) {
                        distanceAlert += 1;
                    }
                    if (city.getTemperatureSensor().getValue() > 45) {
                        temperatureAlert += 1;
                    }
                    if (city.getPressureSensor().getValue() > 2050 || city.getPressureSensor().getValue() < 1010) {
                        pressureAlert += 1;
                    }
                    if (city.getHumiditySensor().getValue() > 35) {
                        humidityAlert += 1;
                    }
                }
            }
        }
        cityAlerts.put("Distance Alert", distanceAlert);
        cityAlerts.put("Temperature Alert", temperatureAlert);
        cityAlerts.put("Pressure Alert", pressureAlert);
        cityAlerts.put("Humidity Alert", humidityAlert);
        return cityAlerts;
    }

}
