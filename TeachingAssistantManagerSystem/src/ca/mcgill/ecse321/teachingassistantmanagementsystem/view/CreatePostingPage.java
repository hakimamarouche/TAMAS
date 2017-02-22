package ca.mcgill.ecse321.teachingassistantmanagementsystem.view;


import java.awt.Color;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.teachingassistantmanagementsystem.controller.*;
import ca.mcgill.ecse321.teachingassistantmanagementsystem.ump.*;

public class CreatePostingPage {
	private JLabel errorMessage;
	
	private String error = null;
	
	private void initComponents() {
	    // elements for error message
	    errorMessage = new JLabel();
	    errorMessage.setForeground(Color.RED);
	
	}
}
