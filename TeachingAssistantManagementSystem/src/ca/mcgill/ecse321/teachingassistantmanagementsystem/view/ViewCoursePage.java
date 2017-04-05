package ca.mcgill.ecse321.teachingassistantmanagementsystem.view;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.List;
import java.util.Properties;

import javax.swing.AbstractButton;
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
	private JButton createCourseButton;
	private JLabel experienceLabel;
	private JTextField experienceTextField;
	private JButton applyToJobs;
	private JLabel mcgillIDLabel;
	private JTextField mcgillIDText;
	private JComboBox<String> taGraderDropDown;
	private JLabel reviewLabel;
	private JTextField reviewText;
	private JLabel reviewStudentIdLabel;
	private JTextField reviewStudentIdText;
	private JButton createReviewButton;
	private JButton acceptApplicationButton;
	private JButton acceptOfferButton;
	private JButton declineOfferButton;
	private JButton getApplicationInfoButton;
	private JLabel mcgillIdLabel;
	private JLabel mcgillIdText;
	private JLabel getExperienceLabel;
	private JLabel getExperienceText;
	private JLabel jobTypeLabel;
	private JLabel jobTypeText;
	private JLabel applicationStatusLabel;
	private JLabel applicationStatusText;
	private JButton deleteCourseButton;
	private JButton refreshButton;
	
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
	    refreshButton = new JButton();
	    applicationStatusLabel = new JLabel();
	    applicationStatusText = new JLabel();
	    mcgillIdLabel = new JLabel();
	    mcgillIdText = new JLabel();
	    getExperienceLabel = new JLabel();
	    getExperienceText = new JLabel();
	    jobTypeLabel = new JLabel();
	    jobTypeText = new JLabel();
	    getApplicationInfoButton = new JButton();
	    acceptOfferButton = new JButton();
	    declineOfferButton = new JButton();
	    acceptApplicationButton = new JButton();
	    createReviewButton = new JButton();
	    reviewLabel = new JLabel();
	    reviewText = new JTextField();
	    reviewStudentIdLabel = new JLabel();
	    reviewStudentIdText = new JTextField();
	    mcgillIDLabel = new JLabel();
	    mcgillIDText = new JTextField();
	    experienceLabel = new JLabel();
	    experienceTextField = new JTextField();
	    applyToJobs = new JButton();
		dropdownLabel = new JLabel();
		courseDropdown = new JComboBox<String>(new String[0]);
		taGraderDropDown = new JComboBox<String>(new String[0]);
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
		createCourseButton = new JButton();
		deleteCourseButton = new JButton();
		
		
		
	    
	    // global settings and listeners
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("Ta Management System.");
	    setSize(600, 400);
	    
	    
	    // default text
	    deleteCourseButton.setText("Delete Course ");
	    applicationStatusLabel.setText("Application Status: ");
	    applicationStatusText.setText("--");
	    getExperienceLabel.setText("Experience: ");
	    getExperienceText.setText("--");
	    mcgillIdLabel.setText("Mcgill ID: ");
	    mcgillIdText.setText("--");
	    jobTypeLabel.setText("Job Type: ");
	    jobTypeText.setText("--");
	    getApplicationInfoButton.setText("Get App Info");
	    acceptApplicationButton.setText("Offer Job");
	    acceptOfferButton.setText("Accept Offer");
	    declineOfferButton.setText("Decline Offer");
	    reviewLabel.setText("Review: ");
	    reviewText.setText("");
	    reviewStudentIdLabel.setText("Student ID: ");
	    reviewStudentIdText.setText("");
	    dropdownLabel.setText("Course: " );
	    idLabel.setText("ID: " );
		idText.setText("--");
		creditLabel.setText("Credits: " );
		creditText.setText("--");
		taJobLabel.setText("TA Hours: " );
		taJobText.setText("--");
		graderJobLabel.setText("Grader Hours: " );
		graderJobText.setText("--");
		getInfoButton.setText("Get Course Info");
		errorMessage.setText("");
		taHourLabel.setText("TA Work hours:");
		taHourText.setText("");
		graderHourLabel.setText("Grader Work Hours:");
		graderHourText.setText("");
		creditLabel2.setText("Course Credits:");
		creditText2.setText("");
		courseIDLabel.setText("Course ID:");
		courseIDText.setText("");
		budgetLabel.setText("Course Budget:");
		budgetText.setText("");
		studentsEnrolledLabel.setText("Students Enrolled:");
		studentsEnrolledText.setText("");
		createCourseButton.setText("Create Course");
		experienceLabel.setText("Experience:");
		taGraderDropDown.addItem("");
		taGraderDropDown.addItem("grader");
		taGraderDropDown.addItem("TA");
		experienceTextField.setText("");
		applyToJobs.setText("Apply to job");
		mcgillIDLabel.setText("Applicant ID:");
		mcgillIDText.setText("");
		createReviewButton.setText("Create Review");
		refreshButton.setText("Refresh ");
		
		//This will cause a bug for now since there are no courses to show
		
	    // layout		
		
		JPanel panel = new JPanel();
		panel.add(deleteCourseButton);
		panel.add(applicationStatusLabel);
		panel.add(applicationStatusText);
		panel.add(mcgillIdLabel);
		panel.add(mcgillIdText);
		panel.add(getExperienceLabel);
		panel.add(getExperienceText);
		panel.add(jobTypeLabel);
		panel.add(jobTypeText);
		panel.add(getApplicationInfoButton);
		panel.add(acceptOfferButton);
		panel.add(declineOfferButton);
		panel.add(acceptApplicationButton);
		panel.add(reviewLabel);
		panel.add(reviewText);
		panel.add(reviewStudentIdLabel);
		panel.add(reviewStudentIdText);
		panel.add(createReviewButton);
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
		panel.add(refreshButton);
		panel.add(createCourseButton);
		panel.add(applyToJobs);
		panel.add(experienceLabel);
		panel.add(experienceTextField);
		panel.add(taGraderDropDown);
		getContentPane().add(panel);
		
		GroupLayout layout = new GroupLayout(panel);
        layout.setAutoCreateContainerGaps(true);
        panel.setLayout(layout);
        
        GroupLayout.Group hg0 = layout.createParallelGroup();
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
        GroupLayout.Group vg10 = layout.createParallelGroup();
        GroupLayout.Group vg11 = layout.createParallelGroup();
        GroupLayout.Group vg12 = layout.createParallelGroup();
        GroupLayout.Group vg13 = layout.createParallelGroup();
        GroupLayout.Group vg14 = layout.createParallelGroup();
        GroupLayout.Group vg15 = layout.createParallelGroup();
        GroupLayout.Group vg16 = layout.createParallelGroup();
        
        
        //Error message
        
        hg0.addComponent(errorMessage);
        vg0.addComponent(errorMessage);
        
        //Get info
        
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
        
        hg2.addComponent(deleteCourseButton);
        vg6.addComponent(deleteCourseButton);
        
        //Apply to job
        
        hg1.addComponent(mcgillIDLabel);
        vg7.addComponent(mcgillIDLabel);
        
        hg2.addComponent(mcgillIDText);
        vg7.addComponent(mcgillIDText);
        
        hg1.addComponent(experienceLabel);
        vg8.addComponent(experienceLabel);
        
        hg1.addComponent(taGraderDropDown);
        vg9.addComponent(taGraderDropDown);
        
        hg2.addComponent(experienceTextField);
        vg8.addComponent(experienceTextField);
        
        
        
        hg2.addComponent(applyToJobs);
        vg9.addComponent(applyToJobs);
        
      //Create course
        
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
        
        
        hg4.addComponent(createCourseButton);
        vg7.addComponent(createCourseButton);
        
        //Write Review & accept application
        
        hg1.addComponent(reviewLabel);	
        vg10.addComponent(reviewLabel);
        
        hg2.addComponent(reviewText);
        vg10.addComponent(reviewText);
        
        hg1.addComponent(reviewStudentIdLabel);
        vg11.addComponent(reviewStudentIdLabel);
        
        hg2.addComponent(reviewStudentIdText);
        vg11.addComponent(reviewStudentIdText);
        
        hg3.addComponent(createReviewButton);
        vg10.addComponent(createReviewButton);
        
        hg2.addComponent(acceptApplicationButton);
        vg12.addComponent(acceptApplicationButton);
        
        //Get application info
        
        hg1.addComponent(getApplicationInfoButton);
        vg12.addComponent(getApplicationInfoButton);
        
        hg1.addComponent(mcgillIdLabel);
        vg13.addComponent(mcgillIdLabel);
        
        hg2.addComponent(mcgillIdText);
        vg13.addComponent(mcgillIdText);
        
        hg1.addComponent(jobTypeLabel);
        vg14.addComponent(jobTypeLabel);
        
        hg2.addComponent(jobTypeText);
        vg14.addComponent(jobTypeText);
        
        hg1.addComponent(getExperienceLabel);
        vg15.addComponent(getExperienceLabel);
        
        hg2.addComponent(getExperienceText);
        vg15.addComponent(getExperienceText);
        
        hg1.addComponent(applicationStatusLabel);
        vg16.addComponent(applicationStatusLabel);
        
        hg2.addComponent(applicationStatusText);
        vg16.addComponent(applicationStatusText);
        
        //Accept and decline offer
        
        hg3.addComponent(acceptOfferButton);
        vg11.addComponent(acceptOfferButton);
        
        hg4.addComponent(declineOfferButton);
        vg11.addComponent(declineOfferButton);
        
        //Refresh button
        
        hg4.addComponent(refreshButton);
        vg16.addComponent(refreshButton);
        
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
        vseq1.addGroup(vg10);
        vseq1.addGroup(vg11);
        vseq1.addGroup(vg12);
        vseq1.addGroup(vg13);
        vseq1.addGroup(vg14);
        vseq1.addGroup(vg15);
        vseq1.addGroup(vg16);
        
        layout.setHorizontalGroup(layout.createParallelGroup().addComponent(errorMessage).addGroup(hseq1));
        
       
        layout.setVerticalGroup(vseq1);
        courseDropdown.addItem("--");
        for (Course nextCourse: dpt.getTaManager().getCourses()){
			courseDropdown.addItem(nextCourse.getCourseId());
		}
        
        getInfoButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
					getInfoActionPerformed();
        	}
	    });
        deleteCourseButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
					deleteCourseActionPerformed();
        	}
	    });
        
        createCourseButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
					createCourseActionPerformed();
        	}
	    });
        applyToJobs.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
					applyToJobActionPerformed();
        	}
	    });
        createReviewButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
					createReviewActionPerformed();
        	}
	    });
        acceptApplicationButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		acceptApplicationActionPerformed();
        	}
	    });
        acceptOfferButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		acceptOfferActionPerformed();
        	}
	    });
        declineOfferButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		declineOfferActionPerformed();
        	}
	    });
        getApplicationInfoButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		getAppInfoActionPerformed();
        	}
	    });
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		refreshButtonActionPerformed();
        	}
	    });
	}
	
	
	public void refreshdata(){
		errorMessage.setText(error);
		if(error==null || error.length()==0){
			taHourText.setText("");
			graderHourText.setText("");
			creditText2.setText("");
			courseIDText.setText("");
			budgetText.setText("");
			studentsEnrolledText.setText("");
			mcgillIDText.setText("");
			courseDropdown.setSelectedIndex(0);
			taGraderDropDown.setSelectedIndex(0);
			idText.setText("--");
			creditText.setText("--");
			taJobText.setText("--");
			graderJobText.setText("--");
			experienceTextField.setText("");
			reviewText.setText("");
			reviewStudentIdText.setText("");
			mcgillIdText.setText("--");
			jobTypeText.setText("--");
			getExperienceText.setText("--");
			applicationStatusText.setText("--");
		}
		pack();
	}
	public void refreshButtonActionPerformed(){
		courseDropdown.setSelectedIndex(0);
		deleteCourseButton.setText("Delete Course ");
	    applicationStatusLabel.setText("Application Status: ");
	    applicationStatusText.setText("--");
	    getExperienceLabel.setText("Experience: ");
	    getExperienceText.setText("--");
	    mcgillIdLabel.setText("Mcgill ID: ");
	    mcgillIdText.setText("--");
	    jobTypeLabel.setText("Job Type: ");
	    jobTypeText.setText("--");
	    getApplicationInfoButton.setText("Get App Info");
	    acceptApplicationButton.setText("Offer Job");
	    acceptOfferButton.setText("Accept Offer");
	    declineOfferButton.setText("Decline Offer");
	    reviewLabel.setText("Review: ");
	    reviewText.setText("");
	    reviewStudentIdLabel.setText("Student ID: ");
	    reviewStudentIdText.setText("");
	    dropdownLabel.setText("Course: " );
	    idLabel.setText("ID: " );
		idText.setText("--");
		creditLabel.setText("Credits: " );
		creditText.setText("--");
		taJobLabel.setText("TA Hours: " );
		taJobText.setText("--");
		graderJobLabel.setText("Grader Hours: " );
		graderJobText.setText("--");
		getInfoButton.setText("Get Course Info");
		errorMessage.setText("");
		taHourLabel.setText("TA Work hours:");
		taHourText.setText("");
		graderHourLabel.setText("Grader Work Hours:");
		graderHourText.setText("");
		creditLabel2.setText("Course Credits:");
		creditText2.setText("");
		courseIDLabel.setText("Course ID:");
		courseIDText.setText("");
		budgetLabel.setText("Course Budget:");
		budgetText.setText("");
		studentsEnrolledLabel.setText("Students Enrolled:");
		studentsEnrolledText.setText("");
		createCourseButton.setText("Create Course");
		experienceLabel.setText("Experience:");
		experienceTextField.setText("");
		applyToJobs.setText("Apply to job");
		mcgillIDLabel.setText("Applicant ID:");
		mcgillIDText.setText("");
		createReviewButton.setText("Create Review");
		refreshButton.setText("Refresh ");
	}
	public void getAppInfoActionPerformed() {
		error = null;
		boolean isValid = false;
		
		if(courseDropdown.getSelectedItem().toString().equals("--")){
			error = "Must select a course. ";
			refreshdata();
			return;
		}
		
		try{
			if(Integer.parseInt(reviewStudentIdText.getText())<10000000) {
				error = "Invalid McGill ID. ";
				refreshdata();
				return;
			}	
		} catch (NumberFormatException e){
			error = "Invalid McGill ID. ";
			refreshdata();
			return;
		}
			for (Course nextCourse: dpt.getTaManager().getCourses()){
				if (nextCourse.getCourseId().equals(courseDropdown.getSelectedItem().toString())){
					for (JobOffer nextJob : nextCourse.getJob()){
						for (Application nextApp : nextJob.getApplications()){
							if (nextApp.getApplicant().getMcgillId() == Integer.parseInt(reviewStudentIdText.getText()) ){
								mcgillIdText.setText("" + nextApp.getApplicant().getMcgillId());
								getExperienceText.setText(nextApp.getExperience());
								if(nextCourse.getJob(0) == nextJob){
									jobTypeText.setText("TA");
								}
								if(nextCourse.getJob(1)== nextJob){
									jobTypeText.setText("Grader");
								}
								applicationStatusText.setText(nextApp.getStatusFullName());
								
								isValid = true;
							}
						}
					}
				}
			}
			if(!isValid){
				error = "Application not found. ";
				refreshdata();
			}
			else{
				errorMessage.setText("");
				reviewStudentIdText.setText("");
			}
			
			
	}
	public void acceptOfferActionPerformed(){
		error = null;
		TeachingAssistantManagementSystemController tac = new TeachingAssistantManagementSystemController(dpt);
		if(courseDropdown.getSelectedItem().toString().equals("--")){
			error = "Must select course. ";
			refreshdata();
			return;
		}
		try { 
			try{
			tac.acceptOffer(courseDropdown.getSelectedItem().toString(), Integer.parseInt(reviewStudentIdText.getText()));
			} catch (NumberFormatException e){
				error = "Invalid Mcgill ID. ";
			}
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		refreshdata();
	}
	
	public void declineOfferActionPerformed(){
		error = null;
		TeachingAssistantManagementSystemController tac = new TeachingAssistantManagementSystemController(dpt);
		if(courseDropdown.getSelectedItem().toString().equals("--")){
			error = "Must select course. ";
			refreshdata();
			return;
		}
		try{
			try{
				tac.declineOffer(courseDropdown.getSelectedItem().toString(), Integer.parseInt(reviewStudentIdText.getText()));
			} catch (NumberFormatException e){
				error = "Invalid McGill ID. ";
			}
		}catch(InvalidInputException e){
			error = e.getMessage();
		}
		refreshdata();
	}
	public void acceptApplicationActionPerformed(){
		error = null;
		TeachingAssistantManagementSystemController tac = new TeachingAssistantManagementSystemController(dpt);
		if(courseDropdown.getSelectedItem().toString().equals("--")){
			error = "Must select the course the reviewed TA or grader worked on.";
			refreshdata();
			return;
		}
		try {
			try{
			tac.offerJob(courseDropdown.getSelectedItem().toString(), Integer.parseInt(reviewStudentIdText.getText()));
			} catch (NumberFormatException e){
				error = "Invalid Mcgill ID. ";
			}
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		refreshdata();
	}
	public void createReviewActionPerformed(){
		error = null;
		TeachingAssistantManagementSystemController tac = new TeachingAssistantManagementSystemController(dpt);
		if(courseDropdown.getSelectedItem().toString().equals("--")){
			error = "Must select the course the reviewed TA or grader worked on.";
			refreshdata();
			return;
		}
		try {
			try{
			tac.writeReview(courseDropdown.getSelectedItem().toString(),reviewText.toString(), Integer.parseInt(reviewStudentIdText.getText()));
			} catch (NumberFormatException e){
				error = "Invalid Mcgill ID";
			}
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		refreshdata();
		
	}
	
	public void applyToJobActionPerformed(){
		error = null;
		TeachingAssistantManagementSystemController tac = new TeachingAssistantManagementSystemController(dpt);
		if(courseDropdown.getSelectedItem().toString().equals("--")){
			error = "Must select a course to apply to.";
			refreshdata();
			return;
		}
		if(taGraderDropDown.getSelectedIndex()==0){
			error = "Must select either a grader or ta position.";
			refreshdata();
			return;
		}
		try{						
			try{
				for(Course nextCourse: dpt.getTaManager().getCourses()){
					if(nextCourse.getCourseId().equals(courseDropdown.getSelectedItem().toString())){
						if (taGraderDropDown.getSelectedIndex()==1){
							tac.applyForJob(Integer.parseInt(mcgillIDText.getText()), experienceTextField.getText(), nextCourse.getJob(1));
						}
						if (taGraderDropDown.getSelectedIndex()==2){
							tac.applyForJob(Integer.parseInt(mcgillIDText.getText()), experienceTextField.getText(), nextCourse.getJob(0));
						}
					}
				}
			} catch (NumberFormatException e1){
				error = "Invalid Mcgill ID.";
			}		
		} catch (InvalidInputException e){
			error = e.getMessage();
		}
		refreshdata();
	}
	public void getInfoActionPerformed(){
		error = null;
		if(courseDropdown.getSelectedItem().toString().equals("--")){
			error = "Must select a course. ";
			refreshdata();
			return;
		}
		TeachingAssistantManagementSystemController tac = new TeachingAssistantManagementSystemController(dpt);
		try{
			for (Course nextCourse: tac.ViewCourses()){
				if (courseDropdown.getSelectedItem()!=null && courseDropdown.getSelectedItem().toString().equals(nextCourse.getCourseId())){
					idText.setText(nextCourse.getCourseId());
					creditText.setText(String.valueOf(nextCourse.getCoursCredit()));
					taJobText.setText(String.valueOf(nextCourse.getTaWorkHours())+ " hours");
					graderJobText.setText(String.valueOf(nextCourse.getGraderWorkHours())+" hours");
				}
			}
		} catch (NullPointerException e) {
			error = e.getMessage();
		}
		errorMessage.setText("");
	}
	public void deleteCourseActionPerformed(){
		error = null;
		if(courseDropdown.getSelectedItem().toString().equals("--")){
			error = "Must select a course. ";
			refreshdata();
			return;
		}
		TeachingAssistantManagementSystemController tac = new TeachingAssistantManagementSystemController(dpt);
		tac.deleteCourse(courseDropdown.getSelectedIndex()-1);
		courseDropdown.removeItemAt(courseDropdown.getSelectedIndex());
		refreshdata();
	}
	public void createCourseActionPerformed(){
		error = null;
		TeachingAssistantManagementSystemController tac = new TeachingAssistantManagementSystemController(dpt);
		try{
			courseDropdown.addItem(tac.createJobPosting(Integer.parseInt(taHourText.getText()), Integer.parseInt(graderHourText.getText()), Integer.parseInt(creditText2.getText()), courseIDText.getText(), Integer.parseInt(budgetText.getText()), Integer.parseInt(studentsEnrolledText.getText()), new Instructor()).getCourseId());
		} catch (InvalidInputException e){
			error = e.getMessage();
		} catch (NumberFormatException e2){
			error = "Ta work hours, grader work hours, course credits, course budget & students enrolled must be integers.";
		}
		
		refreshdata();
	}
}
	
