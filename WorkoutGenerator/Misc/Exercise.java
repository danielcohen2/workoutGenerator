
public class Exercise {
	
	private ExerciseInfo info;
	private ExerciseFormat format;
	
	public Exercise(ExerciseInfo info, ExerciseFormat format) {
		this.info = info;
		this.format = format;
	}
	
	public ExerciseInfo getInfo() {
		return info;
	}
	public void setInfo(ExerciseInfo info) {
		this.info = info;
	}
	public ExerciseFormat getFormat() {
		return format;
	}
	public void setFormat(ExerciseFormat format) {
		this.format = format;
	}
	
	public String toString() { 
		return getInfo().getFullName() + " (" + getInfo().getPrimaryMuscleGroup() +") || " + getFormat();
	}

	//some exercises in database are repeated because they're variations or they're listed twice because work different muscle groups 
	// So created equals and hashCode methods to ensure that when creating HashSet of exercises there are no duplicates 
	// (i.e barbell bench press and dumbell bench press are listed as short name = bench press, so when creating list you won't have 2 bench press exercises) 
	@Override
	public boolean equals(Object o) {
		Exercise e = (Exercise) o;
		return this.getInfo().getShortName().equals(e.getInfo().getShortName());
	}
		
	//just uses exerciseInfo shortName to ensure no duplicates in HashSet
	public int hashCode() {
		return this.getInfo().getShortName().hashCode();
	}

	
	

}
