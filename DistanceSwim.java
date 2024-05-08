package task2;

public class DistanceSwim extends Qualification {
	private int distance;

	public DistanceSwim(Instructor assessor, int distance) {
		super(assessor);
		this.distance = distance;
	}
	
	public String toString() {
		return String.format("Distance Swim %dm - Assessed by: ", distance, assessor.getName());
	}
	
}
