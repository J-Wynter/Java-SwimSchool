package task2;

import java.util.ArrayList;

public class SwimLesson {
	private static int MAX_STUDENTS = 4;
	
	private String day;
	private String startTime;
	private Level level;
	private Instructor instructor;
	private ArrayList<SwimStudent> students;

	public SwimLesson(String day, String startTime, Level level, Instructor instructor) {
		this.day = day;
		this.startTime = startTime;
		this.level = level;
		this.instructor = instructor;
		this.students = new ArrayList<SwimStudent>();
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getDay() {
		return day;
	}

	public String getStartTime() {
		return startTime;
	}
	
	public Instructor getInstructor() {
		return instructor;
	}
	
	public ArrayList<SwimStudent> getStudents() {
		return students;
	}
	
	public boolean isFull() {
		return students.size() == MAX_STUDENTS;
	}
	
	public int getAvailableSeats() {
		return MAX_STUDENTS - students.size();
	}
	
	public boolean isEmpty() {
		return students.size() == 0;
	}

	public boolean addStudent(SwimStudent student) {
		if (!isFull()) {
			students.add(student);
			return true;
		}
		return false;
	}

	public boolean hasStudent(SwimStudent student) {
		for (SwimStudent s : students) {
			if (s.equals(student)) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		return String.format("Swim Lesson - Day: %s, Start Time: %s, Level: %s, Instructor: %s", day, startTime, level,
				instructor.getName());
	}

}
