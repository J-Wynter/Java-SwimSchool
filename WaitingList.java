package task2;

import java.util.ArrayList;

public class WaitingList {
	private ArrayList<SwimStudent> students;
	
	public WaitingList() {
		students = new ArrayList<SwimStudent>();
	}
	
	public void addStudent(SwimStudent student) {
		students.add(student);
	}
	
	public boolean hasStudent(SwimStudent student) {
		for (SwimStudent s: students) {
			if (s.equals(student)) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<SwimStudent> getStudents() {
		return students;
	}
	
	public void removeStudent(SwimStudent student) {
		for (SwimStudent s: students) {
			if (s.equals(student)) {
				students.remove(s);
				break;
			}
		}
	}
}
