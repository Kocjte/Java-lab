package Prova;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

abstract class Vehicle {
    private String name;
    public double distance;
    public double l_fuelUsed;
    public double g_fuelUsed;
    

    public Vehicle(String name, double distance, double l_fuelUsed,double g_fuelUsed) {
        this.name = name;
        this.distance = distance;
        this.l_fuelUsed = l_fuelUsed;
        this.g_fuelUsed = g_fuelUsed;
    }

    public String getName() {
        return name;
    }

    public abstract double efficiency();
}

class Car extends Vehicle {
    private double numberHeatedSeats;

    public Car(String name, double distance, double l_fuelUsed, double g_fuelUsed, double numberHeatedSeats) {
        super(name, distance, l_fuelUsed, g_fuelUsed);
        this.numberHeatedSeats = numberHeatedSeats;
    }

    @Override
    public double efficiency() {
        return distance / l_fuelUsed;
    }

    public double electricConOfHeatedSeats() {
        return numberHeatedSeats * 50; // 50 is in watts, the medium cost
    }
}

class Motorcycle extends Vehicle {
    private double numberOfSafetyGear;
 

    public Motorcycle(String name, double distance, double l_fuelUsed, double g_fuelUsed, double numberOfSafetyGear, double engineSize) {
        super(name, distance, l_fuelUsed, g_fuelUsed);
        this.numberOfSafetyGear = numberOfSafetyGear;

    }

    @Override
    public double efficiency() {
        return (distance / (g_fuelUsed/3.7)); //divided by (1 gallon = 3.7 liters)
    }

    public double safetyGearCost() {
        return numberOfSafetyGear * 75; // 75 is the medium price per piece
    }
}

