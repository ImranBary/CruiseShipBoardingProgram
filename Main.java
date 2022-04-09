package cruiseShipClassesSolution;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static final String EMPTY_CABIN = "Empty";
	public static final int NUM_OF_CABINS = 12;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); 
		Cabin[] ship = new Cabin[NUM_OF_CABINS];
		for (int i = 0; i < 12; i++) {
			ship[i] = new Cabin();
			for (int y = 0; y < 3; y++) {
				ship[i].passenger[y] = new Passenger();// memory allocation
			}
		}
//		ship[0].passenger[0].setFirstName("James");
//		ship[1].passenger[0].setFirstName("James");
//		String x = ship[0].passenger[0].getFristName();
//		System.out.println(x);
//		ship[11].passenger[2].setFirstName("Stephen");
//
//		if (ship[7].passenger[0].getFristName() == null) {
//			System.out.println("this shit be empty");
//		}
//		for (int i = 0; i < ship.length; i++) {// make into method
//
//			if (ship[i].passenger[0].getFristName() == null) {
//				System.out.println("Cabin Numb: " + i + " is empty");
//			}
//		}

		boolean flag = true;
		int cabinNum, numOfPassengers;
		String passengerName, passengerSurname;
		while (flag) // loop to make sure the customer enters an unoccupied cabin number and an
		// integer for cabin
		{
			try {
//				emptyCabinNumbers(arr, NUM_OF_CABINS);
				System.out.println("Enter the Cabin Number:");
				cabinNum = input.nextInt();
				if (cabinNum >= 0 && cabinNum <= NUM_OF_CABINS - 1) {
					System.out.println("Enter Number of Passengers Checking in to Cabin (not more than 3)");
					numOfPassengers = input.nextInt();// validate
					if (numOfPassengers == 1 || numOfPassengers == 2 || numOfPassengers == 3) {
						for (int i = 0; i < numOfPassengers; i++) {
							System.out.println("Enter passenger " + (i + 1) + "'s" + " First Name");
							passengerName = input.next();
							System.out.println("Enter passenger " + (i + 1) + "'s" + " Surname");
							passengerSurname = input.next();
							ship[cabinNum].passenger[i].setFirstName(passengerName);
							ship[cabinNum].passenger[i].setFirstName(passengerSurname);
						}
						flag = false;
					} else {
						System.out.println("You must enter 1,2 or 3 passengers");
					}
				} else {
					System.out.println("Enter a Cabin Number between 0 and 11\n");
				}
			} catch (InputMismatchException e) {
				System.err.println("Please enter a number");
				String cr = input.next();
			}
		}
		

	}

	
}
