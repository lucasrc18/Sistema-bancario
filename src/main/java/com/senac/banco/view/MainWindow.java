package com.senac.banco.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.senac.banco.model.BankAccount;
import com.senac.banco.model.User;

/**
 * Classe responsavel por cordenar a pagina principal da aplicação
 */
public class MainWindow extends JPanel {
	
	public MainWindow() {
		super();
		
		Font font = new Font("Roboto", Font.PLAIN, 20);
		
		final User user = new User();
		
		/*
		 * Transferir
		 * Sacar
		 * Depositar
		 * */
		
		JPanel accountPanel = new JPanel(new GridLayout(4, 1));
		accountPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		
//		accountPanel.setBackground(Color.BLUE);
		
		JPanel bankAccountPanel = new JPanel();
		JLabel nameLabel, cpfLabel, accountNumLabel, balanceLabel;
		String userName, userCpf, userAccountNum, userBalance;
		BankAccount userBA = BankAccount.getBankAccount(user);
		
		userName = "Nome: " + user.getName();
		userCpf = "CPF: " + user.getCPF();
		userAccountNum = (userBA != null) ? Integer.toString(userBA.getAccountNumber()) : "Não registrado";
		userBalance = (userBA != null) ? Double.toString(userBA.getBalance()) : "R$ ?";
		
		nameLabel = new JLabel(userName);
		nameLabel.setFont(font);
		
		cpfLabel = new JLabel(userCpf);
		cpfLabel.setFont(font);
		
		accountNumLabel = new JLabel(userAccountNum);
		accountNumLabel.setFont(font);
		
		balanceLabel = new JLabel(userBalance);
		balanceLabel.setFont(font);
		
		accountPanel.add(nameLabel);
		accountPanel.add(cpfLabel);
		accountPanel.add(accountNumLabel);
		accountPanel.add(balanceLabel);
		
		JPanel operationsPanel = new JPanel();
		
		final JButton transferBtn, withdrawBtn, depositBtn;
		
		transferBtn = new JButton("Transferir");
		withdrawBtn = new JButton("Saque");
		depositBtn = new JButton("Deposito");
		
		/*transferBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Abrir lista de contas registradas
				
				// selecionar conta
				
				// transferir conta
			}
		});*/
		
		withdrawBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userBA != null) {
					double amount = Double.parseDouble(JOptionPane.showInputDialog("Qual valor deseja sacar? (R$)"));
					userBA.Withdraw(amount);
				} else {
					new UserRegistrationWindow(user);
				}
			}
		});
		
		depositBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userBA != null) {
					double amount = Double.parseDouble(JOptionPane.showInputDialog("Qual valor deseja depositar? (R$)"));
					userBA.Deposit(amount);
				} else {
					new UserRegistrationWindow(user);
				}
			}
		});
		
		operationsPanel.add(transferBtn);
		operationsPanel.add(withdrawBtn);
		operationsPanel.add(depositBtn);
		
		JPanel header = new JPanel();
		header.setPreferredSize(new Dimension(1200, 200));
		header.add(accountPanel);
		header.add(operationsPanel);
		
		this.add(header);
	}

}
