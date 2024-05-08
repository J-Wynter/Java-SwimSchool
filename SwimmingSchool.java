package task2;

import java.util.ArrayList;
import java.util.Collections;

public class SwimmingSchool {
	private ArrayList<SwimStudent> students;
	private ArrayList<SwimLesson> lessons;
	private ArrayList<Instructor> instructors;
	private WaitingList waitingList;
	
	public SwimmingSchool() {
		students = new ArrayList<SwimStudent>();
		lessons = new ArrayList<SwimLesson>();
		instructors = new ArrayList<Instructor>();
		waitingList = new WaitingList();
		
		loadData();
	}
	
	private void loadData() {
		Instructor i1 = new Instructor("Bob");
		Instructor i2 = new Instructor("Daisy");
		
		DistanceSwim q1 = new DistanceSwim(i1, 10);
		
		SwimStudent s1 = new SwimStudent("John", Level.Novice);
		SwimStudent s2 = new SwimStudent("Rose", Level.Improver);
		s2.addQualification(q1);
		SwimStudent s3 = new SwimStudent("Luke", Level.Improver);
		

		
		SwimLesson l1 = new SwimLesson("Monday", "12:00PM", Level.Novice, i1);
		l1.addStudent(s1);
		
		SwimLesson l2 = new SwimLesson("Saturday", "3:00PM", Level.Improver, i2);
		l2.addStudent(s2);
		l2.addStudent(s3);
		
		students.add(s1);
		students.add(s2);
		students.add(s3);
		
		instructors.add(i1);
		instructors.add(i2);
		
		lessons.add(l1);
		lessons.add(l2);
		
		
	}
	
	public String viewSwimStudentInformation(int index) {
		SwimStudent student = students.get(index);
		StringBuilder sb = new StringBuilder("Swim lessons attended by student:\n");
		boolean hasLessons = false;
		for (SwimLesson l: lessons) {
			if (l.hasStudent(student)) {
				sb.append(l.toString());
				sb.append("\n");
				hasLessons = true;
			}
		}
		if (!hasLessons) {
			sb.append("Student has not taken any swim lessons\n");
		}
		if (waitingList.hasStudent(student)) {
			sb.append("\nStudent is on the waiting list\n");
		}
		sb.append("\n");
		sb.append(student.getQualifications());
		return sb.toString();
	}
	
	public String viewSwimStudents() {
		Collections.sort(students);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < students.size(); i++) {
			sb.append(String.format("%d. %s - %s\n", i, students.get(i).getName(), students.get(i).getLevel()));
		}
		return sb.toString();
	}
	
	public String viewSwimLessons() {
		StringBuilder sb = new StringBuilder();
		
		SwimLesson l;
		for (int i = 0; i < lessons.size(); i++) {
			l = lessons.get(i);
			sb.append(String.format("%d. Day: %s, Start Time: %s, Level: %s\n", i, l.getDay(), l.getStartTime(), l.getLevel()));
		}
		return sb.toString();
	}
	
	public String viewInstructors() {
		Collections.sort(instructors);
		StringBuilder sb = new StringBuilder();
		
		Instructor ins;
		for (int i = 0; i < instructors.size(); i++) {
			ins = instructors.get(i);
			sb.append(String.format("%d. %s\n", i, ins.getName()));
		}
		return sb.toString();
	}
	
	public String viewSwimLessonDetails(int index) {
		SwimLesson lesson = lessons.get(index);
		
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Instructor Name: %s\n", lesson.getInstructor().getName()));
		sb.append("Students:\n");
		if (lesson.isEmpty()) {
			sb.append("This lesson does not have any students\n");
		} else {
			for (SwimStudent s: lesson.getStudents()) {
				sb.append(s.toString());
				sb.append("\n");
			}
		}
		
		if (lesson.isFull()) {
			sb.append("Swim Lesson is full");
		} else {
			sb.append(String.format("Swim Lesson has %d available seats", lesson.getAvailableSeats()));
		}
		
		return sb.toString();
	}
	
	public String viewInstructorSchedule(int index) {
		StringBuilder sb = new StringBuilder();
		Instructor i = instructors.get(index);
		
		for (SwimLesson l: lessons) {
			if (l.getInstructor().equals(i)) {
				sb.append(l.toString());
				sb.append("\n");
			}
		}
		
		return sb.toString();
		
	}
	
	public SwimStudent addStudent(String name) {
		SwimStudent student = new SwimStudent(name, Level.Novice);
		students.add(student);
		return student;
	}
	
	public void addToWaitingList(SwimStudent s) {
		waitingList.addStudent(s);
	}
	
	public void removeFromWaitingList(SwimStudent s) {
		waitingList.removeStudent(s);
	}
	
	public ArrayList<SwimLesson> getSwimLessons(Level level) {
		ArrayList<SwimLesson> swimLessons = new ArrayList<SwimLesson>();
		
		for (SwimLesson l: lessons) {
			if (l.getLevel() == level) {
				swimLessons.add(l);
			}
		}
		return swimLessons;
	}
	
	public SwimLesson getSwimLesson(String day, String startTime) {
		for (SwimLesson l: lessons) {
			if (l.getDay().equals(day) && l.getStartTime().equals(startTime)) {
				return l;
			}
		}
		return null;
	}
	
	public Instructor getInstructor(int index) {
		return instructors.get(index);
	}
	
	public SwimStudent getStudent(int index) {
		return students.get(index);
	}
	
	public ArrayList<SwimStudent> getStudentsAccordingToLevel() {
		ArrayList<SwimStudent> temp = new ArrayList<SwimStudent>();
		for (SwimStudent s: waitingList.getStudents()) {
			if (!students.contains(s)) {
				temp.add(s);
			}
		}
		for (SwimStudent s: waitingList.getStudents()) {
			if (students.contains(s) && s.getLevel() == Level.Novice) {
				temp.add(s);
			}
		}
		
		for (SwimStudent s: waitingList.getStudents()) {
			if (students.contains(s) && s.getLevel() == Level.Improver) {
				temp.add(s);
			}
		}
		
		for (SwimStudent s: waitingList.getStudents()) {
			if (students.contains(s) && s.getLevel() == Level.Advanced) {
				temp.add(s);
			}
		}
		return temp;
	}
}
