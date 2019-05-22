import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExerciseDatabase implements AccessDatabase{
	
	private String path;
	private List<ExerciseInfo> exercises; //ArrayList<HashSet<Exercise>> exercises = new ArrayList<HashSet<Exercise>>(); 
	
	public ExerciseDatabase(String path) {
		this.setPath(path);
		this.setExercises(createListFromDatabase(path));
		
	}

	@Override
	public List<ExerciseInfo> createListFromDatabase(String path) {
		List<ExerciseInfo> exercises = new ArrayList<ExerciseInfo>();
		try {
			File file = new File(path);
			FileInputStream f = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(f);	
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			//Iterator<Row> rowIterator = sheet.iterator(); //wasn't working if one of the cell's data was empty? 
			int lastRow = sheet.getLastRowNum()+1; //zero index so need to add 1 to account for last row
			int rowNum = 1;
			while (rowNum < lastRow) {
				try { //if row is empty or doesn't have all cells filled in, skips row
					Row row = sheet.getRow(rowNum);					
					String fullName = row.getCell(0).getStringCellValue();
					String shortName = row.getCell(1).getStringCellValue();
					String primaryMuscleGroup = row.getCell(2).getStringCellValue();
					String generalMuscleGroup = row.getCell(3).getStringCellValue();
					//String favorite = row.getCell(4).getStringCellValue();
					//boolean isFavorite = (biUniStr.equals("yes") ? true : false);
					//String biUniStr = row.getCell(5).getStringCellValue();
					//boolean isBilateral = (biUniStr.equals("bi") ? true : false);

					ExerciseInfo exercise = new ExerciseInfo(fullName,shortName,primaryMuscleGroup,generalMuscleGroup); // isBilateral);
					exercises.add(exercise);
				}
				catch (Exception e) {
					//System.out.println("Ran into issue gathering data from database. Row is either empty or has cells in it that are empty. Row " + (rowNum+1) + " was skipped");
				}
				rowNum++;
			}
			workbook.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
		return exercises;

	}

	public List<ExerciseInfo> getExercises() {
		return exercises;
	}

	public void setExercises(List<ExerciseInfo> exercises) {
		this.exercises = exercises;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
		
}