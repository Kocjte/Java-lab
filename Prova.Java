package Prova;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

//superclass vehicle

abstract class Vehicle {
    private String name;
    public String Id;
    public double distance;
    public double l_fuelUsed;
    public double g_fuelUsed;
    

    public Vehicle(String name, double distance, double l_fuelUsed,double g_fuelUsed) {
        this.name = name;
        this.Id = Id;
        this.distance = distance;
        this.l_fuelUsed = l_fuelUsed;
        this.g_fuelUsed = g_fuelUsed;
    }

    public String getName() {
        return name;
    }
    public String getId() {
    	return Id;
    }
    
    public abstract double efficiency();
}



//subclass car

class Car extends Vehicle {
    private double numberHeatedSeats;

    public Car(String name,String Id, double distance, double l_fuelUsed, double g_fuelUsed, double numberHeatedSeats) {
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


//subclass motorcycle

class Motorcycle extends Vehicle {
    private double numberOfSafetyGear;
 

    public Motorcycle(String name,String Id, double distance, double l_fuelUsed, double g_fuelUsed, double numberOfSafetyGear) {
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


//Class for the vehicle collections 

public class VehicleCollectionExample {
   
	public static void main(String[] args) {
    
		List<Vehicle> ParkingLot1 = new ArrayList<>();
        List<Vehicle> ParkingLot2 = new ArrayList<>();
        List<Vehicle> ParkingLot3 = new ArrayList<>();

        int carlot1 = 5; // Total number of vehicles

        for (int i = 1; i <= carlot1; i++) {
            String name = "Vehicle" + "_"+i;
            String Id = "0" + i;
            double randomDistance = ThreadLocalRandom.current().nextDouble(100, 500);
            double randoml_FuelUsed = ThreadLocalRandom.current().nextDouble(5, 15);
            double randomg_FuelUsed = ThreadLocalRandom.current().nextDouble(5, 15) * 3.7;

            if (i % 2 == 0) { // Use i % 2 to alternate between Car and Motorcycle
                double randomNumberHeatedSeats = ThreadLocalRandom.current().nextDouble(2, 7);
                Car car = new Car(name, Id, randomDistance, randoml_FuelUsed, randomg_FuelUsed, randomNumberHeatedSeats);
                ParkingLot1.add(car);
            } else {
                double randomNumberOfSafetyGear = ThreadLocalRandom.current().nextDouble(1, 5);
                Motorcycle motorcycle = new Motorcycle(name, Id,randomDistance, randoml_FuelUsed, randomg_FuelUsed, randomNumberOfSafetyGear);
                ParkingLot1.add(motorcycle);
            }
        }
        int carlot2 = 4;
        for (int i = 1; i <= carlot2; i++) {
            String name = "Vehicle" +"_"+ i;
            String Id = "1" + i;
            double randomDistance = ThreadLocalRandom.current().nextDouble(100, 500);
            double randoml_FuelUsed = ThreadLocalRandom.current().nextDouble(5, 15);
            double randomg_FuelUsed = randoml_FuelUsed * 3.7;

            if (i % 2 == 0) { // Use i % 2 to alternate between Car and Motorcycle
                double randomNumberHeatedSeats = ThreadLocalRandom.current().nextDouble(2, 5);
                Car car = new Car(name,Id, randomDistance, randoml_FuelUsed, randomg_FuelUsed, randomNumberHeatedSeats);
                ParkingLot2.add(car);
            } else {
                double randomNumberOfSafetyGear = ThreadLocalRandom.current().nextDouble(1, 4);
                Motorcycle motorcycle = new Motorcycle(name,Id, randomDistance, randoml_FuelUsed, randomg_FuelUsed, randomNumberOfSafetyGear);
                ParkingLot2.add(motorcycle);
            }
        }
        int carlot3 = 8;
        for (int i = 1; i <= carlot3; i++) {
            String name = "Vehicle" +"_"+ i;
            String Id = "2" + i;
            double randomDistance = ThreadLocalRandom.current().nextDouble(100, 500);
            double randoml_FuelUsed = ThreadLocalRandom.current().nextDouble(5, 15);
            double randomg_FuelUsed = randoml_FuelUsed * 3.7;

            if (i % 2 == 0) { // Use i % 2 to alternate between Car and Motorcycle
                double randomNumberHeatedSeats = ThreadLocalRandom.current().nextDouble(2, 5);
                Car car = new Car(name,Id, randomDistance, randoml_FuelUsed, randomg_FuelUsed, randomNumberHeatedSeats);
                ParkingLot3.add(car);
            } else {
                double randomNumberOfSafetyGear = ThreadLocalRandom.current().nextDouble(1, 4);
                Motorcycle motorcycle = new Motorcycle(name,Id, randomDistance, randoml_FuelUsed, randomg_FuelUsed, randomNumberOfSafetyGear);
                ParkingLot3.add(motorcycle);
            }
        }

        
        System.out.println("Parking Lot 1:");
        printInitialParkingLot(ParkingLot1);

        System.out.println("------------------------------------------");
        
        System.out.println("Parking Lot 2:");
        printInitialParkingLot(ParkingLot2);

        System.out.println("------------------------------------------");
        
        System.out.println("Parking Lot 3:");
        printInitialParkingLot(ParkingLot3);
        
        
       
        //////////////////////////////////////
        
        
       //creating the funciton to sum the eff in parking lots
        
        double totalEfficiency1 = calculateSumEfficiency(ParkingLot1);
        double totalEfficiency2 = calculateSumEfficiency(ParkingLot2);
        double totalEfficiency3 = calculateSumEfficiency(ParkingLot3);
        
        //same for individual methods
        double totalcostHseats1 = calculateSeatCost(ParkingLot1);
        double totalcostHseats2 = calculateSeatCost(ParkingLot2);
        double totalcostHseats3 = calculateSeatCost(ParkingLot3);
        
        double totalgearC1 = calculateGearCost(ParkingLot1);
        double totalgearC2 = calculateGearCost(ParkingLot2);
        double totalgearC3 = calculateGearCost(ParkingLot3);
        

        List<ParkingLotEfficiency> parkingLots = new ArrayList<>();//creating array to store things called parkingLots
        
        parkingLots.add(new ParkingLotEfficiency("Parking Lot 1", totalEfficiency1));
        parkingLots.add(new ParkingLotEfficiency("Parking Lot 2", totalEfficiency2));
        parkingLots.add(new ParkingLotEfficiency("Parking Lot 3", totalEfficiency3));

        Collections.sort(parkingLots, Comparator.reverseOrder());//we sort it

        System.out.println("\nParking Lots Sorted by Total Efficiency:");//print
        
        
        for (ParkingLotEfficiency parkingLot : parkingLots) {
            System.out.println(parkingLot.getName() + " Total Efficiency: " + parkingLot.getEfficiency());}
        
        
        //same for the individual methods
        List<ParkingLotIndividualCar> IparkingLots = new ArrayList<>();//creating array to store things called parkingLots
        
        IparkingLots.add(new ParkingLotIndividualCar("Parking Lot 1", totalcostHseats1));
        IparkingLots.add(new ParkingLotIndividualCar("Parking Lot 2", totalcostHseats2));
        IparkingLots.add(new ParkingLotIndividualCar("Parking Lot 3", totalcostHseats3));

        List<ParkingLotIndividualMoto> IaparkingLots = new ArrayList<>();
        
        IaparkingLots.add(new ParkingLotIndividualMoto("Parking Lot 1", totalgearC1));
        IaparkingLots.add(new ParkingLotIndividualMoto("Parking Lot 2", totalgearC2));
        IaparkingLots.add(new ParkingLotIndividualMoto("Parking Lot 3", totalgearC3));
        
        Collections.sort(IparkingLots, Comparator.reverseOrder());//we sort it
        Collections.sort(IaparkingLots, Comparator.reverseOrder());//we sort it for moto too

        System.out.println("\nParking Lots Sorted by Total Individual methods:");//print
        System.out.println("-----------------------------------------------------");//print
        for (ParkingLotIndividualCar parkingLot : IparkingLots) {
            System.out.println(parkingLot.getName() + " Total electircal consupmtion for heated seats: " + parkingLot.gettotalSeatCost());}

	
        for (ParkingLotIndividualMoto parkingLot : IaparkingLots) {
        	System.out.println(parkingLot.getName() + " Total gear cost: " + parkingLot.gettotalGearCost());}
	
	
	}
	
	
	
	
	

	///////////////////////
	
	
	//function to find the sum of the efficencys
    private static double calculateSumEfficiency(List<Vehicle> collection) {
        return collection.stream().mapToDouble(Vehicle::efficiency).sum();
    }
    
    
    private static double calculateSeatCost(List<Vehicle> collection) {
        return collection.stream()
            .filter(vehicle -> vehicle instanceof Car) // Filter to only include Car objects
            .mapToDouble(vehicle -> ((Car) vehicle).electricConOfHeatedSeats()) // Cast and call SeatCost
            .sum();
    }
    private static double calculateGearCost(List<Vehicle> collection) {
        return collection.stream()
            .filter(vehicle -> vehicle instanceof Motorcycle) // Filter to only include Car objects
            .mapToDouble(vehicle -> ((Motorcycle) vehicle).safetyGearCost()) // Cast and call SeatCost
            .sum();
    }

    ////////////////////////////////////////////////////////////////
    

    //function to print initial parking lot
    private static void printInitialParkingLot(List<Vehicle> parkingLot) {
        for (Vehicle vehicle : parkingLot) {
            if (vehicle instanceof Car) {
                Car car = (Car) vehicle; // Cast the Vehicle object to Car
                System.out.println("- Car: " + car.getName() + ", Efficiency: " + car.efficiency() + ", Heated Seats Cost: " + car.electricConOfHeatedSeats());
            } else if (vehicle instanceof Motorcycle) {
            	Motorcycle motorcycle = (Motorcycle) vehicle;
                System.out.println("- Motorcycle: " + vehicle.getName() + ", Efficiency: " + vehicle.efficiency()+ ",Safety gear cost"+ motorcycle.safetyGearCost());
            }
        }
    }
    
    
    
    
    ////////////////////////////////////////////////////////////////
    
    
    
    
    
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

    
 //identical but separated because otherwise I didnt know how to make the sorting work 
    static class ParkingLotIndividualCar implements Comparable<ParkingLotIndividualCar> {
        
    	private final String name;
        private final double totalSeatCost;

        public ParkingLotIndividualCar(String name, double totalSeatCost) {
            this.name = name;
            this.totalSeatCost = totalSeatCost;
        }

        public String getName() {
            return name;
        }
        
        public double gettotalSeatCost() {
            return totalSeatCost;
        }

        

        @Override
        public int compareTo(ParkingLotIndividualCar o) {
            return Double.compare(this.totalSeatCost, o.totalSeatCost);
        }

    }
    static class ParkingLotIndividualMoto implements Comparable<ParkingLotIndividualMoto> {
        
    	private final String name;
        private final double totalGearCost;

        public ParkingLotIndividualMoto(String name,double totalGearCost) {
            this.name = name;
            this.totalGearCost = totalGearCost;
        }

        public String getName() {
            return name;
        }
        
        public double gettotalGearCost() {
            return totalGearCost;
        }

        @Override
        public int compareTo(ParkingLotIndividualMoto o) {
            return Double.compare(this.totalGearCost, o.totalGearCost);
        }
    }
}
