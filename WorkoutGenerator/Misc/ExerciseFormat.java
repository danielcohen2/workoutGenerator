
public class ExerciseFormat {
	
	private int sets;
	private int[] repRange; 
	private int restTime; 
	
	public ExerciseFormat() {
		sets=0;
		repRange=new int[2];
		restTime=0;
	}
	
	public ExerciseFormat(int sets, int[] repRange, int restTime) {
		this.sets = sets;
		this.repRange = repRange;
		this.restTime = restTime;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public int[] getRepRange() {
		return repRange;
	}

	public void setRepRange(int rep) {
		this.repRange[0] = rep;
		this.repRange[1] = 0;
		
	}
	
	public void setRepRange(int repMin, int repMax) {
		this.repRange[0] = repMin;
		this.repRange[1] = repMax;
		
	}

	public int getRestTime() {
		return restTime;
	}

	public void setRestTime(int restTime) {
		this.restTime = restTime;
	}
	
	public String toString() {
		String reps = "";
		if (this.repRange[1] == 0)
			reps = "" + repRange[0];
		else
			reps = repRange[0] + "-" + repRange[1];
		return "sets= "+ this.sets + "|| reps= " + reps + " || rest= " + this.restTime + " mins";
	}
	

}
