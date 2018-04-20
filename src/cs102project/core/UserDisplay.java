package cs102project.core;

import cs102project.interfaces.UserDisplayInterface;

import java.util.Date;
import java.util.Scanner;
import java.util.TreeSet;

public class UserDisplay implements UserDisplayInterface {

    Scanner input = new Scanner(System.in);
    private Reader reader = new Reader();

    public void welcome() {
        System.out.println("Welcome User!\nIn this application you will have \n"
                + "access to a database of different cities and their details\n"
                + "related to Temperature, Pressure, Humidity and Distance. ");
        System.out.println("\nWould you like to see the Main Menu?");
        String userInput = input.nextLine();
        if (userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y")) {
            mainMenu();
        } else if (userInput.equalsIgnoreCase("no") || userInput.equalsIgnoreCase("n")) {
            close();
        } else {
            throw new IllegalArgumentException("Wrong Input");
        }
    }

    @Override
    public void mainMenu() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Please choose an option from the menu!\n");
        System.out.println("1. Display the hottest temperature between two dates");
        System.out.println("2. Display the average attributres of a city");
        System.out.println("3. Display the cities ordered by their average temperature\n"
                + "between two given dates");
        System.out.println("4. Display city alerts");
        System.out.println("5. Close program");
        System.out.println("--------------------------------------------------------------");
        int userInput = input.nextInt();

