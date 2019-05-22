

//NOT USING THIS. DELETE?
public enum BodyPart implements MuscleGroup {
	//these string values NEED to match exactly with the string values in the database
	Chest,Back,Shoulders,Biceps,Triceps,Forearms,Quads,Hamstrings,Glutes,Calves,Core;

	//Chest("Chest"), 
	//have constrcutor so that parameter is field strValue
	//getStringValue will just return the field rather than having to do a switch case
	
	//returned string values NEED to match with the string values in database!!!
	/*@Override
	public String getStringValue() {
		 switch(this){
         case Chest:
             return "Chest";
         case Back:
             return "Back";
         case Shoulders:
             return "Shoulders";
         case Biceps:
         	return "Biceps";
         case Triceps:
         	return "Triceps";
         case Forearms:
             return "";
         case Quads:
             return "";
         case Hamstrings:
             return "";
         case Glutes:
         	return "";
         case Calves:
         	return "";
         case Core:
        	 return "";
         default:
             return "No BodyPart";
     }
	}*/
	

}