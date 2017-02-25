package ca.mcgill.ecse321.teachingassistantmanagementsystem.view;


import java.awt.Color;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.teachingassistantmanagementsystem.controller.*;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.*;

public class ViewCoursePage extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2012446709379049017L;

	private JLabel errorMessage;
	private Department dpt;
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
	
	public ViewCoursePage(Department dpt)
	{
		this.dpt = dpt;
		initComponents();
		refreshData();
	}
	
	private void refreshData() {

		pack();
	}
	
	private void initComponents() {
	    // elements for error message
	    errorMessage = new JLabel();
	    errorMessage.setForeground(Color.RED);
	    
	    // inits
		dropdownLabel = new JLabel();
		JComboBox<String> courseDropdown = new JComboBox<String>(new String[0]);
		idLabel = new JLabel();
		idText = new JLabel();
		creditLabel = new JLabel();
		creditText = new JLabel();
		taJobLabel = new JLabel();
		taJobText = new JLabel();
		graderJobLabel = new JLabel();
		graderJobText = new JLabel();
	    
	    // global settings and listeners
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setTitle("Event Registration");
	    
	    // default text
	    dropdownLabel.setText("Course: " );
	    idLabel.setText("ID: " );
		idText.setText("");
		creditLabel.setText("Credits: " );
		creditText.setText("");
		taJobLabel.setText("TA Jobs: " );
		taJobText.setText("");
		graderJobLabel.setText("Grader Jobs: " );
		graderJobText.setText("");
	    
	    // layout
	    GroupLayout layout = new GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);

	    layout.setHorizontalGroup(
	    		
	    		layout.createParallelGroup()
	    		.addComponent(errorMessage)
	    		.addGroup(layout.createSequentialGroup()
	    				.addGroup(layout.createParallelGroup()
	    						.addComponent(dropdownLabel)
	    						.addComponent(idLabel)
	    						.addComponent(taJobLabel)
	    						.addComponent(graderJobLabel))
	    				.addGroup(layout.createParallelGroup()
	    						.addComponent(courseDropdown)
	    						.addComponent(idText)
	    						.addComponent(taJobText)
	    						.addComponent(graderJobText)
	    				)

	    		)
	    	);
	
	    layout.setVerticalGroup(
	            layout.createSequentialGroup()
	            .addComponent(errorMessage)
	            .addGroup(layout.createParallelGroup()
	                    .addComponent(dropdownLabel)
	                    .addComponent(courseDropdown))
	            .addGroup(layout.createParallelGroup()
	                    .addComponent(idLabel)
	                    .addComponent(idText))
	            .addGroup(layout.createParallelGroup()
	                    .addComponent(taJobLabel)
	                    .addComponent(taJobText))
	            .addGroup(layout.createParallelGroup()
	                    .addComponent(graderJobLabel)
	                    .addComponent(graderJobText))
	        );

	}
}