public class VehicleCollectionExample {
    public static void main(String[] args) {
        List<Vehicle> ParkingLot1 = new ArrayList<>();
        List<Vehicle> ParkingLot2 = new ArrayList<>();
        List<Vehicle> ParkingLot3 = new ArrayList<>();

        int carlot1 = 3; // Total number of vehicles

        for (int i = 1; i <= carlot1; i++) {
            String name = "Vehicle" + i;
            double randomDistance = ThreadLocalRandom.current().nextDouble(100, 500);
            double randoml_FuelUsed = ThreadLocalRandom.current().nextDouble(5, 15);
            double randomg_FuelUsed = ThreadLocalRandom.current().nextDouble(5, 15) * 3.7;

            if (i % 2 == 0) { // Use i % 2 to alternate between Car and Motorcycle
                double randomNumberHeatedSeats = ThreadLocalRandom.current().nextDouble(2, 5);
                Car car = new Car(name, randomDistance, randoml_FuelUsed, randomg_FuelUsed, randomNumberHeatedSeats);
                ParkingLot1.add(car);
            } else {
                double randomNumberOfSafetyGear = ThreadLocalRandom.current().nextDouble(1, 4);
                double randomEngineSize = ThreadLocalRandom.current().nextDouble(500, 1500);
                Motorcycle motorcycle = new Motorcycle(name, randomDistance, randoml_FuelUsed, randomg_FuelUsed, randomNumberOfSafetyGear, randomEngineSize);
                ParkingLot1.add(motorcycle);
            }
        }
        int carlot2 = 3;
        for (int i = 1; i <= carlot2; i++) {
            String name = "Vehicle" + i;
            double randomDistance = ThreadLocalRandom.current().nextDouble(100, 500);
            double randoml_FuelUsed = ThreadLocalRandom.current().nextDouble(5, 15);
            double randomg_FuelUsed = randoml_FuelUsed * 3.7;

            if (i % 2 == 0) { // Use i % 2 to alternate between Car and Motorcycle
                double randomNumberHeatedSeats = ThreadLocalRandom.current().nextDouble(2, 5);
                Car car = new Car(name, randomDistance, randoml_FuelUsed, randomg_FuelUsed, randomNumberHeatedSeats);
                ParkingLot2.add(car);
            } else {
                double randomNumberOfSafetyGear = ThreadLocalRandom.current().nextDouble(1, 4);
                double randomEngineSize = ThreadLocalRandom.current().nextDouble(500, 1500);
                Motorcycle motorcycle = new Motorcycle(name, randomDistance, randoml_FuelUsed, randomg_FuelUsed, randomNumberOfSafetyGear, randomEngineSize);
                ParkingLot2.add(motorcycle);
            }
        }
        int carlot3 = 3;
        for (int i = 1; i <= carlot3; i++) {
            String name = "Vehicle" + i;
            double randomDistance = ThreadLocalRandom.current().nextDouble(100, 500);
            double randoml_FuelUsed = ThreadLocalRandom.current().nextDouble(5, 15);
            double randomg_FuelUsed = randoml_FuelUsed * 3.7;

            if (i % 2 == 0) { // Use i % 2 to alternate between Car and Motorcycle
                double randomNumberHeatedSeats = ThreadLocalRandom.current().nextDouble(2, 5);
                Car car = new Car(name, randomDistance, randoml_FuelUsed, randomg_FuelUsed, randomNumberHeatedSeats);
                ParkingLot3.add(car);
            } else {
                double randomNumberOfSafetyGear = ThreadLocalRandom.current().nextDouble(1, 4);
                double randomEngineSize = ThreadLocalRandom.current().nextDouble(500, 1500);
                Motorcycle motorcycle = new Motorcycle(name, randomDistance, randoml_FuelUsed, randomg_FuelUsed, randomNumberOfSafetyGear, randomEngineSize);
                ParkingLot3.add(motorcycle);
            }
        }

        System.out.println("Parking Lot 1:");
        printInitialParkingLot(ParkingLot1);

        System.out.println("Parking Lot 2:");
        printInitialParkingLot(ParkingLot2);

        System.out.println("Parking Lot 3:");
        printInitialParkingLot(ParkingLot3);
        
        double totalEfficiency1 = calculateSumEfficiency(ParkingLot1);
        double totalEfficiency2 = calculateSumEfficiency(ParkingLot2);
        double totalEfficiency3 = calculateSumEfficiency(ParkingLot3);

        List<ParkingLotEfficiency> parkingLots = new ArrayList<>();
        parkingLots.add(new ParkingLotEfficiency("Parking Lot 1", totalEfficiency1));
        parkingLots.add(new ParkingLotEfficiency("Parking Lot 2", totalEfficiency2));
        parkingLots.add(new ParkingLotEfficiency("Parking Lot 3", totalEfficiency3));

        Collections.sort(parkingLots, Comparator.reverseOrder());

        System.out.println("\nParking Lots Sorted by Total Efficiency:");
        for (ParkingLotEfficiency parkingLot : parkingLots) {
            System.out.println(parkingLot.getName() + " Total Efficiency: " + parkingLot.getEfficiency());}

    }

    private static double calculateSumEfficiency(List<Vehicle> collection) {
        return collection.stream().mapToDouble(Vehicle::efficiency).sum();
    }

    private static void printInitialParkingLot(List<Vehicle> parkingLot) {
        for (Vehicle vehicle : parkingLot) {
            if (vehicle instanceof Car) {
                System.out.println("Car: " + vehicle.getName() + ", Efficiency: " + vehicle.efficiency());
            } else if (vehicle instanceof Motorcycle) {
                System.out.println("Motorcycle: " + vehicle.getName() + ", Efficiency: " + vehicle.efficiency());
            }
        }
    }
    public static void printIndividualMethods() {
    	
    }
    static class ParkingLotEfficiency implements Comparable<ParkingLotEfficiency> {
        private final String name;
        private final double efficiency;

        public ParkingLotEfficiency(String name, double efficiency) {
            this.name = name;
            this.efficiency = efficiency;
        }

        public String getName() {
            return name;
        }

        public double getEfficiency() {
            return efficiency;
        }

        @Override
        public int compareTo(ParkingLotEfficiency o) {
            return Double.compare(this.efficiency, o.efficiency);
        }
    }
}