        switch (userInput) {
            case 1:
                displayHottestTemperature();
                break;
            case 2:
                displayAverageMeasurements();
                break;
            case 3:
                displayCitiesByTemperature();
                break;
            case 4:
                displayAlert();
                break;
            case 5:
                close();
                break;
            default:
                throw new IllegalArgumentException("wrong input");
        }

    }

    @Override
    public void displayHottestTemperature() {
        reader.read();
        System.out.println("Please enter the two dates to search for the hottest\n"
                + "temperature of each city within the two dates: ");
        int day1 = input.nextInt();
        int month1 = input.nextInt();
        int year1 = input.nextInt();
        Date d1 = new Date(day1, month1, year1);
        System.out.print("\nNow enter the second date: ");
        int day2 = input.nextInt();
        int month2 = input.nextInt();
        int year2 = input.nextInt();
        Date d2 = new Date(day2, month2, year2);
        System.out.println("\n");

        System.out.println("Riyadh: " + reader.hottestTemperature(d1, d2).get("Riyadh"));
        System.out.println("Makkah: " + reader.hottestTemperature(d1, d2).get("Makkah"));
        System.out.println("Madina: " + reader.hottestTemperature(d1, d2).get("Madinah"));
        System.out.println("Jeddah: " + reader.hottestTemperature(d1, d2).get("Jeddah"));
        System.out.println("Abha: " + reader.hottestTemperature(d1, d2).get("Abha"));

        System.out.println("\n\nIf you want to go back to the main menu type 1, type 2 to exit.");
        int userInput = input.nextInt();
        if (userInput == 1) {
            mainMenu();
        } else if (userInput == 2) {
            close();
        } else {
            throw new IllegalArgumentException("wrong input");
        }

    }

    @Override
    public void displayAverageMeasurements() {
        reader.read();
        System.out.println("Please enter the city and two dates to get the average\n"
                + "of each of that city's attributes within the two dates. ");
        System.out.print("Enter the name of the city: ");
        String city = input.next();
        System.out.print("\n\nNow enter the first date: ");
        int day1 = input.nextInt();
        int month1 = input.nextInt();
        int year1 = input.nextInt();
        Date d1 = new Date(day1, month1, year1);
        System.out.print("\n\nNow enter the second date: ");
        int day2 = input.nextInt();
        int month2 = input.nextInt();
        int year2 = input.nextInt();
        Date d2 = new Date(day2, month2, year2);

        if (city.equalsIgnoreCase("riyadh") || city.equalsIgnoreCase("makkah")
                || city.equalsIgnoreCase("madinah") || city.equalsIgnoreCase("jeddah")
                ||city.equalsIgnoreCase("abha")) {
            System.out.println("City name: " + city);
            System.out.println("Average Temperature: " + reader.averageMeasurements(new City(city), d1, d2).get("Average Temperature"));
            System.out.println("Average Humidity: " + reader.averageMeasurements(new City(city), d1, d2).get("Average Humidity"));
            System.out.println("Average Pressure: " + reader.averageMeasurements(new City(city), d1, d2).get("Average Pressure"));
            System.out.println("Average Distance: " + reader.averageMeasurements(new City(city), d1, d2).get("Average Distance"));
        } else {
            throw new IllegalArgumentException("wrong input");
        }

        System.out.println("\n\nIf you want to go back to the main menu type 1, type 2 to exit.");
        int userInput = input.nextInt();
        if (userInput == 1) {
            mainMenu();
        } else if (userInput == 2) {
            close();
        } else {
            throw new IllegalArgumentException("wrong input");
        }

    }

    @Override
    public void displayCitiesByTemperature() {
        reader.read();
        System.out.println("\nPlease enter the two dates to get a set of cities\n"
                + " sorted by their increasing average temperature within the two dates.\n");
        int day1 = input.nextInt();
        int month1 = input.nextInt();
        int year1 = input.nextInt();
        Date d1 = new Date(day1, month1, year1);
        System.out.print("\nNow enter the second date: ");
        int day2 = input.nextInt();
        int month2 = input.nextInt();
        int year2 = input.nextInt();
        Date d2 = new Date(day2, month2, year2);
        System.out.println("");
        int i = 1;
        TreeSet<City> citySet = (TreeSet) reader.citiesByTemperature(d1, d2).clone();
        for (City city : (citySet)) {
            System.out.println(i + ". " + city.getCityName() + " Average temperature: "
                    + city.getTemperatureSensor().getAverageValue() + " C");

        }

        System.out.println("\n\nIf you want to go back to the main menu type 1, type 2 to exit.");
        int userInput = input.nextInt();
        if (userInput == 1) {
            mainMenu();
        } else if (userInput == 2) {
            close();
        } else {
            throw new IllegalArgumentException("wrong input");
        }

    }

    @Override
    public void displayAlert() {
        reader.read();
        System.out.println("\nPlease enter the city and two dates to get the \n"
                + "number of alerts of each kind with within the two dates of the city");
        System.out.print("\nEnter the name of the city: ");
        String city = input.next();
        System.out.print("\nNow enter the first date: ");
        int day1 = input.nextInt();
        int month1 = input.nextInt();
        int year1 = input.nextInt();
        Date d1 = new Date(day1, month1, year1);
        System.out.print("\nNow enter the second date: ");
        int day2 = input.nextInt();
        int month2 = input.nextInt();
        int year2 = input.nextInt();
        Date d2 = new Date(day2, month2, year2);
        System.out.println("\n");

        System.out.println("Alerts of City " + city);
        System.out.println("Distance smaller than 21 m : " + reader.alert(new City(city), d1, d2).get("Distance Alert"));
        System.out.println("Temperature higher than 45 degrees : " + reader.alert(new City(city), d1, d2).get("Temperature Alert"));
        System.out.println("Pressure higher than 2050 or lower than 1010 : " + reader.alert(new City(city), d1, d2).get("Pressure Alert"));
        System.out.println("Humidity higher than 35 : " + reader.alert(new City(city), d1, d2).get("Humidity Alert"));

        System.out.println("\n\nIf you want to go back to the main menu type 1 if you\n"
                + "if you want to exit type 2.");
        int userInput = input.nextInt();
        if (userInput == 1) {
            mainMenu();
        } else if (userInput == 2) {
            close();
        } else {
            throw new IllegalArgumentException("wrong input");
        }
    }

    @Override
    public void confirmation() {
        System.out.println("Are you sure you want to exit the program?");
        String userInput = input.nextLine();
        if (userInput.equalsIgnoreCase("no") || userInput.equalsIgnoreCase("n")) {
            mainMenu();
        } else if (userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y")) {
            close();
        } else {
            throw new IllegalArgumentException("Wrong Input");
        }
    }

    @Override
    public void close() {
        System.out.println("Thanks and goodbye!");
    }

}
