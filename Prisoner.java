class Prisoner extends Person {

	private int age;
	private String prisonerID;
	private String sentenceDetails;
	private String crimeType;
	private int volunteerHours = 0;
	private String freeTimeActivity;

	public Prisoner(String name, int age, String prisonerID, String sentenceDetails, String crimeType) {
		super(name);
		this.age = age;
		this.prisonerID = prisonerID;
		this.sentenceDetails = sentenceDetails;
		this.crimeType = crimeType;
	}

	// Override the getPersonID method in the Prisoner class
	@Override
	public String getPersonID() {
		return prisonerID;
	}

	public void addVolunteerHours(int hours, String activity) {
		volunteerHours += hours;
		freeTimeActivity = activity;
		System.out.println("Volunteer hours added successfully.");
	}

	public void displayVolunteerDetails() {
		System.out.println("Volunteer Details:");
		System.out.println("Volunteer Hours: " + volunteerHours);
		System.out.println("Free Time Activity: " + freeTimeActivity);
		System.out.println();
	}

	@Override
	public void displayDetails() {
		super.displayDetails();
		System.out.println("Age: " + age);
		System.out.println("Prisoner ID: " + prisonerID);
		System.out.println("Sentence Details: " + sentenceDetails);
		System.out.println("Crime Type: " + crimeType);
		System.out.println();
	}
}
