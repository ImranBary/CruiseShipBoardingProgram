package cruiseShipClassesSolution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static Scanner input = new Scanner(System.in);

	public static final String EMPTY_CABIN = "EMPTY";
	public static final int NUM_OF_CABINS = 12;
	public static Cabin[] ship = new Cabin[NUM_OF_CABINS];
	public static final int ROWS = 6;
	public static final int MAX_PASSENGERS = Cabin.numOfPassengers;//static variable from Cabin

	public static String[][] queue = new String[ROWS][MAX_PASSENGERS];
	public static int queueFront = 0;
	public static int queueEnd = 0;

	/*
	 * Method to initialise the queue array as empty
	 */
	public static void initialiseQueue(String[][] array) {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < MAX_PASSENGERS; j++) {
				array[i][j] = EMPTY_CABIN;
			}
		}
	}

	public static void main(String[] args) throws IOException, ParseException {
		allocateMemory();
		initialiseQueue(queue);
		String choice;
		do {
			menuPrint();
			choice = input.next();
			choice = choice.toUpperCase();
			if (choice.equals("A")) {
				addPassengers();

			} else if (choice.equals("V")) {
				viewCabins();

			} else if (choice.equals("E")) {
				emptyCabins();

			} else if (choice.equals("D")) {
				deletePassenger();

			} else if (choice.equals("F")) {
				findPassengerFromName();

			} else if (choice.equals("S")) {
				writeFile();

			} else if (choice.equals("L")) {
				readFile();

			} else if (choice.equals("O")) {
				sort();

			} else if (choice.equals("Q")) {
				// do nothing

			} else if (choice.equals("T")) {
				expenses();
			} else {
				System.out.println("Please enter a valid input.");

			}

		} while (!choice.equals("Q"));
		System.out.println("You quit the program");

	}

	/**
	 * This method will print: 1) Individual Passengers and their expense 2) The
	 * total expense
	 */
	public static void expenses() {
		for (int i = 0; i < NUM_OF_CABINS; i++) {
			for (int j = 0; j < MAX_PASSENGERS; j++) {
				if (!ship[i].passenger[j].getFristName().equals(EMPTY_CABIN)) {
					System.out.printf("Passenger[%d] at Cabin [%d]: %s_%s Expenses are: £%d \n", (j + 1), i,
							ship[i].passenger[j].getFristName(), ship[i].passenger[j].getSurname(),
							ship[i].passenger[j].getExpenses());
				}
			}
		}
		System.out.println("\n");
		int numOfCustomers = 0;
		for (int i = 0; i < NUM_OF_CABINS; i++) {
			for (int j = 0; j < MAX_PASSENGERS; j++) {
				if (!ship[i].passenger[j].getFristName().equals(EMPTY_CABIN)) {
					numOfCustomers++;
				}
			}
		}
		if (numOfCustomers != 0) {
			System.out.printf("The total expenses for all [%d]Passengers is: £%d", numOfCustomers,
					numOfCustomers * ship[0].passenger[0].getExpenses());
		} else if (numOfCustomers == 0) {
			System.out.println("There are no booked customers.");
		}
	}

	/**
	 * This method takes an array and sorts it alphabetically
	 *
	 */
	public static void sort() {
		String[] shipCopy = copyArray();
		Boolean changeMade = true;
		int num;
		String temp;
		while (changeMade)// loops if changes are made
		{
			changeMade = false;
			for (int i = 0; i < shipCopy.length - 1; i++) {
				num = shipCopy[i].compareTo(shipCopy[i + 1]);
				if (num > 0) {
					temp = shipCopy[i];
					shipCopy[i] = shipCopy[i + 1];
					shipCopy[i + 1] = temp;
					changeMade = true;
				}
			}
		}
		for (String shipCopy1 : shipCopy) {// not print the empty cabins
			if (shipCopy1.equals(String.join("_", EMPTY_CABIN, EMPTY_CABIN))) {
				// do nothing
			} else {
				System.out.println(shipCopy1);
			}
		}
	}

	/**
	 * Make copy of Cabin Array into a normal string array to be used in the sorter
	 *
	 * @return Copied String array
	 */
	public static String[] copyArray() {
		String[] ship2 = new String[NUM_OF_CABINS * MAX_PASSENGERS];// created the array
		// copy contents of ship to ship 2
		int k = 0;
		while (k < (NUM_OF_CABINS * MAX_PASSENGERS)) {
			for (Cabin ship1 : ship) {
				for (int j = 0; j < MAX_PASSENGERS; j++) {
					ship2[k] = String.join("_", ship1.passenger[j].getFristName(), ship1.passenger[j].getSurname()); 
					// joining first and surname with an underscore
					k++;
				}
			}
		}
		return ship2;
	}

	/**
	 * Read file and display it in the terminal
	 *
	 * @throws ParseException
	 */
	public static void readFile() throws ParseException,FileNotFoundException{
		try {
			File inputFile = new File("records.txt");
			Scanner readFile = new Scanner(inputFile);
			while (readFile.hasNextLine()) {
				String data = readFile.nextLine();
				System.out.println(data);
			}
			readFile.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error occured");
			e.printStackTrace();
		}
	}

	/**
	 * Method gets date to be used in the file writer method as header.
	 *
	 * @return the current date as a string
	 * @throws ParseException
	 */
	public static String getDate() throws ParseException {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
		String date_ = formatter.format(date);
		return date_;
	}

	/**
	 * Method to create and write the array elements to file
	 *
	 * @throws IOException
	 * @throws ParseException
	 */
	public static void writeFile() throws IOException, ParseException {
		FileWriter writer = new FileWriter("records.txt");
		writer.write("  " + getDate() + "  \n\n");
		for (int i = 0; i < ship.length; i++) {
			for (int j = 0; j < MAX_PASSENGERS; j++) {
				writer.write("Cabin Number:" + i + "  " + "Passenger Number:" + (j + 1) + "  "
						+ ship[i].passenger[j].getFristName() + "\t" + "");
				writer.write(ship[i].passenger[j].getSurname() + "\t" + "");
				writer.write("\n");
			}

		}
		writer.close();
		System.out.println("Successfully written to file ");
	}

	/**
	 * Method loops through cabin and also passengers looking for a match
	 */
	public static void findPassengerFromName() {
		System.out.println("Please enter the First Name followed by the Surname");
		String firstName = input.next();
		String surName = input.next();
		int cabin = -999;// this is an impossible index number, only changed if we find the customer
		int passNum = -999;
		for (int i = 0; i < ship.length; i++) {
			for (int j = 0; j < MAX_PASSENGERS; j++) {
				if (ship[i].passenger[j].getFristName().equals(firstName.toUpperCase())
						&& ship[i].passenger[j].getSurname().equals(surName.toUpperCase())) {
					cabin = i;
					passNum = j;
				}
			}
		}
		if (cabin != -999 && passNum != -999) {
			System.out.printf("Customer %s %s (Passenger %d ) is at Cabin Number:%d \n\n", firstName.toUpperCase(),
					surName.toUpperCase(), (passNum + 1), cabin);
		} else {
			System.out.println("The customer does not exist in the system.");
		}
	}

	/**
	 * Method to print cabins
	 */
	public static void viewCabins() {
		for (int i = 0; i < ship.length; i++) {
			if (ship[i].passenger[0].getFristName().equals(EMPTY_CABIN)) {
				System.out.printf("Cabin Number: %d is empty \n", i);// checks and prints the empty ones
			} else {
				System.out.print("Cabin Number: " + i + " is occupied by:");
				for (int j = 0; j < MAX_PASSENGERS; j++) {// loops through occupied cabins to get individual passenger
					// names
					if (!ship[i].passenger[j].getFristName().equals(EMPTY_CABIN)) {
						System.out.printf("  - %s %s - ", ship[i].passenger[j].getFristName(),
								ship[i].passenger[j].getSurname());
					}
				}
				System.out.println();
			}
		}
	}

	/**
	 * Method allocates memory to ship array, initialising the passengers.
	 */
	public static void allocateMemory() {
		for (int i = 0; i < NUM_OF_CABINS; i++) {
			ship[i] = new Cabin();
			for (int y = 0; y < MAX_PASSENGERS; y++) {
				ship[i].passenger[y] = new Passenger();
			}
		}
	}

	/**
	 * Method does a lot of things
	 *
	 * @return altered Cabin [] array
	 */
	public static Cabin[] addPassengers() {
		boolean flag = true;
		while (flag) 
		// loop to make sure the customer enters an unoccupied cabin number and an integer for cabin
		{
			try {
				System.out.printf("Enter Number of Passengers Checking in to Cabin (Max %d)\n",MAX_PASSENGERS);
				int numOfPassengers = input.nextInt();// validate
				if (checkIfEmpty() == true) {
					// this handles the normal addition and only works in the Cabin array is not empty
					if (numOfPassengers > 0 && numOfPassengers <=  MAX_PASSENGERS ) {
						
						System.out.println("Enter the Cabin Number:");
						int cabinNum = input.nextInt();
						if (cabinNum >= 0 && cabinNum <= NUM_OF_CABINS - 1) {
							for (int i = 0; i < numOfPassengers; i++) {
								System.out.println("Enter passenger " + (i + 1) + "'s" + " First Name");
								String passengerName = input.next();
								System.out.println("Enter passenger " + (i + 1) + "'s" + " Surname");
								String passengerSurname = input.next();
								ship[cabinNum].passenger[i].setFirstName(passengerName);
								ship[cabinNum].passenger[i].setSurname(passengerSurname);
							}
						} else {
							System.out.println("Enter a Cabin Number between 0 and 11\n");
						}
						
					} else {
						System.out.printf("You must enter up to %d passengers in each cabin \n",MAX_PASSENGERS);
					}

				} else if (checkIfEmpty() == false) {
					addPassengerToQueue(numOfPassengers);
				}
				flag = false;// get out of loop

			} catch (InputMismatchException e) {
				System.err.println("Please enter a number");
				String somethingToMakeItStop = input.next();
			}
		}
		return ship;
	}

	/**
	 * Method to delete passengers from a cabin, only if it is occupied
	 *
	 */
	public static void deletePassenger() {
		try {
			System.out.println("Enter the Cabin Number you would like to delete passengers from");
			int cabinNum = input.nextInt();
			
			if (cabinNum >= 0 && cabinNum <= NUM_OF_CABINS - 1) {//only take numbers between 0 - 11
				
				if (!ship[cabinNum].passenger[0].getFristName().equals(EMPTY_CABIN)) {// check if the cabin number is occupied
					
					for (int i = 0; i < MAX_PASSENGERS; i++) {// loop through setting each possible passenger as Empty
						ship[cabinNum].passenger[i].setFirstName(EMPTY_CABIN);
						ship[cabinNum].passenger[i].setSurname(EMPTY_CABIN);
					}
					System.out.printf("Deleted all Passengers from Cabin Number: %d \n", cabinNum);
					addPassengerFromQueue(cabinNum);

				} else if (ship[cabinNum].passenger[0].getFristName().equals(EMPTY_CABIN)) {
					System.out.println("Cabin is already empty");
					
				}
				
			} else {
				System.out.println("Enter a Cabin Number between 0 and 11\n");
			}
		} catch (InputMismatchException e) {
			System.err.println("Please enter a number");
			String somethingToMakeItStop = input.next();
		}

	}

	/**
	 * Method to add passengers to queue, only adds if the cabin array is full and
	 * the queue is not full
	 *
	 * @param numberOfPassengers
	 */
	public static void addPassengerToQueue(int numberOfPassengers) {
		if (checkIfEmptyQueue() == true) {// only add if the queue is empty
			System.out.println("The Cabin is full, customers will be added to the Queue");
			for (int i = 0; i < numberOfPassengers; i++) {
				System.out.println("Enter passenger " + (i + 1) + "'s" + " First Name");
				String passengerName1 = input.next();
				System.out.println("Enter passenger " + (i + 1) + "'s" + " Surname");
				String passengerSurname1 = input.next();
				queue[queueEnd][i] = String.join("_", passengerName1, passengerSurname1);
			}
			System.out.println(Arrays.deepToString(queue));
			queueEnd++;
			if (queueEnd == ROWS) {
				queueEnd = 0;

			}

		} else {
			System.out.println("The Cabin and  Queue is full, can't add any more passengers");
		}
	}

	/**
	 * Method to add to queue 2d array
	 *
	 * @param cabinNumber
	 */
	public static void addPassengerFromQueue(int cabinNumber) {
		if (!queue[queueFront][0].equals(EMPTY_CABIN)) {
			for (int i = 0; i < MAX_PASSENGERS; i++) {
				if (!queue[queueFront][i].equals(EMPTY_CABIN)) {
					String firstAndSurname = queue[queueFront][i];
					queue[queueFront][i] = EMPTY_CABIN;
					String[] nameParts = firstAndSurname.split("_");
					String first_name = nameParts[0];
					String sur_name = nameParts[1];
					System.out.printf(" [%s]  and  [%s]  have been added     ", first_name, sur_name);
					ship[cabinNumber].passenger[i].setFirstName(first_name);
					ship[cabinNumber].passenger[i].setSurname(sur_name);
				}
			}
			queueFront++;
			if (queueFront == ROWS) {
				queueFront = 0;

			}
		}
	}

	/**
	 * Method to loop through array and display cabins. Because the cabins are
	 * filled from passenger[0] we need only check this
	 */
	public static void emptyCabins() {
		for (int i = 0; i < ship.length; i++) {
			if (ship[i].passenger[0].getFristName().equals(EMPTY_CABIN)) {
				System.out.printf("Cabin Number: %d is empty \n", i);
			}
		}
	}

	/**
	 * Check if Cabin Array is empty. Needed for queue
	 *
	 * @return Boolean either true or false if empty or not
	 */
	public static boolean checkIfEmpty() {
		int num = 0;
		for (Cabin ship1 : ship) {
			if (ship1.passenger[0].getFristName().equals(EMPTY_CABIN)) {
				num++;
			}
		}
		return num > 0;
	}

	/**
	 * Check if Queue Array is empty. Needed for queue
	 *
	 * @return Boolean either true or false if empty or not
	 */
	public static boolean checkIfEmptyQueue() {
		int num = 0;
		for (int i = 0; i < ROWS; i++) {
			if (queue[i][0].equals(EMPTY_CABIN)) {
				num++;
			}
		}
		return num > 0;
	}

	/**
	 * Method to print the menu
	 */
	public static void menuPrint() {
		System.out.println("\n\nPlease enter a choice:");
		System.out.println("A: Add customer to a Cabin");
		System.out.println("V: View all Cabins");
		System.out.println("E: Display Empty Cabins");
		System.out.println("D: Delete customers from Cabin");
		System.out.println("F: Find Cabin from customer name");
		System.out.println("S: Store program data into file");
		System.out.println("L: Load program data from file");
		System.out.println("O: View passengers Ordered alphabetically by name");
		System.out.println("T: Print Expense per customer and total expenses");
		System.out.println("Q: To Quit program\n");
	}
}
