package com.senac.banco.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserRegistrationWindow extends JFrame {

	private JPanel loginPanel;
	
	public UserRegistrationWindow() {
		super("Registrar uma nova conta");
		
		Dimension ds = new Dimension(400, 600);
		JPanel content = new JPanel();
		
		JLabel titleLabel = new JLabel("Nova conta: ");
//		titleLabel.setFont();
		
		loginPanel = new JPanel();

		content.add(titleLabel);
		content.add(loginPanel);
		
		// Configura a janela
		this.setVisible(true);
		this.setPreferredSize(ds);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		this.add(content);
		this.pack();
		this.setLocationRelativeTo(null);
	}

}
