class Person {

	protected String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getPersonID() {

		return "";
	}

	public void displayDetails() {
		System.out.println("Person Details:");
		System.out.println("Name: " + name);
	}
}
