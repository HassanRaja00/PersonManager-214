package homework1;
//Hassan Raja ID: 112249751
//import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.IOException;
import java.util.Scanner;

public class PersonDataManager {
	Person[] people;
	int size;
	
	public PersonDataManager() {
		people = new Person[18];
		size = 0;
	}
	
	public Person[] getPeople() {
		return this.people;
	}
	public int getSize() {
		return this.size;
	}

	
	public static PersonDataManager buildFromFile(String location) throws IllegalArgumentException, FileNotFoundException {
		PersonDataManager dataManager = new PersonDataManager();
		String inputLine;
		Scanner sc = new Scanner(new File(location));
		sc.nextLine();//skipping the first line due to titles
		String line1 = null;
		int i = 0;
		
		while(sc.hasNextLine() ) {
			inputLine = sc.nextLine();
			inputLine = inputLine.replaceAll("\\s", "");
			inputLine = inputLine.replaceAll("\"", "");
			String[] inArray = inputLine.split(",");
			if(inArray.length == 5 && !(inputLine.equals("Name")) && !(inputLine.equals("Sex")) && !(inputLine.equals("Age"))) {
				//String [] split = inArray[2].split(" ");
				Person temp = new Person(inArray[0], inArray[1], Integer.parseInt(inArray[2]), 
						Double.parseDouble(inArray[3]), Double.parseDouble(inArray[4]));
				
				try {
					dataManager.addPerson(temp);
					//dataManager.incrementSize();
				} catch (PersonAlreadyExistsException ex) {
					ex.getMessage();
				}
				i++;
			}
		}
		
		sc.close();
		return dataManager;
	}
	
	public void addPerson(Person newPerson) throws PersonAlreadyExistsException {
		for(int i=0; i <size; i++) {
			if(people[i].getName().equals(newPerson.getName()) && people[i].getAge() == newPerson.getAge() &&
					people[i].getGender().equals(newPerson.getGender()) && people[i].getHeight() == newPerson.getHeight() &&
					people[i].getWeight() == newPerson.getWeight()) {
				throw new PersonAlreadyExistsException("This person already exists!");
			}
		}
		
		if(size == people.length-1) {
			Person[] morePeople = new Person[size*2+1];
			for(int i=0; i<people.length; i++) {
				morePeople[i] = people[i];
			}
			people = morePeople;
		}
		people[size] = newPerson;
		size++;
		
	}
	
	public void removePerson(String name) throws PersonDoesNotExistException{
		boolean removed = false;
		for(int i=0; i<size; i++) {
			if(people[i].getName().equals(name)) {
				people[i] = null;
				removed = true;
				size--;
				for(int j=i; j<size; j++) {
					people[j] = people[j+1];
					}
				}
		}
		if (!removed)
			throw new PersonDoesNotExistException("Person with name " + name + " does not exist!");
	}
	
	public void getPerson(String name) throws PersonDoesNotExistException{
		boolean exists = false;
		for(int i=0; i<size; i++) {
			if(people[i].getName().equals(name)) {
				System.out.println(people[i].toString());
				exists = true;
			}
		}
		if(!exists) 
			throw new PersonDoesNotExistException("Person with name " + name + " does not exist!");
	}
	
	public Person[] sortList() {
		Person temp;
		for(int i=0; i<size; i++) {
			for(int j=0;j < size-1-i; j++) {
				if(people[j+1].getName().compareTo(people[j].getName()) < 0) {
					temp = people[j];
					people[j] = people[j+1];
					people[j+1] = temp;	
				}
			}
		}
		return people;
	}
	
	public void printTable() {
		System.out.println("   Name   |   Age   |   Gender   |   Height   |   Weight   ");
		System.out.println("====================================================================");
		for(int i=0; i<size; i++) {
			System.out.println("   " + people[i].getName() + "   |   " + people[i].getAge() + "   |   " + people[i].getGender() +
					"   |   " + (int)(people[i].getHeight()/12) + " feet and " + (int)(people[i].getHeight()%2) + " inches   |   " + (int)(people[i].getWeight()) + " pounds   ");
		}
	}

}