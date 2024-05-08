package task2;

import java.util.ArrayList;
import java.util.Objects;

public class SwimStudent implements Comparable<SwimStudent> {
	private String name;
	private Level level;
	private ArrayList<Qualification> qualifications;
	
	public SwimStudent(String name, Level level) {
		this.name = name;
		this.level = level;
		this.qualifications = new ArrayList<Qualification>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public String getQualifications() {
		StringBuilder sb = new StringBuilder();
		sb.append("Qualifications:\n");
		if (qualifications.isEmpty()) {
			sb.append("Student does not have any qualifications");
		} else {
			for (Qualification q: qualifications) {
				sb.append(q.toString());
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	public boolean addQualification(Qualification qualification) {
		if (qualifications.contains(qualification)) {
			return false;
		} else {
			qualifications.add(qualification);
			return true;
		}
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SwimStudent other = (SwimStudent) obj;
		return level == other.level && Objects.equals(name, other.name);
	}

	@Override
	public int compareTo(SwimStudent o) {
		return name.compareTo(o.name);
	}

	@Override
	public String toString() {
		return String.format("Student name: %s, Level: %s", name, level);
	}

}
