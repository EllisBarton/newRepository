class Warden extends Person {

	private double prisonFunds;

	public Warden(String name, double prisonFunds) {
		super(name);
		this.prisonFunds = prisonFunds;
	}

	public double getPrisonFunds() {
		return prisonFunds;
	}

	public void addFunds(double amount) {
		prisonFunds += amount;
	}

	public void removeFunds(double amount) {
		if (amount <= prisonFunds) {
			prisonFunds -= amount;
		} else {
			System.out.println("Insufficient funds.");
		}
	}
}
