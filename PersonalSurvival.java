package task2;

public class PersonalSurvival extends Qualification {
	private Level level;

	public PersonalSurvival(Instructor assessor, Level level) {
		super(assessor);
		this.level = level;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonalSurvival other = (PersonalSurvival) obj;
		return level == other.level;
	}

}
