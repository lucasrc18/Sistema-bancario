package com.senac.banco.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.senac.banco.model.User;

public class UserRegistrationWindow extends JFrame {
	
	public UserRegistrationWindow(final User user) {
		super("Registrar uma nova conta");
		
		Dimension ds = new Dimension(400, 600);
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		
		JLabel titleLabel = new JLabel("Atualizar dados da conta: ");
		titleLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		titleLabel.setBorder(new EmptyBorder(40, 10, 0, 10));
		
		JPanel headerPanel = new JPanel();
		headerPanel.add(titleLabel);
		
		JPanel fieldsPanel = new JPanel();
//		fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));

		final JTextField nameField, cpfField, emailField;
		Dimension textFieldDs = new Dimension(250, 30);
		
		nameField = new JTextField();
		nameField.setPreferredSize(textFieldDs);
		
		cpfField = new JTextField();
		cpfField.setPreferredSize(textFieldDs);
		
		emailField  = new JTextField();
		emailField.setPreferredSize(textFieldDs);
		
		fieldsPanel.add(nameField);
		fieldsPanel.add(cpfField);
		fieldsPanel.add(emailField);
		
		JButton updateBtn = new JButton("Atualizar dados");
		updateBtn.setBorder(new EmptyBorder(0, 0, 20, 0));
		
		updateBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!cpfField.getText().isEmpty())
					user.setCPF(cpfField.getText());
				if(!nameField.getText().isEmpty())
					user.setName(nameField.getText());
				if(!emailField.getText().isEmpty())
					user.setEmail(emailField.getText());
			}
			
		});
		
		content.add(headerPanel);
		content.add(fieldsPanel);
		content.add(updateBtn);
		
		// Configura a janela
		this.setVisible(true);
		this.setPreferredSize(ds);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
		this.add(content);
		this.pack();
		this.setLocationRelativeTo(null);
	}

}
