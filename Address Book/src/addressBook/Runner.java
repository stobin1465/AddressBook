package addressBook;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Runner {
	static Scanner scanner = new Scanner(System.in);
	static ArrayList<Address> book = new ArrayList<Address>();

	public static void main(String[] args) {
		boolean flag = false;
		try {
			System.out.println("Reading from database");
			FileInputStream fileIn = new FileInputStream(
					"C:\\Users\\stobi\\OneDrive\\Desktop\\TTS projects\\HW\\Final project\\Address Book\\abook1.bin");
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			

			while (flag == false) {
				book = (ArrayList<Address>) objectIn.readObject();
			}
			objectIn.close();
		} catch (EOFException e) {
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		int menuOptionSelected = printMenuOptions();

		while (menuOptionSelected != 6) {

			switch (menuOptionSelected) {
			case 1:
				add();
				break;
			case 2:
				remove();
				break;
			case 3:
				search();
				break;
			case 4:
				print();
				break;
			case 5:
				delete();
				break;
			case 6:
				quit();
				break;
			default:
				break;
			}
			menuOptionSelected = printMenuOptions();

		}

	}

	public static int printMenuOptions() {
		System.out.println("1. Add an entry");
		System.out.println("2. Remove an entry");
		System.out.println("3. Search for a specific entry");
		System.out.println("4. Print Address");
		System.out.println("5. Delete Book");
		System.out.println("6. Quit");

		System.out.print("Please select what you'd like to do with the database: ");
		int menuOption = scanner.nextInt();
		scanner.nextLine();

		return menuOption;
	}

	public static void add() {
		System.out.print("First Name: ");
		String firstName = scanner.nextLine();
		System.out.println("\n");

		System.out.print("Last Name: ");
		String lastName = scanner.nextLine();
		System.out.println("\n");

		System.out.print("Email Address: ");
		String email = scanner.nextLine();
		System.out.println("\n");

		System.out.print("Phone Number: ");
		String phone = scanner.nextLine();
		System.out.println("\n");
		
		Address newAddress = new Address(firstName, lastName, phone, email);
		book.add(newAddress);
		remakeDB();
		
		System.out.println("Added a new entry!");

	}

	public static void remove() {
		System.out.print("Enter an entry's email to remove: ");
		String email = scanner.nextLine();
		boolean removeFlag = false;
		for (int i = 0; i < book.size(); i++) {
			if (book.get(i).getEmail().equals(email)) {
				System.out.println("Deleted the following entry: " + book.get(i).getEmail());
				removeFlag = true;
				book.remove(i);
				remakeDB();
				break;
			}
		}
		if (removeFlag == false) {
			System.out.println("Search entry not found");
		}
	}

	public static void search() {
		
		//
		System.out.println("1. First Name");
		System.out.println("2. Last Name");
		System.out.println("3. Phone Number");
		System.out.println("4. Email Address");
		System.out.print("Choose a search type: ");
		int menuOption = scanner.nextInt();
		scanner.nextLine();
		System.out.println("\n");

		
		System.out.print("Enter your search: ");
		String searchValue = scanner.nextLine();

		Address searchMatch = new Address();

		for (int i = 0; i < book.size(); i++) 
		
		{
			if (book.get(i).getFirstName().equals(searchValue) && menuOption == 1) {
				searchMatch = book.get(i);
			}
			if (book.get(i).getLastName().equals(searchValue) && menuOption == 2) {
				searchMatch = book.get(i);
			}
			if (book.get(i).getPhoneNumber().equals(searchValue) && menuOption == 3) {
				searchMatch = book.get(i);
			}
			if (book.get(i).getEmail().equals(searchValue) && menuOption == 4) {
				searchMatch = book.get(i);
			}
		}
		
		printAddress(searchMatch);
	}

	public static void delete() {

		book.clear();
		clearDB();
		System.out.println("Address book cleared");
	}

	public static void print() {
		for (int i = 0; i < book.size(); i++) {
			printAddress(book.get(i));
		}
	}

	public static void quit() {
		scanner.close();
		System.out.println("Goodbye!");
	}

	public static void printAddress(Address address) {

		System.out.println("**********************");
		System.out.println("First Name: " + address.getFirstName());
		System.out.println("Last Name: " + address.getLastName());
		System.out.println("Phone Number: " + address.getPhoneNumber());
		System.out.println("Email: " + address.getEmail());
		System.out.println("**********************");

	}

	public static void clearDB() {
		try {
			FileOutputStream fileOut = new FileOutputStream(
					"C:\\Users\\stobi\\OneDrive\\Desktop\\TTS projects\\HW\\Final project\\Address Book\\abook1.bin");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.close();
		} catch (Exception e) {
			return;
		}
	}

	public static void remakeDB() {
		clearDB();
		try {
			FileOutputStream fileOut = new FileOutputStream(
					"C:\\Users\\stobi\\OneDrive\\Desktop\\TTS projects\\HW\\Final project\\Address Book\\abook1.bin");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(book);
			objectOut.close();
		} catch (Exception e) {
			return;
		}
	}
}
