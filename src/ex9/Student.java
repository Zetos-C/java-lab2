package ex9;

public class Student {
	private int ID;
	private String name;
	private double avgScore;

	public Student(int iD, String name, double avgScore) {
		super();
		ID = iD;
		this.name = name;
		this.avgScore = avgScore;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}

}
