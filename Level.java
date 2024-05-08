package task2;

public enum Level {
	Novice,
	Improver,
	Advanced;
	
	public static Level getLevel(int id) {
		if (id == 1) {
			return Novice;
		} else if (id == 2) {
			return Improver;
		} else {
			return Advanced;
		}
	}
}
