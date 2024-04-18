import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class PrisonManager {

	private static ArrayList<Person> persons = new ArrayList<>();
	private static HashSet<String> freeTimeActivities = new HashSet<>();
	private static ArrayList<PrisonCell> prisonCells = new ArrayList<>();
	private static Warden[] prisonStaff = new Warden[3];
	private static HashMap<String, Person> personMap = new HashMap<>();
	private static Warden currentWarden;
	static {

		Prisoner johnDoe = new Prisoner("John Doe", 30, "JD123", "5 years", "Theft");
		Prisoner janeSmith = new Prisoner("Jane Smith", 25, "JS456", "15 years", "Fraud");
		Prisoner mikeJohnson = new Prisoner("Mike Johnson", 35, "MJ789", "20 years", "Assault");

		persons.add(johnDoe);
		persons.add(janeSmith);
		persons.add(mikeJohnson);

		prisonStaff[0] = new Warden("Chief Warden", 50);
		prisonStaff[1] = new Warden("Assistant Warden 1", 45);
		prisonStaff[2] = new Warden("Assistant Warden 2", 40);

		freeTimeActivities.add("Basketball");
		freeTimeActivities.add("Soccer");
		freeTimeActivities.add("Weightlifting");
		freeTimeActivities.add("Library");

		prisonCells.add(new PrisonCell("A Block", 101));
		prisonCells.add(new PrisonCell("B Block", 102));
		prisonCells.add(new PrisonCell("C Block", 103));

		personMap.put(johnDoe.getPersonID(), johnDoe);
		personMap.put(janeSmith.getPersonID(), janeSmith);
		personMap.put(mikeJohnson.getPersonID(), mikeJohnson);

		initializeWarden();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Prison Management System.");
		initializeWarden();
		while (true) {
			System.out.println("Are you a 'Warden' or a 'Prisoner'? Enter your role (type 'exit' to end):");
			String role = scanner.nextLine();

			if ("Warden".equalsIgnoreCase(role)) {
				interactAsWarden(scanner);
			} else if ("Prisoner".equalsIgnoreCase(role)) {
				interactAsPrisoner(scanner);
			} else if ("exit".equalsIgnoreCase(role)) {
				break;
			} else {
				System.out.println("Invalid role entered. Please try again.");
			}
		}

		scanner.close();
	}

	private static void interactAsWarden(Scanner scanner) {
		while (true) {
			System.out.println("Welcome, Warden!");
			System.out.println("1. Display all persons");
			System.out.println("2. Add a new prisoner");
			System.out.println("3. Manage prison funds");
			System.out.println("4. View person assignments");
			System.out.println("Enter your choice:");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				displayAllPersons();
				break;
			case 2:
				addNewPrisoner(scanner);
				break;
			case 3:
				managePrisonFunds(scanner);
				break;
			case 4:
				viewPersonAssignments();
				break;
			default:
				System.out.println("Invalid choice.");
			}
		}
	}

	private static void interactAsPrisoner(Scanner scanner) {
		System.out.println("Welcome, Person!");

		System.out.println("Enter your name to access your account:");
		String personName = scanner.nextLine();

		Person currentPerson = getPersonByName(personName);

		if (currentPerson != null) {
			while (true) {
				System.out.println("1. View my details");
				System.out.println("2. Add volunteer hours");
				System.out.println("3. Exit");

				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					currentPerson.displayDetails();
					if (currentPerson instanceof Prisoner) {
						((Prisoner) currentPerson).displayVolunteerDetails();
					}
					break;
				case 2:
					if (currentPerson instanceof Prisoner) {
						addVolunteerHours(scanner, (Prisoner) currentPerson);
					} else {
						System.out.println("Only prisoners can add volunteer hours.");
					}
					break;
				case 3:
					return;
				default:
					System.out.println("Invalid choice.");
				}
			}
		} else {
			System.out.println("Person not found. Please check your name and try again.");
		}
	}

	private static void displayAllPersons() {
		System.out.println("All Persons:");
		for (Person person : persons) {
			person.displayDetails();
		}
	}

	private static void addNewPrisoner(Scanner scanner) {
		System.out.println("Enter person name:");
		String name = scanner.nextLine();

		System.out.println("Enter person age:");
		int age = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Enter person ID:");
		String personID = scanner.nextLine();

		System.out.println("Enter sentence details:");
		String sentenceDetails = scanner.nextLine();

		System.out.println("Enter crime type:");
		String crimeType = scanner.nextLine();

		System.out.println("Select a prison cell (enter cell number):");
		int cellNumber = scanner.nextInt();
		scanner.nextLine();

		PrisonCell selectedCell = getPrisonCellByNumber(cellNumber);

		if (selectedCell != null) {
			Prisoner newPrisoner = new Prisoner(name, age, personID, sentenceDetails, crimeType);
			persons.add(newPrisoner);
			selectedCell.assignPrisoner(newPrisoner);
			System.out.println("Person added successfully.");
		} else {
			System.out.println("Invalid cell number. Person not added.");
		}
	}

	private static void managePrisonFunds(Scanner scanner) {
		System.out.println("Current Prison Funds: $" + getWarden().getPrisonFunds());
		System.out.println("1. Add funds");
		System.out.println("2. Remove funds");
		System.out.println("Enter your choice:");

		int choice = scanner.nextInt();
		scanner.nextLine();

		switch (choice) {
		case 1:
			System.out.println("Enter the amount to add: $");
			double amountToAdd = scanner.nextDouble();
			getWarden().addFunds(amountToAdd);
			System.out.println("Funds added successfully.");
			break;
		case 2:
			System.out.println("Enter the amount to remove: $");
			double amountToRemove = scanner.nextDouble();
			getWarden().removeFunds(amountToRemove);
			System.out.println("Funds removed successfully.");
			break;
		default:
			System.out.println("Invalid choice.");
		}
	}

	private static void addVolunteerHours(Scanner scanner, Prisoner currentPrisoner) {
		System.out.println("Enter the number of volunteer hours:");
		int hours = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Select free time activity (basketball, soccer, weightlifting, library):");
		String activity = scanner.nextLine();

		currentPrisoner.addVolunteerHours(hours, activity);
		freeTimeActivities.add(activity);
	}

	private static void viewPersonAssignments() {
		System.out.println("Person Assignments:");
		for (PrisonCell cell : prisonCells) {
			if (cell.getAssignedPrisoner() != null) {
				System.out.println(cell + " - " + cell.getAssignedPrisoner().getName());
			}
		}
	}

	private static PrisonCell getPrisonCellByNumber(int cellNumber) {
		for (PrisonCell cell : prisonCells) {
			if (cell.getCellNumber() == cellNumber) {
				return cell;
			}
		}
		return null;
	}

	private static void initializeWarden() {
		if (prisonStaff[0] instanceof Warden) {
			currentWarden = (Warden) prisonStaff[0];
		} else {
			System.out.println("Error: Chief Warden not found in the prison staff.");
		}
	}

	private static Warden getWarden() {
		return currentWarden;
	}

	private static Person getPersonByName(String name) {
		for (Person person : persons) {
			if (person.getName().equalsIgnoreCase(name)) {
				return person;
			}
		}
		return null;
	}
}
