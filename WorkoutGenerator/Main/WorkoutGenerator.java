import java.nio.file.FileSystems;
import java.nio.file.Path;
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
		//ExerciseDatabase db = new ExerciseDatabase(".\\ExerciseDatabase2.xlsx");
		ExerciseDatabase db = new ExerciseDatabase("C:\\Users\\lenovo\\Documents\\WorkspaceNew\\WorkoutGenerator\\ExerciseDatabase2.xlsx");
		return db.getExercises();  
	}
	
	public String toString() {
		return w.toString();
	}
	
	public static void main(String[] args) {
		//ideally Focus->MuscleGroup-> bodyPart and General Body Part are enums so that there's a pop up box that you choose from the options and select
		//has enums
		//WorkoutGenerator wg2 = new WorkoutGenerator(new Focus(BodyPart.Chest,BodyPart.Back),Type.superset);	
		//WorkoutGenerator wg3 = new WorkoutGenerator(new Focus(GeneralBodyPart.UpperBody),Type.circuit);
		//WorkoutGenerator wg4 = new WorkoutGenerator(new Focus(GeneralBodyPart.Arms),Type.regularMedium);
		String cwd = System.getProperty("user.dir");
        System.out.println("Current working directory : " + cwd);
		
		WorkoutGenerator wgUI = new WorkoutGenerator();
	}





}
