import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class UI extends JPanel{
	
	private JFrame frame;
	private List<MuscleGroup> mgSelected;
	private List<JCheckBox> checkBoxes;
	private JTextArea workoutArea;
	
	private WorkoutGenerator wg;
	
	public UI() {
		this.mgSelected = new ArrayList<MuscleGroup>();	
		createUserInterface();	
	}
	
	private void createUserInterface() {
		frame = new JFrame("Workout Generator");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//frame.setLayout(new BorderLayout());
		//frame.setLayout(new FlowLayout());
		frame.setLayout(new GridLayout(2,1));
		frame.setBackground(Color.WHITE);		
		frame.setSize(700, 500);
		
		createComponents();

		//frame.getContentPane().add(this,BorderLayout.CENTER); //UI is a JPanel - we set the size in the constructor 	
		//frame.pack();		
		frame.setLocationByPlatform(true);
		frame.setVisible(true);	
	}
	
	private void createComponents() { 	
		createTopRow();
		createWorkoutArea();	
	}
	
	private void createTopRow() {
		JPanel topRow = new JPanel();
		topRow.setLayout(new FlowLayout());	
		createTopLeftComponents(topRow); 
		createTopRightComponents(topRow);		
		frame.add(topRow);		
	}
	
	private void createTopLeftComponents(JPanel topRow) {	
		JPanel topLeft = new JPanel();
		topLeft.setLayout(new BoxLayout(topLeft, BoxLayout.Y_AXIS));
		JLabel muscleGroupsTitle = new JLabel("Muscle Groups: (Select up to 3)");
		muscleGroupsTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		topLeft.add(muscleGroupsTitle);
		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setLayout(new GridLayout(0,2));		
		checkBoxes = createCheckBoxesAndAddToJPanel(checkBoxPanel);		
		topLeft.add(checkBoxPanel);
		topRow.add(topLeft);
	}
	
	private void createTopRightComponents(JPanel topRow) {
		JPanel topRight = new JPanel();
		topRight.setLayout(new BoxLayout(topRight, BoxLayout.Y_AXIS));
		JLabel typeComboBoxTitle = new JLabel("Workout Type:");
		topRight.add(typeComboBoxTitle);
		JComboBox workoutTypeOptionsCB = new JComboBox(Type.values());
		topRight.add(workoutTypeOptionsCB);
		
		JButton generateWorkoutButton = new JButton("Generate Workout!");
		generateWorkoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				workoutArea.setText("");
				updateMGSelected();
				List<MuscleGroup> muscleGroups = mgSelected;
				Type type = (Type) workoutTypeOptionsCB.getSelectedItem();
				if (muscleGroups.size()==0)
					JOptionPane.showMessageDialog(frame, "Need to Select at least 1 Muscle Group");
				else {
					wg = new WorkoutGenerator(new Focus(muscleGroups), type);
					String workoutText;
					if (!wg.getWorkout().getIsValidParameters()) {
						workoutText="";
						JOptionPane.showMessageDialog(frame, "Please select different combination of Muscle Groups (not enough unique exercises to generate workout)");
					}
					else {
						workoutText = wg.toString();
						workoutArea.setText(workoutText);
						resizeUI();
					}
						
				}			
			}
		});
		topRight.add(generateWorkoutButton);		
		topRow.add(topRight);
	}
	
	private void createWorkoutArea() {
		JPanel bottomHalf= new JPanel();
		workoutArea = new JTextArea();
		bottomHalf.add(workoutArea);
		frame.add(bottomHalf);
	}


	private List<JCheckBox> createCheckBoxesAndAddToJPanel(JPanel panel) {
		List<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
		for (MuscleGroup m : createArrayOfAllMuscleGroups()) {
			JCheckBox checkBox = new JCheckBox(m.toString()); //problem here - checkbox value is string not MG
			checkBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (moreThanThreeMGSelected(checkBoxes)) {
						checkBox.doClick(); //deselects the box
						JOptionPane.showMessageDialog(frame, "Only have up to 3 muscle groups selected");	
					}			
				}
			});
			checkBoxes.add(checkBox);
			panel.add(checkBox);
		}
		return checkBoxes;	
	}
	
	private void updateMGSelected() {
		mgSelected.clear();
		for (JCheckBox checkBox : checkBoxes) {
			if (checkBox.isSelected()) {
				MuscleGroup m = MuscleGroup.getMuscleGroupValue(checkBox.getText());
				mgSelected.add(m);
			}
		}
	}
	
	private boolean moreThanThreeMGSelected(List<JCheckBox> checkBoxes) {
		updateMGSelected();
		return (mgSelected.size() > 3);
	}

	private MuscleGroup[] createArrayOfAllMuscleGroups() {
		MuscleGroup[] bodyParts = BodyPart.values();
		MuscleGroup[] generalBodyParts = GeneralBodyPart.values();
		MuscleGroup[] muscleGroups = new MuscleGroup[bodyParts.length + generalBodyParts.length];
		int count=0;
		while (count < muscleGroups.length) {
			for (MuscleGroup m : bodyParts) {
				muscleGroups[count] = m;
				count++;
			}
			for (MuscleGroup m : generalBodyParts) {
				muscleGroups[count] = m;
				count++;
			}
		}
		return muscleGroups;
	}
	
	//if click generate workout button, resizes frame to fit the workout
	private void resizeUI() {
		frame.pack();
		
	}


}
