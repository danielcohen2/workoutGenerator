import java.util.List;

public class WorkoutGenerator {
	
	private List<ExerciseInfo> exercises; 
	private Workout w;
	
	public WorkoutGenerator() {
		UI ui = new UI();
	}
		
	public WorkoutGenerator(Focus focus, Type type) {
		exercises = getExercisesInfo();
		w = new Workout(focus,type,exercises);	
	}
	
	private static List<ExerciseInfo> getExercisesInfo() {
		ExcelDatabase db = new ExcelDatabase(".\\ExerciseDatabase2.xlsx");
		return db.getExercises(); 
	}
	
	public String toString() {
		return w.toString();
	}
	
	public Workout getWorkout() {
		return w;
	}
	
	public static void main(String[] args) {	
		WorkoutGenerator wgUI = new WorkoutGenerator();
	}





}
