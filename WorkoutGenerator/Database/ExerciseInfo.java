import java.util.HashSet;
import java.util.Set;

public class ExerciseInfo {
	
	private String fullName;
	private String shortName; // way of grouping similar exercises - use this to avoid duplicates in HashSets (see equals, hashcode method below for more details) 
	private String primaryMuscleGroup;
	private String generalMuscleGroup; 
	//private Boolean isBilateral; //if false then it is uni-lateral

	
	public ExerciseInfo(String fullName, String shortName, String primaryMuscleGroup, String generalMuscleGroup) {
		this.fullName = fullName;
		this.shortName = shortName;
		this.setPrimaryMuscleGroup(primaryMuscleGroup);
		this.generalMuscleGroup = generalMuscleGroup;
		
		
	}
	
	/*public ExerciseInfo2(String fullName, String shortName, String primaryMuscleGroup, String generalMuscleGroup,Boolean isBilateral) {
		this.fullName = fullName;
		this.shortName = shortName;
		this.setPrimaryMuscleGroup(primaryMuscleGroup);
		this.generalMuscleGroup = generalMuscleGroup;
		this.isBilateral = isBilateral;
		
	}*/
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public String getGeneralMuscleGroup() {
		return generalMuscleGroup;
	}

	public void setGeneralMuscleGroup(String generalMuscleGroup) {
		this.generalMuscleGroup = generalMuscleGroup;
	}

	/*public Boolean getIsBilateral() {
		return isBilateral;
	}

	public void setIsBilateral(Boolean isBilateral) {
		this.isBilateral = isBilateral;
	}
*/
	public String toString() {
		return fullName+"-("+primaryMuscleGroup+")";
	}

	public String getPrimaryMuscleGroup() {
		return primaryMuscleGroup;
	}

	public void setPrimaryMuscleGroup(String primaryMuscleGroup) {
		this.primaryMuscleGroup = primaryMuscleGroup;
	}

}
