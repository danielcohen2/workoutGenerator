import java.util.ArrayList;
import java.util.List;

public class WorkoutExercises {
	
	private List<GroupOfExercises> exercisesGroups; 
	private List<Exercise> orderedWorkoutList;
	
	public WorkoutExercises() {
		exercisesGroups = new ArrayList<GroupOfExercises>();
		orderedWorkoutList = new ArrayList<Exercise>();
	}
	
	public WorkoutExercises(GroupOfExercises e1) {
		this();
		exercisesGroups.add(e1); 	
	}
	
	public WorkoutExercises(GroupOfExercises e1, GroupOfExercises e2) {
		this(e1);
		exercisesGroups.add(e2);
	}
	
	public WorkoutExercises(GroupOfExercises e1, GroupOfExercises e2, GroupOfExercises e3) {
		this(e1,e2);
		exercisesGroups.add(e3);
	}
	
	private int findSize() {
		return orderedWorkoutList.size();
	}

	public int getSize() {
		return findSize();
	}

	public void add(GroupOfExercises exercises) {
		this.exercisesGroups.add(exercises);	
	}

	public List<Exercise> getOrderedWorkoutList() {
		return orderedWorkoutList;
	}

	public void addToOrderedWorkoutList(Exercise e) {
		orderedWorkoutList.add(e);		
	}

	public List<GroupOfExercises> getExercisesGroups() {
		return exercisesGroups;
	}
	
	public String toString() {
		String exercisesStr = "";
		for (int i=0;i<exercisesGroups.size();i++) {
			exercisesStr+=exercisesGroups.get(i).toString();
			if (i!=exercisesGroups.size()-1)
				exercisesStr+="\n";
		}
		return exercisesStr;
	}
	
		
	



}
