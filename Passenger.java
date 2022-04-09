package cruiseShipClassesSolution;

public class Passenger {
	public static final String EMPTY_CABIN = "EMPTY";
	
	private String firstName = EMPTY_CABIN;
	private String surname = EMPTY_CABIN ;
	private int expenses = 200;
	
	public void setFirstName(String firstName) {
		this.firstName = firstName.toUpperCase();
	}
	public void setSurname (String surname) {
		this.surname = surname.toUpperCase();
	}

	public String getFristName() {
		return firstName;
	}
	public String getSurname() {
		return surname;
	}
	public int getExpenses() {
		return expenses;
	}
}
