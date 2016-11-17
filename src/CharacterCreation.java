/*
 	Matthew Kehoe
*/

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CharacterCreation extends JFrame
{
	private final int WINDOW_WIDTH = 600;
	private final int WINDOW_HEIGHT = 500;
	private final int MAX_POINTS = 100;
	private final int MIN_POINTS = 0;
	private JTextField selectedUserName;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu optionsMenu;
	private JMenuItem openChar;
	private JMenuItem saveChar;
	private JMenuItem resetAll;
	private JMenuItem exit;
	private JRadioButton male;
	private JRadioButton female;
	private ButtonGroup bgroup;
	private JComboBox userClassBox;
	private JPanel genderPanel;
	private JPanel classPanel;
	private JPanel userNamePanel;
	private JLabel userNameLabel;
	private JTextField selectedClassText;
	private JTextField selectedGenderText;
	private JPanel westPanel;
	private JPanel eastPanel;
	private JPanel sliderPanel;
	private JLabel pointsLabel;
	private JLabel intelligenceLabel;
	private JLabel dexterityLabel;
	private JLabel strengthLabel;
	private JLabel wisdomLabel;
	private JTextField pointsText;
	private JTextField intelligenceText;
	private JTextField dexterityText;
	private JTextField strengthText;
	private JTextField wisdomText;
	private JSlider pointsSlider;
	private JSlider intelligenceSlider;
	private JSlider dexteritySlider;
	private JSlider strengthSlider;
	private JSlider wisdomSlider;
	private JPanel pointsPanel;
	private JPanel pointsPanelCenter;
	private JPanel intelligencePanel;
	private JPanel intelligencePanelCenter;
	private JPanel dexterityPanel;
	private JPanel dexterityPanelCenter;
	private JPanel strengthPanel;
	private JPanel strengthPanelCenter;
	private JPanel wisdomPanel;
	private JPanel wisdomPanelCenter;
	
	// They have four classes available to choose from 
	private String[] classes = { "Warrior", "Barbarian",
								 "Mage", "Thief" };
	
	// Constructor
	public CharacterCreation()
	{
		setTitle("RPG Character Creation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		// Use a Grid Layout
		setLayout(new BorderLayout());
		
		// Build the User Name panel
		buildUserNamePanel();

		// Build the Gender panel
		buildGenderPanel();
		
		// Build the Class panels
		buildClassPanel();
		
		// Build the Slider panel
		buildSliderPanel();
		
		// Build the Menu bar
		buildMenuBar();
		
		// Make a west panel with a grid layout of 3 rows and 1 column
		westPanel = new JPanel();
		westPanel.setLayout(new GridLayout(3,1));
		
		// Add the panels to the west panel
		westPanel.add(userNamePanel);
		westPanel.add(genderPanel);
		westPanel.add(classPanel);
		
		// Make an east panel with a grid layout of 5 rows and 1 column
		eastPanel = new JPanel();
		eastPanel.add(sliderPanel);
		
		// Add the panels to the frame
		add(westPanel, BorderLayout.WEST);
		add(eastPanel, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	// Build the menu bar at the top of the screen.
	public void buildMenuBar()
	{
		menuBar = new JMenuBar();
		
		// Create the two menus for file and options
		buildFileMenu();
		buildOptionsMenu();
		
		// Add the file menu and option menu to the menu bar
		menuBar.add(fileMenu);
		menuBar.add(optionsMenu);
		
		// Set the window's menu bar
		setJMenuBar(menuBar);
	}
	
	// Build the File menu from the menu bar.
	public void buildFileMenu()
	{
		fileMenu = new JMenu("File");
		
		openChar = new JMenuItem("Open Character");
		openChar.addActionListener(new OpenCharListener());
		
		saveChar = new JMenuItem("Save Character");
		saveChar.addActionListener(new SaveCharListener());
		
		// Add the open character and save character to the File menu
		fileMenu.add(openChar);
		fileMenu.add(saveChar);
	}
	
	// Build the user name panel
	public void buildUserNamePanel()
	{
		userNamePanel = new JPanel();
		// Add a border to specify this is the user name section
		userNamePanel.setBorder(BorderFactory.createTitledBorder("User Name"));
		
		// Specify the text field for the user name
		selectedUserName = new JTextField(10);
		userNameLabel = new JLabel("Enter your user name: ");
		
		// Restrict the user name to 1 and 10 characters
		selectedUserName.addActionListener(new TextFieldListener());

		// Add the label and the text field to the panel
		userNamePanel.add(userNameLabel);
		userNamePanel.add(selectedUserName);
	}
	
	// Build the Gender panel using radio buttons for male or female
	public void buildGenderPanel()
	{
		genderPanel = new JPanel();
		
		male = new JRadioButton("Male", true);
		female = new JRadioButton("Female");
		
		// Add these to a button group
		bgroup = new ButtonGroup();
		bgroup.add(male);
		bgroup.add(female);
		
		// Register the buttons with an action listener
		male.addActionListener(new GenderSelectedListener());
		female.addActionListener(new GenderSelectedListener());
		
		// Add a border to specify this is the gender section
		genderPanel.setBorder(BorderFactory.createTitledBorder("Gender"));
		
		// Add the Selected Gender Text
		selectedGenderText = new JTextField("Male", 5);
		selectedGenderText.setEditable(false);
		
		// Add the male and female radio buttons to the panel
		genderPanel.add(male);
		genderPanel.add(female);
		genderPanel.add(selectedGenderText);
	}
	
	// Build the Class panel for the four different options
	public void buildClassPanel()
	{
		classPanel = new JPanel();
		
		// Add a border to specify this is the class section
		classPanel.setBorder(BorderFactory.createTitledBorder("Class"));
		
		// Create a combo box for the class
		userClassBox = new JComboBox(classes);
		
		// Register the userClass with an action listener
		userClassBox.addActionListener(new ClassSelectedListener());
		
		// Add the Selected Class Text
		selectedClassText = new JTextField("Warrior", 7);
		selectedClassText.setEditable(false);
		
		// Add the userClass combo box to the panel
		classPanel.add(userClassBox);
		classPanel.add(selectedClassText);
	}
	
	// Build the Options menu from the menu bar
	public void buildOptionsMenu()
	{
		optionsMenu = new JMenu("Options");
		
		resetAll = new JMenuItem("Reset All");
		// Create an action listener to reset all characters
		resetAll.addActionListener(new ResetAllListener());
		
		exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitListener());
		
		// Add reset all and exit to the Options menu
		optionsMenu.add(resetAll);
		optionsMenu.add(exit);
	}
	
	// Build the Slider panel
	public void buildSliderPanel()
	{		
		// Make all the sliders
		pointsSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
		pointsSlider.setMajorTickSpacing(10); // Major tick every 10
		pointsSlider.setMinorTickSpacing(1); // Minor tick every 1
		pointsSlider.setPaintTicks(true); // Display the tick marks
		pointsSlider.setPaintLabels(true); // Displays the numbers
		pointsSlider.addChangeListener(new PointsSliderListener());
		
		intelligenceSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		intelligenceSlider.setMajorTickSpacing(10); // Major tick every 10
		intelligenceSlider.setMinorTickSpacing(1); // Minor tick every 1
		intelligenceSlider.setPaintTicks(true); // Display the tick marks
		intelligenceSlider.setPaintLabels(true); // Displays the numbers
		intelligenceSlider.addChangeListener(new PointsSliderListener());
		
		dexteritySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		dexteritySlider.setMajorTickSpacing(10); // Major tick every 10
		dexteritySlider.setMinorTickSpacing(1); // Minor tick every 1
		dexteritySlider.setPaintTicks(true); // Display the tick marks
		dexteritySlider.setPaintLabels(true); // Displays the numbers
		dexteritySlider.addChangeListener(new PointsSliderListener());
		
		strengthSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		strengthSlider.setMajorTickSpacing(10); // Major tick every 10
		strengthSlider.setMinorTickSpacing(1); // Minor tick every 1
		strengthSlider.setPaintTicks(true); // Display the tick marks
		strengthSlider.setPaintLabels(true); // Displays the numbers
		strengthSlider.addChangeListener(new PointsSliderListener());
		
		wisdomSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		wisdomSlider.setMajorTickSpacing(10); // Major tick every 10
		wisdomSlider.setMinorTickSpacing(1); // Minor tick every 1
		wisdomSlider.setPaintTicks(true); // Display the tick marks
		wisdomSlider.setPaintLabels(true); // Displays the numbers
		wisdomSlider.addChangeListener(new PointsSliderListener());
		
		// Add the sliders and text fields individually to the corresponding panel
		buildInternalSliderPanels();
		
		// Add the Sliders to the panel
		sliderPanel = new JPanel();
		sliderPanel.setBorder(BorderFactory.createTitledBorder("Attributes"));
		sliderPanel.setLayout(new GridLayout(5,1));
		
		sliderPanel.add(pointsPanel);
		sliderPanel.add(intelligencePanel);
		sliderPanel.add(dexterityPanel);
		sliderPanel.add(strengthPanel);
		sliderPanel.add(wisdomPanel);
	}
	
	// Build the internal panels inside the Slider panel
	private void buildInternalSliderPanels()
	{
		// Make the panels, labels and text fields for the Points panel
		pointsPanel = new JPanel();
		pointsPanel.setLayout(new BorderLayout());
		pointsLabel = new JLabel("Points Left: ");
		pointsText = new JTextField();
		pointsText.setText(Integer.toString(pointsSlider.getValue()));
		pointsText.setEditable(false);
		
		pointsPanelCenter = new JPanel();
		pointsPanelCenter.setLayout(new GridLayout(1,2));
		pointsPanelCenter.add(pointsLabel);
		pointsPanelCenter.add(pointsText);
		
		// Add these to the points panel
		pointsPanel.add(pointsPanelCenter, BorderLayout.CENTER);
		pointsPanel.add(pointsSlider, BorderLayout.SOUTH);
		
		// Make the panels, labels and text fields for the Intelligence panel
		intelligencePanel = new JPanel();
		intelligencePanel.setLayout(new BorderLayout());
		intelligenceLabel = new JLabel("Current Intelligence: ");
		intelligenceText = new JTextField();
		intelligenceText.setText(Integer.toString(intelligenceSlider.getValue()));
		intelligenceText.setEditable(false);
		
		intelligencePanelCenter = new JPanel();
		intelligencePanelCenter.setLayout(new GridLayout(1,2));
		intelligencePanelCenter.add(intelligenceLabel);
		intelligencePanelCenter.add(intelligenceText);
		
		// Add these to the Intelligence panel
		intelligencePanel.add(intelligencePanelCenter, BorderLayout.CENTER);
		intelligencePanel.add(intelligenceSlider, BorderLayout.SOUTH);
		
		// Make the panels, labels and text fields for the Dexterity panel
		dexterityPanel = new JPanel();
		dexterityPanel.setLayout(new BorderLayout());
		dexterityLabel = new JLabel("Current Dexterity: ");
		dexterityText = new JTextField();
		dexterityText.setText(Integer.toString(dexteritySlider.getValue()));
		dexterityText.setEditable(false);
		
		dexterityPanelCenter = new JPanel();
		dexterityPanelCenter.setLayout(new GridLayout(1,2));
		dexterityPanelCenter.add(dexterityLabel);
		dexterityPanelCenter.add(dexterityText);
		
		// Add these to the Dexterity panel
		dexterityPanel.add(dexterityPanelCenter, BorderLayout.CENTER);
		dexterityPanel.add(dexteritySlider, BorderLayout.SOUTH);
		
		// Make the panels, labels and text fields for the Strength panel
		strengthPanel = new JPanel();
		strengthPanel.setLayout(new BorderLayout());
		strengthLabel = new JLabel("Current Strength: ");
		strengthText = new JTextField();
		strengthText.setText(Integer.toString(strengthSlider.getValue()));
		strengthText.setEditable(false);
		
		strengthPanelCenter = new JPanel();
		strengthPanelCenter.setLayout(new GridLayout(1,2));
		strengthPanelCenter.add(strengthLabel);
		strengthPanelCenter.add(strengthText);
		
		// Add these to the Strength panel
		strengthPanel.add(strengthPanelCenter, BorderLayout.CENTER);
		strengthPanel.add(strengthSlider, BorderLayout.SOUTH);
		
		// Make the panels, labels and text fields for the Wisdom panel
		wisdomPanel = new JPanel();
		wisdomPanel.setLayout(new BorderLayout());
		wisdomLabel = new JLabel("Current Wisdom: ");
		wisdomText = new JTextField();
		wisdomText.setText(Integer.toString(wisdomSlider.getValue()));
		wisdomText.setEditable(false);
		
		wisdomPanelCenter = new JPanel();
		wisdomPanelCenter.setLayout(new GridLayout(1,2));
		wisdomPanelCenter.add(wisdomLabel);
		wisdomPanelCenter.add(wisdomText);
		
		// Add these to the Wisdom panel
		wisdomPanel.add(wisdomPanelCenter, BorderLayout.CENTER);
		wisdomPanel.add(wisdomSlider, BorderLayout.SOUTH);
	}
	
	// Private inner class to handle the event when the user clicks
	// the open character button
	private class OpenCharListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PLAYER FILES", "player", "player");
			fileChooser.setFileFilter(filter);
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.setDialogTitle("Open a file in the format of character_name.player");
			int status = fileChooser.showOpenDialog(null);
			if (status == JFileChooser.CANCEL_OPTION)
			{
				JOptionPane.showMessageDialog(null, "No open action was performed.");
			}
			else if (status == JFileChooser.ERROR_OPTION)
			{
				JOptionPane.showMessageDialog(null, "No open action was performed.");
			}
			else if (status == JFileChooser.APPROVE_OPTION)
			{
				File selectedFile = fileChooser.getSelectedFile();
				String filename = selectedFile.getPath();
				Scanner scan = null;
				try 
				{
					scan = new Scanner(selectedFile);
				} 
				catch (FileNotFoundException e1) 
				{
				
					JOptionPane.showMessageDialog(null, filename + " was not found.");
				}
				// Set all the data for the character opened!
				try
				{
					while (scan.hasNext())
					{			
						selectedUserName.setText(scan.nextLine());
						{
							String genderInput = scan.nextLine();
							if(genderInput.equals("Male"))
							{
								male.setSelected(true);
							}
							else if((genderInput.equals("Female")))
							{
								female.setSelected(true);
							}
						}
						selectedGenderText.setText(scan.nextLine());
						{
							String classInput = scan.nextLine();
							if(classInput.equals("Warrior"))
							{
								userClassBox.setSelectedIndex(0);
							}
							else if(classInput.equals("Barbarian"))
							{
								userClassBox.setSelectedIndex(1);
							}
							else if(classInput.equals("Mage"))
							{
								userClassBox.setSelectedIndex(2);
							}
							else if(classInput.equals("Thief"))
							{
								userClassBox.setSelectedIndex(3);
							}
						}
						selectedClassText.setText(scan.nextLine());
						pointsText.setText(scan.nextLine());
						pointsSlider.setValue(Integer.parseInt(scan.nextLine()));
						intelligenceText.setText(scan.nextLine());
						intelligenceSlider.setValue(Integer.parseInt(scan.nextLine()));
						dexterityText.setText(scan.nextLine());
						dexteritySlider.setValue(Integer.parseInt(scan.nextLine()));
						strengthText.setText(scan.nextLine());
						strengthSlider.setValue(Integer.parseInt(scan.nextLine()));
						wisdomText.setText(scan.nextLine());
						wisdomSlider.setValue(Integer.parseInt(scan.nextLine()));
					}
				}
				catch (Exception e4)
				{
					JOptionPane.showMessageDialog(null, "Please check the " + 
													"player input file.");
				}
				JOptionPane.showMessageDialog(null, "You opened " + filename);
			}
		}
	}
	
	// Private inner class to handle the event when the user clicks
	// the save character button
	private class SaveCharListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("PLAYER FILES", "player", "player");
			fileChooser.setFileFilter(filter);
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.setName(selectedUserName.getText());
			fileChooser.setDialogTitle("Save a file in the format of character_name.player");
			int status = fileChooser.showSaveDialog(null);
			int textLength = selectedUserName.getText().length();
			File selectedFile = fileChooser.getSelectedFile();
			if (status == JFileChooser.CANCEL_OPTION)
			{
				JOptionPane.showMessageDialog(null, "No save action was performed.");
			}
			else if (status == JFileChooser.ERROR_OPTION)
			{
				JOptionPane.showMessageDialog(null, "No save action was performed.");
			}
			else if(textLength <= 0 || textLength > 10)
			{
				selectedUserName.setText("");
				JOptionPane.showMessageDialog(null, "The length of the " +
								"user name must be between 1 and 10 characters. Please " 
								+ "adjust the user name field to match a name between "
								+ "one and ten characters.");
			}
			else if(!selectedFile.getName().equals(selectedUserName.getText()) &&
					!(selectedFile.getName().equals(selectedUserName.getText() + ".player")))
			{
				JOptionPane.showMessageDialog(null, "You must save the " +
						"file as " + selectedUserName.getText() + ".player" );
			}
			JDialog.setDefaultLookAndFeelDecorated(true);
			// Must have the correct user name format!

			if(textLength > 0 && textLength <= 10 && status == JFileChooser.APPROVE_OPTION
					&& (selectedFile.getName().equals(selectedUserName.getText())
					|| (selectedFile.getName().equals(selectedUserName.getText() + ".player"))))
			{
			int confirmSave = JOptionPane.showConfirmDialog(null, "Are you sure you want to "
						+ "save this character?", "Confirm",JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE);
			if (confirmSave == JOptionPane.NO_OPTION) 
			{
				JOptionPane.showMessageDialog(null, "No save action was performed.");
			} 
			else if (confirmSave == JOptionPane.CLOSED_OPTION) 
			{
				JOptionPane.showMessageDialog(null, "You did not save " + selectedFile + ".player");
			}  
			else if (status == JFileChooser.APPROVE_OPTION && confirmSave == JOptionPane.YES_OPTION)
			{
				// Make a PrintWriter object to save data
				try 
				{
					if(!selectedFile.getName().toLowerCase().endsWith(".player"))
					{
						selectedFile = new File(selectedFile.getAbsolutePath() + ".player"); 
					}
					PrintWriter outputFile = new PrintWriter(selectedFile);
					outputFile.println(selectedUserName.getText());
					outputFile.println(selectedGenderText.getText());
					outputFile.println(selectedGenderText.getText());
					outputFile.println(selectedClassText.getText());
					outputFile.println(selectedClassText.getText());
					outputFile.println(pointsText.getText());
					outputFile.println(pointsText.getText());
					outputFile.println(intelligenceText.getText());
					outputFile.println(intelligenceText.getText());
					outputFile.println(dexterityText.getText());
					outputFile.println(dexterityText.getText());
					outputFile.println(strengthText.getText());
					outputFile.println(strengthText.getText());
					outputFile.println(wisdomText.getText());
					outputFile.println(wisdomText.getText());
					outputFile.close();
					JOptionPane.showMessageDialog(null, "You saved " + selectedFile);
				} 
				catch (FileNotFoundException e2) 
				{
					JOptionPane.showMessageDialog(null, selectedUserName.getText() + 
							".player was not found.");
				}
			}
			}
		}
	}
	
	// Private inner class to handle the event when the user clicks
	// the reset all button
	private class ResetAllListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{		
			selectedUserName.setText("");
			male.setSelected(true);
		    female.setSelected(false);
		    selectedGenderText.setText("Male");
		    userClassBox.setSelectedIndex(0);
		    selectedClassText.setText("Warrior");
		    pointsText.setText(Integer.toString(MAX_POINTS));
			pointsSlider.setValue(MAX_POINTS);
			intelligenceText.setText(Integer.toString(MIN_POINTS));
			intelligenceSlider.setValue(MIN_POINTS);
			dexterityText.setText(Integer.toString(MIN_POINTS));
			dexteritySlider.setValue(MIN_POINTS);
			strengthText.setText(Integer.toString(MIN_POINTS));
			strengthSlider.setValue(MIN_POINTS);
			wisdomText.setText(Integer.toString(MIN_POINTS));
			wisdomSlider.setValue(MIN_POINTS);	
		}
	}
	
	// Private inner class to handle the event when the user types
	// something into the user name text field.
	private class TextFieldListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			int textLength = selectedUserName.getText().length();
			if(textLength > 0 && textLength <= 10)
			{
				selectedUserName.getText();
			}
			else
			{
				selectedUserName.setText("");
				JOptionPane.showMessageDialog(null, "The length of the " +
								"user name must be between 1 and 10 characters.");
			}
		}
	}
	
	// Private inner class to handle the event when the user clicks
	// the exit button
	private class ExitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			System.exit(0);
		}
	}
	
	// Private inner class to handle the event when the user
	// selects a class inside the combo box
	private class ClassSelectedListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			// Get the class which the user selected
			String selectedClass = 	
					(String) userClassBox.getSelectedItem();
			
			// Display the class selected inside the text field
			selectedClassText.setText(selectedClass);
		}
	}
	
	// Private inner class to handle the event when the user
	// selects a gender inside the button group
	private class GenderSelectedListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if (male.isSelected())
				selectedGenderText.setText("Male");
			else
				selectedGenderText.setText("Female");
		}
	}
	
	// Private inner class to handle the event when the user
	// moves the sliders
	private class PointsSliderListener implements ChangeListener
	{
		public void stateChanged(ChangeEvent e) 
		{
			// Formula to find the total points
			int intelligenceVal = intelligenceSlider.getValue();
			int dexterityVal = dexteritySlider.getValue();
			int strengthVal = strengthSlider.getValue();
			int wisdomVal = wisdomSlider.getValue();
			int totalPoints = intelligenceVal + dexterityVal + strengthVal + wisdomVal;
			int pointsLeft = MAX_POINTS - totalPoints;
			int totalPointsNotInt = dexterityVal + strengthVal + wisdomVal;
			int totalPointsNotDex = intelligenceVal + strengthVal + wisdomVal;
			int totalPointsNotStr = intelligenceVal + dexterityVal + wisdomVal;
			int totalPointsNotWsd = intelligenceVal + dexterityVal + strengthVal;
			
			// Subtract 100 minus the totalPoints
			// Display this value inside the Text Field and the slider
			// This value cannot go below 0
			if (pointsLeft >= 0)
			{
				pointsSlider.setValue(pointsLeft);
				pointsText.setText(Integer.toString(pointsLeft));
				intelligenceSlider.setValue(intelligenceVal);
				intelligenceText.setText(Integer.toString(intelligenceVal));
				dexteritySlider.setValue(dexterityVal);
				dexterityText.setText(Integer.toString(dexterityVal));
				strengthSlider.setValue(strengthVal);
				strengthText.setText(Integer.toString(strengthVal));
				wisdomSlider.setValue(wisdomVal);
				wisdomText.setText(Integer.toString(wisdomVal));
			}
			else if (totalPoints > 100 && openChar.equals(false))
			{
				pointsSlider.setValue(MIN_POINTS);
				pointsText.setText(Integer.toString(MIN_POINTS));
					
				if(e.getSource() == intelligenceSlider)
				{
					intelligenceSlider.setValue(MAX_POINTS - totalPointsNotInt);
					intelligenceText.setText(Integer.toString(MAX_POINTS - totalPointsNotInt));
				}
				if(e.getSource() == dexteritySlider)
				{
					dexteritySlider.setValue(MAX_POINTS - totalPointsNotDex);
					dexterityText.setText(Integer.toString(MAX_POINTS - totalPointsNotDex));
				}
				if(e.getSource() == strengthSlider)
				{
					strengthSlider.setValue(MAX_POINTS - totalPointsNotStr);
					strengthText.setText(Integer.toString(MAX_POINTS - totalPointsNotStr));
				}
				if(e.getSource() == wisdomSlider)
				{
					wisdomSlider.setValue(MAX_POINTS - totalPointsNotWsd);
					wisdomText.setText(Integer.toString(MAX_POINTS - totalPointsNotWsd));
				}
			}
		}
	}
	
	// Main Method
	// Creates an instance of the CharacterCreation class
	public static void main(String[] args)
	{
		new CharacterCreation();
	}
}
