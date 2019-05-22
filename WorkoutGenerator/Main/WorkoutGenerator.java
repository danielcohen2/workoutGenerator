import java.util.List;

public class WorkoutGenerator {
	
	private List<ExerciseInfo> exercises; 
	
	public WorkoutGenerator() {
		//TextBasedUI ui
				//ui.promptWorkoutType
				//ui.promptFocuses
				
				//use answers to create random workout
	}
	
	
	public WorkoutGenerator(Focus focus, Type type) {
		exercises = getExercisesInfo();
		Workout w = new Workout(focus,type,exercises);
		System.out.println(w);
		
		
	}
	
	private static List<ExerciseInfo> getExercisesInfo() {
		ExerciseDatabase db = new ExerciseDatabase("C:\\Users\\lenovo\\Documents\\WorkspaceNew\\WorkoutGenerator\\ExerciseDatabase1.xlsx");
		return db.getExercises();  
	}
	
	
	public static void main(String[] args) {
		
		//ideally Focus->MuscleGroup-> bodyPart and General Body Part are enums so that there's a pop up box that you choose from the options and select
		//has enums
		WorkoutGenerator wg2 = new WorkoutGenerator(new Focus(BodyPart.Chest,BodyPart.Back),Type.superset);	
		WorkoutGenerator wg3 = new WorkoutGenerator(new Focus(GeneralBodyPart.UpperBody),Type.circuit);
		WorkoutGenerator wg4 = new WorkoutGenerator(new Focus(GeneralBodyPart.Arms),Type.regularMedium);
		
	}





}
