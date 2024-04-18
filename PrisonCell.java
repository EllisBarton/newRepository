class PrisonCell {
	private String cellName;
	private int cellNumber;
	private Prisoner assignedPrisoner;

	public PrisonCell(String cellName, int cellNumber) {
		this.cellName = cellName;
		this.cellNumber = cellNumber;
		this.assignedPrisoner = null;
	}

	public int getCellNumber() {
		return cellNumber;
	}

	public Prisoner getAssignedPrisoner() {
		return assignedPrisoner;
	}

	public void assignPrisoner(Prisoner prisoner) {
		this.assignedPrisoner = prisoner;
	}

	@Override
	public String toString() {
		return "Prison Cell " + cellNumber + ": " + cellName;
	}
}
