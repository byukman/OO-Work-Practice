//Becki Yukman
//rebecca.yukman@gmail.com

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vehicle{
	int vin;
	String make;
	String model;
	int year;
	String color;
	int weight;
	double msrp;
	int mileage; 
	Manufacturer manufacturer;
	int daysSinceOil;
	int milesSinceOil;
	
	//general case constructor (assumption of fresh oil change implicit)
	Vehicle(int vin1, String make1, String model1, int year1, String color1, int weight1, double msrp1, int mileage1, Manufacturer manufacturer1) {
		vin = vin1;
		make = make1;
		model = model1;
		year = year1;
		color = color1;
		weight = weight1;
		msrp = msrp1;
		mileage = mileage1;
		manufacturer = manufacturer1;
		daysSinceOil = 0;
		milesSinceOil = 0;
		
	}
	//no argument constructor (assumption of fresh oil change implicit)
	Vehicle() {
		vin = 0;
		make = "";
		model = "";
		year = 0;
		color = "";
		weight = 0;
		msrp = 0;
		mileage = 0;
		manufacturer = null;
		daysSinceOil = 0;
		milesSinceOil = 0;
	}
	
	//adds a vehicle to the inventory
	void addVehicle(Vehicle[] inventory){
		for (int i = 0; i<inventory.length; i++){
			if (inventory[i] == null) {
				inventory[i] = this;
				break;
			}
		}
	}
	
	//removes a vehicle from the inventory
	void removeVehicle(Vehicle[] inventory){
		for (int i = 0; i<inventory.length; i++){
			if (inventory[i] == this) {
				inventory[i] = null;
				break;
			}
		}
	}
	
	//overriding the print function be declaring our own print specs
	public String toString() {
	        String p =  "VIN: "+vin+" "+year+" "+make; /*+" "+model+"\n"+color+", "+weight+"lbs"+"\n"+mileage+" miles driven \n"+"ONLY "+msrp+"\n"+"Contact: "+manufacturer+"\n";*/
	        if(make == "BMW") {
		 		p = p+"© Copyright BMW AG, Munich, Germany\n";
		 	}
		 	return p;
	
	}
	 
	 //cycles through the given inventory and prints results
	 static void printAll(Vehicle[] inventory){
		 for (int i = 0; i<inventory.length; i++){
				if (inventory[i] != null){
					System.out.println(inventory[i]);
				}
			}
	 }
	 
	 /* from: https://www.cs.cmu.edu/~adamchik/15-121/lectures/Sorting%20Algorithms/sorting.html
	  * void selectionSort(int[] ar){
		   for (int i = 0; i ‹ ar.length-1; i++) {
		      int min = i;
		      for (int j = i+1; j ‹ ar.length; j++)
		            if (ar[j] ‹ ar[min]) min = j;
		      int temp = ar[i];
		      ar[i] = ar[min];
		      ar[min] = temp;
			} 
		}
		*/
	 
	//sorts given Vehicle array by VIN
	static Vehicle[] sortVIN(Vehicle[] inventory){	
		for(int i = 0; i< inventory.length-1; i++) {
			if (inventory[i] != null) {
				int min = inventory[i].vin;
				int minInt = i;
				for (int j = i+1; j<inventory.length; j++) {
					if (inventory[j] != null) {	
						if(inventory[j].vin < min) {
							min = inventory[j].vin;
							minInt = j;
						}
						Vehicle temp = inventory[i];
						inventory[i] = inventory[minInt];
						inventory[minInt] = temp;
					}
				}
			}
		}
		printAll(inventory);
		return inventory;
	}

	//TODO: actually sort given Vehicle array by Make and then Model
	static Vehicle[] sortMake(Vehicle[] inventory){
		List<String> makes = new ArrayList();
		for(int i = 0; i<inventory.length; i++) {
			if (inventory[i] != null) {
				makes.add(inventory[i].make);
			}
		}
		Collections.sort(makes, String.CASE_INSENSITIVE_ORDER);
		Vehicle[] sorted = new Vehicle[10];
		System.out.println(makes);
		return sorted;
	}
	
	//sorts given Vehicle array by Year
	static Vehicle[] sortYear(Vehicle[] inventory){	
		for(int i = 0; i< inventory.length-1; i++) {
			if (inventory[i] != null) {
				int min = inventory[i].year;
				int minInt = i;
				for (int j = i+1; j<inventory.length; j++) {
					if (inventory[j] != null) {	
						if(inventory[j].year < min) {
							min = inventory[j].year;
							minInt = j;
						}
						Vehicle temp = inventory[i];
						inventory[i] = inventory[minInt];
						inventory[minInt] = temp;
					}
				}
			}
		}
		printAll(inventory);			
		return inventory;
	}
	
	//returns a list of vehicles from the given inventory and from the given year 
	static Vehicle[] findByYear(Vehicle[] inventory, int year) {
		Vehicle[] list = new Vehicle[100];
		for (int i = 0; i<inventory.length; i++){
			if (inventory[i] != null && inventory[i].year == year){
				inventory[i].addVehicle(list);
			}
		}
		return list;
	}
	
	//returns a list of vehicles from the given inventory and from the given year 
	static Vehicle[] findByMake(Vehicle[] inventory, String make) {
		Vehicle[] list = new Vehicle[10];
		for (int i = 0; i<inventory.length; i++){
			if (inventory[i] != null && inventory[i].make.equals(make)){
				inventory[i].addVehicle(list);
				}
		}
		return list;
	}
		
	//instance specific mileage assignment
	void addToMileage(int sum){
		this.mileage = this.mileage+sum;
	}
	
	//iterative looping adding mileage assignment (static)
	static void addToMileage(Vehicle[] inventory, int sum){
		for(int i = 0; i<inventory.length; i++){
			if (inventory[i] != null){
				inventory[i].mileage = inventory[i].mileage+sum;
				inventory[i].milesSinceOil = inventory[i].milesSinceOil+sum;
			}
		}
	}
	
	//oil change resets daysSinceOil and milesSinceOil
	void oilChange(){
		this.daysSinceOil = 0;
		this.milesSinceOil = 0;
	}
	
	//checks if the instance its called on needs an oil change
	boolean needsOil(){
		if (make == "Subaru"){
			if (this.daysSinceOil == 180) {
				return true;
			}
			if (this.milesSinceOil == 7000) {
				return true;
			}
			return false;
		}
		else if (make == "Volkswagen"){
			if (this.daysSinceOil == 210) {
				return true;
			}
			if (this.milesSinceOil == 8000) {
				return true;
			}
			return false;
		}
		else if (make == "Tesla"){
			return false;
		}
		else if (this.daysSinceOil == 90){
			return true;
		}
		else if (this.milesSinceOil == 3000){
			return true;
		}
		return false;
	}
	
	//iterates through the given list and returns average msrp
	static double findAvgMSRP(Vehicle[] inventory){
		int sum = 0;
		int numOfVehicles = 0;
		for (int i = 0; i<inventory.length; i++){
			if (inventory[i] != null) {
				sum += inventory[i].msrp;
				numOfVehicles++;
			}
		}
		return ((double)sum)/((double)numOfVehicles);
	}
	
	//iterates through the given list and returns the minimum msrp
	static double findMinMSRP(Vehicle[] inventory){
		double min = 1000000.00;
		for (int i = 0; i<inventory.length; i++){
			if (inventory[i] != null) {
				if (inventory[i].msrp < min){
					min = inventory[i].msrp;
				}
			}
		}
		return min;
	}

	//iterates through the list and returns the maximum msrp
	static double findMaxMSRP(Vehicle[] inventory){
		double max = 0.0;
		for (int i = 0; i<inventory.length; i++){
			if (inventory[i] != null) {
				if (inventory[i].msrp > max){
					max = inventory[i].msrp;
				}
			}
		}
		return max;
	}
	
	//iterates through the given list and returns average mileage
	static int findAvgMileage(Vehicle[] inventory){
		int sum = 0;
		int numOfVehicles = 0;
		for (int i = 0; i<inventory.length; i++){
			if (inventory[i] != null) {
				sum += inventory[i].mileage;
				numOfVehicles++;
			}
		}
		return sum/numOfVehicles;
	}
	
	//iterates through the given list and returns the minimum mileage
	static int findMinMileage(Vehicle[] inventory){
		int min = 1000000;
		for (int i = 0; i<inventory.length; i++){
			if (inventory[i] != null) {
				if (inventory[i].mileage < min){
					min = inventory[i].mileage;
				}
			}
		}
		return min;
	}
		
	//iterates through the list and returns the maximum mileage
	static int findMaxMileage(Vehicle[] inventory){
		int max = 0;
		for (int i = 0; i<inventory.length; i++){
			if (inventory[i] != null) {
				if (inventory[i].mileage > max){
					max = inventory[i].mileage;
				}
			}
		}
		return max;
	}
		
	static int numNeedingOil(Vehicle[] inventory) {
		int sum = 0;
		for (int i = 0; i<inventory.length; i++){
			if (inventory[i] != null && inventory[i].needsOil()){
				sum++;
			}
		}
		return sum;
	}
	
	public static void main(String[] args){
		Vehicle[] inventory = new Vehicle[10];
		Vehicle test = new Vehicle(2768,"Toyota","RAV4",2000,"white",2000,9999.99,120000, new Manufacturer("Joe Car","12 Main Street","555-555-5555"));
		Vehicle test1 = new Vehicle(2996,"BMW","Z3",2016,"gold",2000,44999.99,2000, new Manufacturer("Jane Truck","47 Pomona Street","555-444-5555"));
		Vehicle test2 = new Vehicle(2232,"Ford","Taurus",2017,"blue",2000,19999.99,2000, new Manufacturer("Gus Gas","333 Triple Street","555-333-5555"));
		Vehicle test3 = new Vehicle(2554,"Volkswagen","Jetta",2010,"green",2000,19999.99,140000, new Manufacturer("Annie Sullivan","27 Central Avenue","555-222-5555"));
		Vehicle test4 = new Vehicle(2097,"Tesla","S",2017,"black",2000,69999.99,130000, new Manufacturer("Mary Smith","86 Cliff Blvd.","555-111-5555"));
		test.addVehicle(inventory);
		test1.addVehicle(inventory);
		test2.addVehicle(inventory);
		test3.addVehicle(inventory);
		test4.addVehicle(inventory);
		printAll(inventory);
		test1.removeVehicle(inventory);
		System.out.println("Removing "+test1.make+"...\n");
		printAll(inventory);
		System.out.println("Year 2000:\n");
		Vehicle[] twoThousand = findByYear(inventory, 2000);
		printAll(twoThousand);
		System.out.println("Made By Ford:\n");
		Vehicle[] fords = findByMake(inventory, "Ford");
		printAll(fords);
		System.out.println("Increasing milegage by 3000 miles...\n");
		addToMileage(inventory,3000);
		printAll(inventory);
		System.out.println("Does "+inventory[0].make+" need an oil change?");
		System.out.println(inventory[0].needsOil());
		System.out.println("Changing oil...");
		inventory[0].oilChange();
		System.out.println("Does "+inventory[0].make+" still need an oil change?");
		System.out.println(inventory[0].needsOil());
		System.out.println("Does "+inventory[3].make+" need an oil change?");
		System.out.println(inventory[3].needsOil());
		System.out.println("Average MSRP: "+findAvgMSRP(inventory));
		System.out.println("Min MSRP: "+findMinMSRP(inventory));
		System.out.println("Max MSRP: "+findMaxMSRP(inventory));
		System.out.println("Average Mileage: "+findAvgMileage(inventory));
		System.out.println("Min Mileage: "+findMinMileage(inventory)+"\n note: 3000 miles added via earlier method");
		System.out.println("Max Mileage: "+findMaxMileage(inventory));
		System.out.println("Number of cars in need of oil change: "+numNeedingOil(inventory)+"\n note: Toyota oil change via earlier method");
		System.out.println("\nSorted by VIN:");
		sortVIN(inventory);
		System.out.println("\nSorted by Year:");
		sortYear(inventory);
		System.out.println("\nSorted by Make:");
		sortMake(inventory);
	} 
}