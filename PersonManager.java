package homework1;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PersonManager {
	static PersonDataManager pdm;
	
	public static void main (String s[]) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Starting...");
		System.out.println("Menu:");
		String initialStatement = "(I) - Import from File \n(A) - Add Person \n(R) - Remove Person \n(G) - Get Information on Person \n" +
				"(P) - Print Table \n(S) - Save to File\n(Q) - Quit";
		
		boolean keepGoing = true;
		
		while(keepGoing) {
			System.out.println(initialStatement);
			System.out.print("Print select an option: ");
			String option = input.next();
			
			switch(option) {
			case "I": 
				try {
				System.out.print("Enter a location: ");
				String location = input.next();
				pdm = PersonDataManager.buildFromFile(location);
				System.out.println("Loading...");
				System.out.println("This method's Big(O) Notation was O(n^2) ");
				pdm.printTable();
			} catch (FileNotFoundException ex) {
				System.out.println("\n" + ex.getMessage());
				//keepGoing = true;
			}
				
				//keepGoing = true;
				break;
			case "A":
				try {
					System.out.print("Please enter the name of the person: ");
					String name = input.next();
					System.out.print("Please enter the age: ");
					int age = input.nextInt();
					System.out.print("Please enter the gender (M or F): ");
					String gender = input.next();
					System.out.print("Please enter the height (inches): ");
					double height = input.nextDouble();
					System.out.print("Please enter the weight (pounds): ");
					double weight = input.nextDouble();
					try {
						pdm.addPerson(new Person(name, gender, age, height, weight));
						System.out.println(name + " has been added to the list");
						System.out.println("This method's Big(O) Notation was O(n^2) ");
					} catch (PersonAlreadyExistsException ex) {
						System.out.println(ex.getMessage());
					} catch(NullPointerException ex2) {
						System.out.println("An error has occurred. Try again\n");
					}
				} catch (InputMismatchException ex) {
					System.out.println("The input you entered is incorrect. Try again\n");
					//keepGoing = true;
				} catch(IllegalArgumentException ex) {
					System.out.println("The input you entered is incorrect. Try again\n");
					//keepGoing = true;
				}
				try {
					pdm.sortList();
					pdm.printTable();
				} catch (NullPointerException ex) {
					System.out.println("An error has occurred. Try again\n");
				}
				
				//keepGoing = true;
				break;
			case "R":
				try {
					System.out.print("Please enter the name of the person: ");
					String nameR = input.next();
					System.out.println(nameR);
					nameR.replaceAll("\n", "");
					try {
						pdm.removePerson(nameR);
						System.out.println(nameR + " has been removed");
						System.out.println("This method's Big(O) Notation was O(n^2) ");
						pdm.printTable();
					} catch (PersonDoesNotExistException ex) {
						System.out.println(ex.getMessage());
					}
				} catch (NullPointerException ex) {
					System.out.println("You cannot remove a person if there aren't any people!\n");
				}
				
				//keepGoing = true;
				break;
			case "G":
				try {
					System.out.print("Please enter name of person: ");
					String nameG = input.next();
					try {
						pdm.getPerson(nameG);
						System.out.println("This method's Big(O) Notation was O(n) ");
					} catch(PersonDoesNotExistException ex) {
						System.out.println(ex.getMessage());
					}
				} catch (NullPointerException e) {
					System.out.println("You cannot get info of people without people!\n");
				}
				
				//keepGoing = true;
				break;
			case "P":
				try {
					pdm.printTable();
					System.out.println("This method's Big(O) Notation was O(n) ");
				} catch (NullPointerException ex) {
					System.out.println("You did not import the list. Import list to have people!\n");
					//keepGoing = true;
				}
				
				
				break;
			case "S":
				System.out.print("Please select a name for the file: ");
				String newFileName = input.next();
				try {
					PrintWriter writer = new PrintWriter(new File(newFileName));
					StringBuilder sb = new StringBuilder();
					Person[] list = pdm.getPeople();
					sb.append("Name");
					sb.append(',');
					sb.append("Sex");
					sb.append(',');
					sb.append("Age");
					sb.append(',');
					sb.append("Height(in)");
					sb.append(',');
					sb.append("Weight(lbs)");
					for(int i=0; i<pdm.getSize();i++) {
						sb.append('\n');
						sb.append(list[i].getName());
						sb.append(',');
						sb.append(list[i].getGender());
						sb.append(',');
						sb.append(list[i].getAge());
						sb.append(',');
						sb.append(list[i].getHeight());
						sb.append(',');
						sb.append(list[i].getWeight());
						sb.append(',');
					}
					writer.write(sb.toString());
					writer.close();
					System.out.println("A file named " + newFileName + " has been created");
					System.out.println("This method's Big(O) Notation was O(n) ");
				} catch (FileNotFoundException ex) {
					System.out.println("Cannot create the file\n");
				}
				//keepGoing = true;
				break;
			case "Q":
				System.out.println("Sorry to see you go!");
				input.close();
				System.exit(0);
			default:
				System.out.print("invalid argument\n");
			}
			
		}
		
	}
}
