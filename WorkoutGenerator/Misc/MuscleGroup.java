public interface MuscleGroup {
	
	//String getStringValue();
	//MuscleGroup getMuscleGroupValue(String muscleGroupStr);
	
	static MuscleGroup getMuscleGroupValue(String muscleGroupStr) {
		for (MuscleGroup m : GeneralBodyPart.values()) {
			if (m.toString().equals(muscleGroupStr))
				return m;
		}
		for (MuscleGroup m : BodyPart.values()) {
			if (m.toString().equals(muscleGroupStr))
				return m;
		}
		return null;
	} 
}