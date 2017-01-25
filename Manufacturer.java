//Becki Yukman
//rebecca.yukman@gmail.com

public class Manufacturer {
	String name;
	String address;
	String telephone;
	
	//general constructor
	Manufacturer(String name1, String address1, String telephone1){
		name = name1;
		address = address1;
		telephone = telephone1;
	}
	
	//no argument constructor
	Manufacturer(){
		name = "";
		address = "";
		telephone = "";
	}
	
	//overriding the print function be declaring our own print specs
	public String toString(){
		return name+"\n"+address+", "+telephone;
	}
}
	
