package com.senac.banco.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.senac.banco.components.TextField;
import com.senac.banco.main.Utils;
import com.senac.banco.model.BankAccount;
import com.senac.banco.model.User;
import com.senac.banco.view.MainWindow.AccountLabels;

public class UserRegistrationWindow extends JFrame {
	
	public UserRegistrationWindow(final User user, final AccountLabels accountLabels) {
		super("Registrar uma nova conta");
		
		final UserRegistrationWindow regwin = this;
		
		Dimension ds = new Dimension(400, 600);
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		
		JLabel titleLabel = new JLabel("Atualizar dados da conta: ");
		titleLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		titleLabel.setBorder(new EmptyBorder(0, 40, 0, 20)); 
		
		JPanel headerPanel = new JPanel();
		headerPanel.add(titleLabel);
		
		JPanel fieldsPanel = new JPanel();

		final JTextField nameField, cpfField, emailField;
		Dimension textFieldDs = new Dimension(250, 30);
		
		nameField = new TextField("Digite seu nome...");
		nameField.setPreferredSize(textFieldDs);
		
		cpfField = new TextField("Digite seu cpf...");
		cpfField.setPreferredSize(textFieldDs);
		
		emailField = new TextField("Digite seu email...");
		emailField.setPreferredSize(textFieldDs);
		
		fieldsPanel.add(nameField);
		fieldsPanel.add(cpfField);
		fieldsPanel.add(emailField);
		
		JButton updateBtn = new JButton("Atualizar dados");
		JPanel btnPanel = new JPanel();
		btnPanel.add(updateBtn);
		
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!cpfField.getText().isEmpty())
					user.setCPF(cpfField.getText());
				if(!nameField.getText().isEmpty())
					user.setName(nameField.getText());
				if(!emailField.getText().isEmpty())
					user.setEmail(emailField.getText());
				
				user.createBankAccount();
				
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						BankAccount userBA = BankAccount.getBankAccount(user);
						
						String userName = "Nome: " + user.getName();
						String userCpf = "CPF: " + user.getCPF();
						String userAccountNum = (userBA != null) ? "N°: " + Integer.toString(userBA.getAccountNumber()) : "Não registrado";
						String userBalance = (userBA != null) ? Utils.doubleToRealCurrency(userBA.getBalance()) : "R$ ?";
						
						accountLabels.nameLabel.setText(userName);
						accountLabels.cpfLabel.setText(userCpf);
						accountLabels.accountNumLabel.setText(userAccountNum);
						accountLabels.balanceLabel.setText(userBalance);
					}
				});
				
				regwin.dispose();
			}

		});
		
		content.add(headerPanel);
		content.add(fieldsPanel);
		content.add(btnPanel);
		
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
