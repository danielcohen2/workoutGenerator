import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Workout {
	
	private List<ExerciseInfo> exerciseDB;
	
	private Focus focus;
	private Type type;
	private WorkoutExercises exercises;
	private List<Exercise> orderedExerciseList;
	private List<GroupOfExercises> exercisesByMuscleGroup;
	
	private static Random rand;
	
	private final int NUM_TOTAL_EXERCISES = 6; //6
	private boolean isValidParameters;
	
	public Workout(Focus focus, Type type, List<ExerciseInfo> exerciseInfo) {
		this.focus = focus;
		this.type = type;	
		this.exerciseDB = exerciseInfo;
		this.rand = new Random();
		
		if (!isValidWorkoutParamters()) {
			isValidParameters = false;
		}
		else {	
			isValidParameters = true;
			this.exercises = createExercisesForWorkout();	
			this.orderedExerciseList = exercises.getOrderedWorkoutList();
			this.exercisesByMuscleGroup = exercises.getExercisesGroups();
		}	
	}

	//need 12-25 sets per workout
		//rest - 2-4 mins for strength, 30-60 secs endurance, 1-2 powe r
		//finisher ->5 mins of either cardio or abs 
	public String workoutFormat() {
		String formatStr = "Workout: "+"\n";
		Type t = getType();
		int restTime;
		int exerciseNumber;
		char letterNumber;
		switch(t) {
		case circuit :
			formatStr+="Directions: complete the series of exercises in succession with minimal rest in between. Once all exercises are completed, rest for prescribed amout of time, then repeat. \n"; 
			restTime=rand.nextInt(2)+1; //either 1 or 2 mins
			exerciseNumber=1;
			letterNumber = 'a';
			for (Exercise e : orderedExerciseList) { //need to alterante primary and secondary?? alternate uni/bi lateral movements? 
				e.getFormat().setSets(1);
				e.getFormat().setRepRange(10,12);
				e.getFormat().setRestTime(0);
				formatStr+=(exerciseNumber+(""+letterNumber)+". "+e+"\n");	
				letterNumber++;		
			}
			formatStr+=("Rest || " + restTime + " mins"+"\n");
			formatStr+=("Repeat circuit for 3 sets \n");	
			break;
		case superset :
			formatStr+="Directions: complete one set of A and then B before resting. Repeat for all the prescribed sets before moving on to the next pair.\r\n"; 
			//8-10 reps, 3 sets of each superset then move to hte next
			//1a.
			//1b
			//back to back exercises with no rest in between. 1-2 mins of rest in between
			restTime=rand.nextInt(2)+1; //either 1 or 2 mins
			int numOfRestPeriods=this.NUM_TOTAL_EXERCISES/2; 
			exerciseNumber=1;
			int exerciseTracker=0;
			while (numOfRestPeriods>0) {
				letterNumber = 'a';
				Exercise e = orderedExerciseList.get(exerciseTracker);
				e.getFormat().setSets(3);
				e.getFormat().setRepRange(8,10);
				e.getFormat().setRestTime(0);
				formatStr+=(exerciseNumber+(""+letterNumber)+". "+e+"\n");	
				letterNumber++;
				exerciseTracker++;
				Exercise e2 =  orderedExerciseList.get(exerciseTracker);
				e2.getFormat().setSets(3);
				e2.getFormat().setRepRange(8,10);
				e2.getFormat().setRestTime(restTime);
				formatStr+=(exerciseNumber+(""+letterNumber)+". "+e2+"\n");	
				exerciseNumber++;
				exerciseTracker++;
				//formatStr+=("Rest || " + restTime + " mins");
				numOfRestPeriods--;		
				/*if (numOfRestPeriods!=0)
					formatStr+="\n";*/
			}
			break;
		case regularLight:
			//15 reps, 1-2 mins rest in between. 2-3 sets
			formatStr+="Directions: complete each exercise and wait to complete its next set until alloted rest time has passed (if there's a lot of rest time in between, feel free to do a set of the next exercise in between to speed up workout) \n"; 
			restTime=rand.nextInt(2)+1; //either 1 or 2 mins
			int setNumber = rand.nextInt(2)+2; //either 2 or 3 sets
			int repNumber = (setNumber == 2 ? 20 : 15); //if sets=2, then 20 reps, if sets=3 then do 15 reps
			exerciseNumber=1;
			for (Exercise e : orderedExerciseList) { //need to alterante primary and secondary?? alternate uni/bi lateral movements? 
				e.getFormat().setSets(setNumber);
				e.getFormat().setRepRange(repNumber);
				e.getFormat().setRestTime(restTime);
				formatStr+=(exerciseNumber+". "+e+"\n");
				exerciseNumber++;
			}	
			break;
		case regularMedium:
			//8-12 reps, 1-2 mins rest in between. 3-4 sets
			formatStr+="Directions: complete each exercise and wait to complete its next set until alloted rest time has passed (if there's a lot of rest time in between, feel free to do a set of the next exercise in between to speed up workout) \n"; 
			restTime=rand.nextInt(2)+1; //either 1 or 2 mins
			setNumber = rand.nextInt(2)+3; //either 3 or 4 sets
			exerciseNumber=1;
			for (Exercise e : orderedExerciseList) { //need to alterante primary and secondary?? alternate uni/bi lateral movements? 
				e.getFormat().setSets(setNumber);
				e.getFormat().setRepRange(8,12);
				e.getFormat().setRestTime(restTime);
				formatStr+=(exerciseNumber+". "+e+"\n");
				exerciseNumber++;
			}
			break;
		case regularHeavy:
			//5 reps, 3-4 mins rest in between, 5 sets
			formatStr+="Directions: complete each exercise and wait to complete its next set until alloted rest time has passed (if there's a lot of rest time in between, feel free to do a set of the next exercise in between to speed up workout) \n"; 
			restTime=rand.nextInt(2)+3; //either 3 or 4 mins
			setNumber = 5; //either 3 or 4 sets
			repNumber = 5; 
			exerciseNumber=1;
			for (Exercise e : orderedExerciseList) { //need to alterante primary and secondary?? alternate uni/bi lateral movements? 
				e.getFormat().setSets(setNumber);
				e.getFormat().setRepRange(repNumber);
				e.getFormat().setRestTime(restTime);
				formatStr+=(exerciseNumber+". "+e+"\n");
				exerciseNumber++;
			}
			break;
		default:
			break;
		}
		return formatStr;
	}
	
	
		//get list of exercises for workout -> depends on focus, grab 6 
	public List<ExerciseInfo> getExerciseDB() {
		return exerciseDB;
	}

	public Focus getFocus() {
		return focus;
	}
	
	public void setFocus(Focus focus) {
		this.focus = focus;
	}

	public int getNUM_TOTAL_EXERCISES() {
		return NUM_TOTAL_EXERCISES;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public WorkoutExercises getExercises() {
		return exercises;
	}

	public void setExercises(WorkoutExercises workoutExercises) {
		this.exercises = workoutExercises;
	}

	public List<Exercise> getOrderedExerciseList() {
		return orderedExerciseList;
	}

	public List<GroupOfExercises> getExercisesByMuscleGroup() {
		return exercisesByMuscleGroup;
	}
		
	public void printExercises() {
		for (Exercise e : getOrderedExerciseList())
			System.out.println(e);
	}
	
	public String toString() {
		String str = "";
		String focusStr = focus.toString();
		str+=focusStr;
		String typeStr = type.toString();
		str+=("\n"+"Type: "+ typeStr);
		//str+=("\n"+exercises.toString());
		str+=("\n"+workoutFormat());
		return str;
		
	}
	
	private WorkoutExercises createExercisesForWorkout() {
		WorkoutExercises exercises = new WorkoutExercises(); 
		int numberOfMuscleGroupsInFocus = getFocus().getSizeOfFocus(); //should be 1, 2, or 3
		
		int numExercisesForEachMuscleGroup = getNUM_TOTAL_EXERCISES() / numberOfMuscleGroupsInFocus;		
		
		while (exercises.getSize() < getNUM_TOTAL_EXERCISES()) {
			List<MuscleGroup> muscleGroups = getFocus().getMuscleGroups();
			for (MuscleGroup m : muscleGroups) { //create list of exercises for each of the muscle groups
				GroupOfExercises g = createSetOfExercisesForMG(numExercisesForEachMuscleGroup, m, getExerciseDB());
				exercises.add(g); 
			}		
			for (int counterForEachExercise=0; counterForEachExercise < numExercisesForEachMuscleGroup; counterForEachExercise++) {
				//loop through different groupsOfExercises (separated by MuscleGroups) and add one exercise of each so that main list is alternating musclegroups
				for (GroupOfExercises g : exercises.getExercisesGroups()) {
					Exercise e = g.getExercises().get(counterForEachExercise);
					if (exercises.getOrderedWorkoutList().contains(e)) {
						System.out.println("very rare situation that 2 exercises are the same from differnt muscle group lists (i.e chin up (back), chin up (biceps)");
						break;
					}
					exercises.addToOrderedWorkoutList(g.getExercises().get(counterForEachExercise));
				} 
			}
			//if there aren't NUM_TOTAL EXERCISES in exercises, then will start back at the  re-makes groups of exercises
		}
		return exercises;
	
		//return exercises;
	}
	
	//quick check to see if it's possible to create required number of unique exercises (some MuscleGroups only have a few unique exercises) - used in UI 
	private boolean possibleToCreateSetOfExercisesForMG(int numOfExercisesPerMuscleGroup, MuscleGroup muscleGroup, List<ExerciseInfo> db) {
		List<Exercise> allExercisesForMuscleGroup = createFilteredExerciseListByMGFromDB(muscleGroup,db);
		List<Integer>  diffUniqueExercises = new ArrayList<Integer>();		
		for (Exercise e : allExercisesForMuscleGroup) {
			if (!diffUniqueExercises.contains(e.hashCode()))
				diffUniqueExercises.add(e.hashCode());
		}
		int numUnique = diffUniqueExercises.size();
		return (numUnique >= numOfExercisesPerMuscleGroup);
	}
	
	
	private GroupOfExercises createSetOfExercisesForMG(int numOfExercisesPerMuscleGroup, MuscleGroup muscleGroup, List<ExerciseInfo> db) {
		List<Exercise> allExercisesForMuscleGroup = createFilteredExerciseListByMGFromDB(muscleGroup,db);
		
		List<Exercise> selectedExercisesForMuscleGroup = new ArrayList<Exercise>();
			
		boolean alternatePMG = false; 
		if (muscleGroup instanceof GeneralBodyPart) //for general muscle groups, it's selecting exercise from one big concatenated list of different primary muscle groups, so want to make sure there's some logic so exercises chosen are not all same primary muscle group within the general muscle group (i.e not all bicep in arms workout)
			alternatePMG = true;
		
		//NEED TO ADDRESS - 5/24/19 - this is assuming that wont get into infinte loop - all exercises need to have atleast 6 unique execises (HAMSTRINGS is example of this) 
		while (selectedExercisesForMuscleGroup.size()<numOfExercisesPerMuscleGroup) { //randomly select exercise from potential list and add it to workout list if not a duplicate
			
				
			boolean addExercise = true; 
			int randomNumberToSelectExercise = rand.nextInt(allExercisesForMuscleGroup.size()); 
			Exercise e = allExercisesForMuscleGroup.get(randomNumberToSelectExercise);
			if (alternatePMG) { //if it's a general muscle group, make sure new exercise is different primary muscle group than the last added exercise 
				if (selectedExercisesForMuscleGroup.size()>0) {
					Exercise latestExerciseInList = selectedExercisesForMuscleGroup.get(selectedExercisesForMuscleGroup.size()-1);
					if (e.getInfo().getPrimaryMuscleGroup().equals(latestExerciseInList.getInfo().getPrimaryMuscleGroup())) //if same primary muscle group dont add
						addExercise=false;
				}
			}	
			if (addExercise && !selectedExercisesForMuscleGroup.contains(e))
				selectedExercisesForMuscleGroup.add(e);
		}
				
		return new GroupOfExercises(selectedExercisesForMuscleGroup,muscleGroup);	
	}
	

	
	//filters down exercises in database to make a list of only those with the same muscleGroup
	private List<Exercise> createFilteredExerciseListByMGFromDB(MuscleGroup muscleGroup, List<ExerciseInfo> db) {
		List<Exercise> filteredExercises = new ArrayList<Exercise>();
		
		List<ExerciseInfo> filteredExerciseInfo = new ArrayList<ExerciseInfo>(); 
		//depending on what hte workout MuscleGroup is, we need to either look at the exercise primaryMuscleGroup or the exercise generalMuscleGroup
		if (muscleGroup instanceof GeneralBodyPart) {			 
			filteredExerciseInfo = db.stream().filter(e -> e.getGeneralMuscleGroup().equals(muscleGroup.toString())).collect(Collectors.toList());
			
			if (muscleGroup.equals(GeneralBodyPart.UpperBody)) {	//have upper body include Arms exercises too		
				List<ExerciseInfo> arms = db.stream().filter(e -> e.getGeneralMuscleGroup().equals(GeneralBodyPart.Arms.toString())).collect(Collectors.toList());
				filteredExerciseInfo.addAll(arms);
			}
			if (muscleGroup.equals(GeneralBodyPart.FullBody)) {
				List<ExerciseInfo> upperBody = db.stream().filter(e -> e.getGeneralMuscleGroup().equals(GeneralBodyPart.UpperBody.toString())).collect(Collectors.toList());
				List<ExerciseInfo> lowerBody = db.stream().filter(e -> e.getGeneralMuscleGroup().equals(GeneralBodyPart.LowerBody.toString())).collect(Collectors.toList());
				filteredExerciseInfo.addAll(upperBody);
				filteredExerciseInfo.addAll(lowerBody);
			}
			if (muscleGroup.equals(GeneralBodyPart.Everything)) {
				filteredExerciseInfo = db;
				
			}
			
		}			
		else { // regular BodyPart - so grab primary muscle group 
			filteredExerciseInfo = db.stream().filter(e -> e.getPrimaryMuscleGroup().equals(muscleGroup.toString())).collect(Collectors.toList());	
		}
		//create list of Exercises rather than just exerciseInfo
		for (ExerciseInfo exerciseInfo : filteredExerciseInfo) {
			filteredExercises.add(new Exercise(exerciseInfo,new ExerciseFormat()));
		}
		return filteredExercises;
				
	}
	
	private boolean isValidWorkoutParamters() {
		// TODO Auto-generated method stub
		int numberOfMuscleGroupsInFocus = getFocus().getSizeOfFocus(); //should be 1, 2, or 3
		if (getNUM_TOTAL_EXERCISES() % numberOfMuscleGroupsInFocus != 0) {// NUM_TOTAL_EXERCISE should be 6
			System.out.println("Total Number of exercises not divisible by number of muscle groups");
			return false;
		}
		int numExercisesForEachMuscleGroup = getNUM_TOTAL_EXERCISES() / numberOfMuscleGroupsInFocus;	
		for (MuscleGroup m : getFocus().getMuscleGroups()) {
			if (!possibleToCreateSetOfExercisesForMG(numExercisesForEachMuscleGroup, m, exerciseDB))
				return false;
		}	
		//int numExercisesForEachMuscleGroup = getNUM_TOTAL_EXERCISES() / numberOfMuscleGroupsInFocus;	
		return true;
		
	}
	
	public boolean getIsValidParameters() {
		return isValidParameters;
	}
	
	

	
}




