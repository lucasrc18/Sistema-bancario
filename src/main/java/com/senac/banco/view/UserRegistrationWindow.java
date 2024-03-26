package com.senac.banco.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserRegistrationWindow extends JFrame {

	private JPanel loginPanel;
	
	public UserRegistrationWindow() {
		super("Registrar uma nova conta");
		
		Dimension ds = new Dimension(400, 600);
		JPanel content = new JPanel(new GridLayout());
		
		JLabel titleLabel = new JLabel("Nova conta: ");
		titleLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		
		JPanel headerPanel = new JPanel();
		headerPanel.add(titleLabel);
		
		loginPanel = new JPanel();

		JTextField email = new JTextField("Email: ");
		JTextField senha = new JTextField("Senha: ");
		
		loginPanel.add(email);
		loginPanel.add(senha);
		
		content.add(headerPanel);
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
