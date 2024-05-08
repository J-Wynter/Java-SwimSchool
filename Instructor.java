package task2;

import java.util.Objects;

public class Instructor implements Comparable<Instructor> {
	private String name;
	
	public Instructor(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instructor other = (Instructor) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public int compareTo(Instructor o) {
		return name.compareTo(o.name);
	}
	
	
}
