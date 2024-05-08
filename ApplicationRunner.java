
package task2;

import java.util.ArrayList;
import java.util.Scanner;

public class ApplicationRunner {
    
    private static Scanner in = new Scanner(System.in);


	public static void main(String[] args) {
		SwimmingSchool school = new SwimmingSchool();
		
		int option = -1;
		do {
			System.out.println("1. View swim student information");
			System.out.println("2. View swim lesson details");
			System.out.println("3. View instructor schedule");
			System.out.println("4. Add new swim student");
			System.out.println("5. Award swim qualifications");
			System.out.println("6. Move swim student form waiting list");
			System.out.println("0. Exit");			
			option = getIntegerInput("Select> ");
			
			if (option == 1) {
				System.out.println("Students: ");
				System.out.println(school.viewSwimStudents());
				int index = getIntegerInput("Select student: ");
				System.out.println(school.viewSwimStudentInformation(index));
			} else if (option == 2) {
				System.out.println("Swim Classes:");
				System.out.println(school.viewSwimLessons());
				int index = getIntegerInput("Select swim lesson: ");
				System.out.println(school.viewSwimLessonDetails(index));
			} else if (option == 3) {
				System.out.println("Instructors: ");
				System.out.println(school.viewInstructors());
				int index = getIntegerInput("Select instructor: ");
				System.out.println(school.viewInstructorSchedule(index));
			} else if (option == 4) {
				String name = getStringInput("Enter new student name: ");
				SwimStudent s = new SwimStudent(name, Level.Novice);
				
				ArrayList<SwimLesson> noviceSwimLessons = school.getSwimLessons(Level.Novice);
				if (noviceSwimLessons.isEmpty()) {
					school.addToWaitingList(s);
					System.out.println("There are currently no novice swim lessons available.");
					System.out.println("Student has been added to the waiting list");
				} else {
					System.out.println("\n Available Novice Swim Lessons:");
					for (int i = 0; i < noviceSwimLessons.size(); i++) {
						System.out.printf("%d. %s\n", i, noviceSwimLessons.get(i));
					}
					int index = getIntegerInput("Select lesson: ");
					SwimLesson lesson = noviceSwimLessons.get(index);
					if (lesson.isFull()) {
						System.out.println("Selected swim lesson is already full");
					} else {
						school.addStudent(name);
						lesson.addStudent(s);
						System.out.println("Student has been added to the swim lesson");
					}
				}
			} else if (option == 5) {
				System.out.println("Instructors: ");
				System.out.println(school.viewInstructors());
				int instructorIndex = getIntegerInput("Select instructor: ");
				Instructor instructor = school.getInstructor(instructorIndex);
				
				System.out.println("\nStudents: ");
				System.out.println(school.viewSwimStudents());
				int studentIndex = getIntegerInput("Select student: ");
				SwimStudent student = school.getStudent(studentIndex);
				Qualification qualification;
				if (student.getLevel() == Level.Advanced) {
					int qualificationType = getIntegerInput("1. Distance Swim\n2. Personal Survival\nSelect> ");
					
					if (qualificationType == 1) {
						int distance = getIntegerInput("Enter distance: ");
						qualification = new DistanceSwim(instructor, distance);
					} else {
						int levelType = getIntegerInput("1. Novice\n2. Improver\n3. Advanced\nSelect: ");
						Level level = Level.getLevel(levelType);
						qualification = new PersonalSurvival(instructor, level);
					}
					
					if (student.addQualification(qualification)) {
						System.out.println("Qualification has been added");
					} else {
						System.out.println("Student already has that qualification");
					}
				} else {
					int distance = getIntegerInput("Enter distance: ");
					qualification = new DistanceSwim(instructor, distance);
					
					if (student.addQualification(qualification)) {
						System.out.println("Qualification has been added");
						
						if (student.getLevel() == Level.Novice && distance == 20) {
							student.setLevel(Level.Improver);
							System.out.println("Student has been leveled up to improver");
							school.addToWaitingList(student);
							System.out.println("Student has been added to waiting list");
						} else if (student.getLevel() == Level.Improver && distance == 400) {
							student.setLevel(Level.Advanced);
							System.out.println("Student has been leveled up to advanced");
							school.addToWaitingList(student);
							System.out.println("Student has been added to waiting list");
						}
					} else {
						System.out.println("Student already has that qualification");
					}
				}
			} else if (option == 6) {
				ArrayList<SwimStudent> students = school.getStudentsAccordingToLevel();
				
				System.out.println("Students: ");
				for (int i = 0; i < students.size(); i++) {
					System.out.printf("%d. %s - %s\n", i, students.get(i).getName(), students.get(i).getLevel());
				}
				int studentIndex = getIntegerInput("Select student: ");
				
				SwimStudent student = school.getStudent(studentIndex);

				ArrayList<SwimLesson> swimLessons = school.getSwimLessons(students.get(studentIndex).getLevel());
				
				if (swimLessons.isEmpty()) {
					System.out.println("There are currently no swim lessons available for this level");
				} else {
					System.out.println("Available Swim Lessons: ");
					
					for (int i = 0; i < swimLessons.size(); i++) {
						System.out.printf("%d. %s\n", i, swimLessons.get(i));
					}
					
					int index = getIntegerInput("Select lesson: ");
					SwimLesson lesson = swimLessons.get(index);
					if (lesson.isFull()) {
						System.out.println("Selected swim lesson is already full");
					} else {
						lesson.addStudent(student);
						school.removeFromWaitingList(student);
						System.out.println("Student has been added to the swim lesson");
					}
					
				}

			}
			
			System.out.println();
		} while (option != 0);
		
		in.close();
	}
	
	public static int getIntegerInput(String prompt) {
		System.out.print(prompt);
		int value = in.nextInt();
		in.nextLine();
		return value;
	}
	
	public static String getStringInput(String prompt) {
		System.out.print(prompt);
		return in.nextLine();
	}
    
}
