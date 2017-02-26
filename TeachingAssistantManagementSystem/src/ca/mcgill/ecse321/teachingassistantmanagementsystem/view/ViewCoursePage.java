package ca.mcgill.ecse321.teachingassistantmanagementsystem.view;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.List;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.teachingassistantmanagementsystem.application.TeachingAssistantManagementSystem;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.controller.*;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.*;

public class ViewCoursePage extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2012446709379049017L;

	private JLabel errorMessage;
	private static Department dpt;
	private String error = null;
	private List applicants = new List();
	private JLabel dropdownLabel;
	private JComboBox<String> courseDropdown;
	private JLabel idLabel;
	private JLabel idText;
	private JLabel creditLabel;
	private JLabel creditText;
	private JLabel taJobLabel;
	private JLabel taJobText;
	private JLabel graderJobLabel;
	private JLabel graderJobText;
	private JButton getInfoButton;
	private JLabel taHourLabel;
	private JTextField taHourText;
	private JLabel graderHourLabel;
	private JTextField graderHourText;
	private JLabel creditLabel2;
	private JTextField creditText2;
	private JLabel courseIDLabel;
	private JTextField courseIDText;
	private JLabel budgetLabel;
	private JTextField budgetText;
	private JLabel studentsEnrolledLabel;
	private JTextField studentsEnrolledText;
	private JLabel taCapacityLabel;
	private JTextField taCapacityText;
	private JLabel graderCapacityLabel;
	private JTextField graderCapacityText;
	private JButton createCourseButton;
	private JButton addJobToList;
	private JButton applyToJobs;
	private JLabel mcgillIDLabel;
	private JTextField mcgillIDText;
	
	public ViewCoursePage(Department dpt)
	{
		this.dpt = dpt;
		initComponents();
		refreshdata();
	}
	
	
	private void initComponents() {
	    // elements for error message
	    errorMessage = new JLabel();
	    errorMessage.setForeground(Color.RED);
	    
	    // inits
	    mcgillIDLabel = new JLabel();
	    mcgillIDText = new JTextField();
	    addJobToList = new JButton();
	    applyToJobs = new JButton();
		dropdownLabel = new JLabel();
		courseDropdown = new JComboBox<String>(new String[0]);
		idLabel = new JLabel();
		idText = new JLabel();
		creditLabel = new JLabel();
		creditText = new JLabel();
		taJobLabel = new JLabel();
		taJobText = new JLabel();
		graderJobLabel = new JLabel();
		graderJobText = new JLabel();
		getInfoButton = new JButton();
		taHourLabel = new JLabel();
		taHourText = new JTextField();
		graderHourLabel = new JLabel();
		graderHourText = new JTextField();
		creditLabel2 = new JLabel();
		creditText2= new JTextField();
		courseIDLabel = new JLabel();
		courseIDText = new JTextField();
		budgetLabel = new JLabel();
		budgetText = new JTextField();
		studentsEnrolledLabel = new JLabel();
		studentsEnrolledText = new JTextField();
		taCapacityLabel = new JLabel();
		taCapacityText = new JTextField();
		graderCapacityLabel = new JLabel();
		graderCapacityText = new JTextField();
		createCourseButton = new JButton();
		
	    
	    // global settings and listeners
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("Event Registration");
	    
	    // default text
	    dropdownLabel.setText("Course: " );
	    idLabel.setText("ID: " );
		idText.setText("--");
		creditLabel.setText("Credits: " );
		creditText.setText("--");
		taJobLabel.setText("TA Jobs: " );
		taJobText.setText("--");
		graderJobLabel.setText("Grader Jobs: " );
		graderJobText.setText("--");
		getInfoButton.setText("Get course info");
		errorMessage.setText("--");
		taHourLabel.setText("TA Work hours:");
		taHourText.setText("");
		graderHourLabel.setText("Grader Work hours:");
		graderHourText.setText("");
		creditLabel2.setText("Course credits:");
		creditText2.setText("");
		courseIDLabel.setText("Course ID:");
		courseIDText.setText("");
		budgetLabel.setText("Course budget:");
		budgetText.setText("");
		studentsEnrolledLabel.setText("Students Enrolled:");
		studentsEnrolledText.setText("");
		taCapacityLabel.setText("Ta Job Capacity:");
		taCapacityText.setText("");
		graderCapacityLabel.setText("Grader Job Capacity:");
		graderCapacityText.setText("");
		createCourseButton.setText("Create Course");
		addJobToList.setText("Add job");
		applyToJobs.setText("Apply to jobs");
		mcgillIDLabel.setText("Applicant ID:");
		mcgillIDText.setText("");
		
		//This will cause a bug for now since there are no courses to show
		
	    // layout		
		JPanel panel = new JPanel();
		panel.add(errorMessage);
		panel.add(dropdownLabel);
		panel.add(courseDropdown);
		panel.add(idLabel);
		panel.add(idText);
		panel.add(creditLabel);
		panel.add(creditText);
		panel.add(mcgillIDLabel);
		panel.add(mcgillIDText);
		panel.add(taJobLabel);
		panel.add(taJobText);
		panel.add(graderJobLabel);
		panel.add(graderJobText);
		panel.add(getInfoButton);
		panel.add(taHourLabel);
		panel.add(taHourText);
		panel.add(graderHourLabel);
		panel.add(graderHourText);
		panel.add(creditLabel2);
		panel.add(creditText2);
		panel.add(courseIDLabel);
		panel.add(courseIDText);
		panel.add(budgetLabel);
		panel.add(budgetText);
		panel.add(studentsEnrolledLabel);
		panel.add(studentsEnrolledText);
		panel.add(taCapacityLabel);
		panel.add(taCapacityText);
		panel.add(graderCapacityLabel);
		panel.add(graderCapacityText);
		panel.add(createCourseButton);
		panel.add(applyToJobs);
		panel.add(addJobToList);
		getContentPane().add(panel);
		
		GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateContainerGaps(true);
        panel.setLayout(layout);
        
        GroupLayout.Group hg1 = layout.createParallelGroup();
        GroupLayout.Group hg2 = layout.createParallelGroup();
        GroupLayout.Group hg3 = layout.createParallelGroup();
        GroupLayout.Group hg4 = layout.createParallelGroup();

        GroupLayout.Group vg0 = layout.createParallelGroup();
        GroupLayout.Group vg1 = layout.createParallelGroup();
        GroupLayout.Group vg2 = layout.createParallelGroup();
        GroupLayout.Group vg3 = layout.createParallelGroup();
        GroupLayout.Group vg4 = layout.createParallelGroup();
        GroupLayout.Group vg5 = layout.createParallelGroup();
        GroupLayout.Group vg6 = layout.createParallelGroup();
        GroupLayout.Group vg7 = layout.createParallelGroup();
        GroupLayout.Group vg8 = layout.createParallelGroup();
        GroupLayout.Group vg9 = layout.createParallelGroup();
        
        hg1.addComponent(errorMessage);
        vg0.addComponent(errorMessage);
        
        hg1.addComponent(dropdownLabel);
        vg1.addComponent(dropdownLabel);
        
        hg2.addComponent(courseDropdown);
        vg1.addComponent(courseDropdown);
        
        hg1.addComponent(idLabel);
        vg2.addComponent(idLabel);
        
        hg2.addComponent(idText);
        vg2.addComponent(idText);
        
        hg1.addComponent(creditLabel);
        vg3.addComponent(creditLabel);
        
        hg2.addComponent(creditText);
        vg3.addComponent(creditText);
        
        hg1.addComponent(taJobLabel);
        vg4.addComponent(taJobLabel);
        
        hg2.addComponent(taJobText);
        vg4.addComponent(taJobText);
        
        hg1.addComponent(graderJobLabel);
        vg5.addComponent(graderJobLabel);
        
        hg2.addComponent(graderJobText);
        vg5.addComponent(graderJobText);
        
        hg1.addComponent(getInfoButton);
        vg6.addComponent(getInfoButton);
        
        hg1.addComponent(mcgillIDLabel);
        vg7.addComponent(mcgillIDLabel);
        
        hg2.addComponent(mcgillIDText);
        vg7.addComponent(mcgillIDText);
        
        hg1.addComponent(addJobToList);
        vg8.addComponent(addJobToList);
        
        hg2.addComponent(applyToJobs);
        vg8.addComponent(applyToJobs);
        
        hg3.addComponent(taHourLabel);
        vg1.addComponent(taHourLabel);
        
        hg4.addComponent(taHourText);
        vg1.addComponent(taHourText);
        
        hg3.addComponent(graderHourLabel);
        vg2.addComponent(graderHourLabel);
        
        hg4.addComponent(graderHourText);
        vg2.addComponent(graderHourText);
        
        hg3.addComponent(creditLabel2);
        vg3.addComponent(creditLabel2);
        
        hg4.addComponent(creditText2);
        vg3.addComponent(creditText2);
        
        hg3.addComponent(courseIDLabel);
        vg4.addComponent(courseIDLabel);
        
        hg4.addComponent(courseIDText);
        vg4.addComponent(courseIDText);
        
        hg3.addComponent(budgetLabel);
        vg5.addComponent(budgetLabel);
        
        hg4.addComponent(budgetText);
        vg5.addComponent(budgetText);
        
        hg3.addComponent(studentsEnrolledLabel);
        vg6.addComponent(studentsEnrolledLabel);
        
        hg4.addComponent(studentsEnrolledText);
        vg6.addComponent(studentsEnrolledText);
        
        hg3.addComponent(taCapacityLabel);
        vg7.addComponent(taCapacityLabel);
        
        hg4.addComponent(taCapacityText);
        vg7.addComponent(taCapacityText);
        
        hg3.addComponent(graderCapacityLabel);
        vg8.addComponent(graderCapacityLabel);
        
        hg4.addComponent(graderCapacityText);
        vg8.addComponent(graderCapacityText);
        
        hg3.addComponent(createCourseButton);
        vg9.addComponent(createCourseButton);
        
        GroupLayout.SequentialGroup hseq1 = layout.createSequentialGroup();
        hseq1.addGroup(hg1);
        hseq1.addGroup(hg2);
        hseq1.addGroup(hg3);
        hseq1.addGroup(hg4);
        
        GroupLayout.SequentialGroup vseq1 = layout.createSequentialGroup();
        vseq1.addGroup(vg0);
        vseq1.addGroup(vg1);
        vseq1.addGroup(vg2);
        vseq1.addGroup(vg3);
        vseq1.addGroup(vg4);
        vseq1.addGroup(vg5);
        vseq1.addGroup(vg6);
        vseq1.addGroup(vg7);
        vseq1.addGroup(vg8);
        vseq1.addGroup(vg9);
        
        
        layout.setHorizontalGroup(hseq1);
        layout.setVerticalGroup(vseq1);
        
        for (Course nextCourse: dpt.getTaManager().getCourses()){
			courseDropdown.addItem(nextCourse.getCourseId());
		}
        
        getInfoButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
					getInfoActionPerformed();
        	}
	    });
        createCourseButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
					createCourseActionPerformed();
        	}
	    });
	}
	
	public void refreshdata(){
		errorMessage.setText(error);
		taHourText.setText("");
		graderHourText.setText("");
		creditText2.setText("");
		courseIDText.setText("");
		budgetText.setText("");
		studentsEnrolledText.setText("");
		taCapacityText.setText("");
		graderCapacityText.setText("");
			
	}
	public void addJobToListActionPerformed(){
		TeachingAssistantManagementSystemController tac = new TeachingAssistantManagementSystemController(dpt);
		
	}
	public void getInfoActionPerformed(){
		TeachingAssistantManagementSystemController tac = new TeachingAssistantManagementSystemController(dpt);
		try{
			for (Course nextCourse: tac.ViewCourses()){
				if (courseDropdown.getSelectedItem()!=null && courseDropdown.getSelectedItem().toString().equals(nextCourse.getCourseId())){
					idText.setText(nextCourse.getCourseId());
					creditText.setText(String.valueOf(nextCourse.getCoursCredit()));
					taJobText.setText(String.valueOf(nextCourse.getTaWorkHours())+" hours");
					graderJobText.setText(String.valueOf(nextCourse.getGraderWorkHours())+" hours");
				}
			}
		} catch (NullPointerException e) {
			error = e.getMessage();
		}
	}
	public void createCourseActionPerformed(){
		TeachingAssistantManagementSystemController tac = new TeachingAssistantManagementSystemController(dpt);
		try{
			courseDropdown.addItem(tac.createJobPosting(Integer.parseInt(taHourText.getText()), Integer.parseInt(graderHourText.getText()), Integer.parseInt(creditText2.getText()), courseIDText.getText(), Integer.parseInt(budgetText.getText()), Integer.parseInt(studentsEnrolledText.getText()), new Instructor(), Integer.parseInt(taCapacityText.getText()), Integer.parseInt(graderCapacityText.getText())).getCourseId());
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		
		refreshdata();
	}
}
	
