
public enum GeneralBodyPart implements MuscleGroup {
	//these string values NEED to match exactly with the string values in the database
	UpperBody, LowerBody, FullBody, Abs, Arms; //FullBodyCardioFocused;
	
	//have enum have constructor with name i.e (UpperBody("Upper body"), LowerBody("Lower Body"),)
	/*@Override
	    public String getStringValue(){
	        switch(this){
	            case UpperBody:
	                return "Upper Body";
	            case LowerBody:
	                return "Lower Body";
	            case FullBody:
	                return "Full Body";
	            case Abs:
	            	return "Abs";
	            case Arms:
	            	return "Arms";
	            default:
	                return "No GeneralBodyPart";
	        }
	    }
*/
}
