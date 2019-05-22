import java.util.ArrayList;
import java.util.List;

public class Focus {
	
	private List<MuscleGroup> muscleGroups; 
	
	public Focus(MuscleGroup m) {	
		muscleGroups = new ArrayList<MuscleGroup>();
		muscleGroups.add(m);
		
	}
	
	public Focus(MuscleGroup m1, MuscleGroup m2) {
		this(m1);
		this.muscleGroups.add(m2);		
	}
	
	public Focus(MuscleGroup m1, MuscleGroup m2, MuscleGroup m3) {
		this(m1,m2);
		this.muscleGroups.add(m3);
	}

	public List<MuscleGroup> getMuscleGroups() {
		return muscleGroups;
	}

	public void setMuscleGroups(List<MuscleGroup> muscleGroups) {
		this.muscleGroups = muscleGroups;
	}
	
	public int getSizeOfFocus() {
		return muscleGroups.size();
	}
	
	public void addFocus(MuscleGroup muscleGroup) {
		muscleGroups.add(muscleGroup);
	}
	
	public String toString() {
		String muscleGroupsStr="Focus: ";
		for (int i=0; i<muscleGroups.size();i++) {
			muscleGroupsStr+=(muscleGroups.get(i).toString());
			if (i!=muscleGroups.size()-1)
				muscleGroupsStr+=", ";
		}
		/*for (MuscleGroup m : muscleGroups) {
			muscleGroupsStr+=("\n"+m.toString());
		}*/
		return muscleGroupsStr;
	}
	

}
