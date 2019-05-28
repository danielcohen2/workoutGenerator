import java.util.List;

public interface AccessDatabase {
	
	//public List<ExerciseInfo> createListFromDatabase(String path);
	public List<ExerciseInfo> fetchExercises();
	
	

}