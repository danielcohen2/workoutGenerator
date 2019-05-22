import java.util.List;

public class GroupOfExercises {
	
	private List<Exercise> exercises;
	private MuscleGroup m;
	
	public GroupOfExercises(List<Exercise> exercises, MuscleGroup m) {
		this.setExercises(exercises);
		this.m = m;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	public MuscleGroup getM() {
		return m;
	}

	public int size() {
		return exercises.size();
	}
	
	public String toString() {
		String muscleGroup = m.toString() + " exercises: ";
		String exercisesStr = "";
		for (Exercise e : exercises) {
			exercisesStr+="\n"+e.toString();
		}
		return muscleGroup+exercisesStr;
	}
	
	public void print() {
		System.out.println(m  + " exercises: ");
		for (Exercise e : exercises) {
			System.out.println(e);
		}
	}
	
	

}
