package homework1;

public class Person {
	
	private int age;
	private double weight, height;
	private String name, gender;
	
	public Person(String name, String gender, int age, double height, double weight) {
		this.age = age;
		this.weight = weight; this.height = height;
		this.name = name; this.gender = gender;
	}
	
	public int getAge() {
		return this.age;
	}
	public void setAge(int newAge) {
		this.age = newAge;
	}
	public double getWeight() {
		return this.weight;
	}
	public void setWeight(int newWeight) {
		this.weight = newWeight;
	}
	public double getHeight() {
		return this.height;
	}
	public void setHeight(int newHeight) {
		this.height = newHeight;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String newName) {
		this.name = newName;
	}
	public String getGender() {
		return this.gender;
	}
	public void setGender(String newGender) {
		this.gender = newGender;
	}
	
	public String toString() {
		if(this.gender.equals("F")) {
			
			return this.name + " is a " + this.age + " year old female who is " + (int)(this.height/12) + 
					" feet and " + (int)(this.height%12) + " inches tall and weighs " + (int)(this.weight) + " lbs.";
		}
		else {
			return this.name + " is a " + this.age + " year old male who is " + (int)(this.height/12) + 
					" feet and " + (int)(this.height%12) + " inches tall and weighs " + (int)(this.weight) + " lbs.";
		}
		
	}
}
