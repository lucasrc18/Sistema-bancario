package com.senac.banco.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.senac.banco.DB.SQLiteDB;
import com.senac.banco.components.ImageComponent;
import com.senac.banco.components.ImageComponent.ImageDimension;
import com.senac.banco.main.Utils;
import com.senac.banco.model.BankAccount;
import com.senac.banco.model.User;

/**
 * Classe responsavel por cordenar a pagina principal da aplicação
 */
public class MainWindow extends JPanel {
	public static class AccountLabels {
		public JLabel nameLabel;
		public JLabel cpfLabel;
		public JLabel accountNumLabel;
		public JLabel balanceLabel;
		public JPanel panel;
		
		public void updateBalance(BankAccount userBA) {
			String userBalance = (userBA != null) ? Utils.doubleToRealCurrency(userBA.getBalance()) : "R$ ?";
			balanceLabel.setText(userBalance);
		}
	}
	
	public MainWindow(SQLiteDB db) {
		super();
		
		final MainWindow mainWindow = this;
		
		Font font = new Font("Roboto", Font.PLAIN, 20);
		
		final User user;
		if(db.getUser() != null) {
			User userData = db.getUser();
			user = new User(userData.getName(), userData.getCPF(), userData.getEmail());
		} else {
			user = new User();
		}
		
		/*
		 * Transferir
		 * Sacar
		 * Depositar
		 * */
		
		JPanel accountPanel = new JPanel(new GridLayout(4, 1));
		accountPanel.setBorder(new EmptyBorder(0, 20, 0, 0));
		
		accountPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
		
		JPanel bankAccountPanel = new JPanel();	
		
		AccountLabels accountLabels = new AccountLabels();
		
		accountLabels.panel = bankAccountPanel;
		
		accountLabels.nameLabel = new JLabel("Nome: ?", SwingConstants.CENTER);
		accountLabels.nameLabel.setFont(font);
		
		accountLabels.cpfLabel = new JLabel("CPF: ?", SwingConstants.CENTER);
		accountLabels.cpfLabel.setFont(font);
		
		accountLabels.accountNumLabel = new JLabel("Não registrado", SwingConstants.CENTER);
		accountLabels.accountNumLabel.setFont(font);
		
		accountLabels.balanceLabel = new JLabel("R$ ?", SwingConstants.CENTER);
		accountLabels.balanceLabel.setFont(font);
		
		accountPanel.add(accountLabels.nameLabel);
		accountPanel.add(accountLabels.cpfLabel);
		accountPanel.add(accountLabels.accountNumLabel);
		accountPanel.add(accountLabels.balanceLabel);
		
		JPanel operationsPanel = new JPanel();
		
		final JButton transferBtn, withdrawBtn, depositBtn;
		
		transferBtn = new JButton("Transferir");
		withdrawBtn = new JButton("Saque");
		depositBtn = new JButton("Deposito");
		
		transferBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(BankAccount.getBankAccount(user) != null) {
					final BankAccount userBA = BankAccount.getBankAccount(user);
					double amount = Double.parseDouble(JOptionPane.showInputDialog("Qual valor deseja transferir? (R$)"));
					
					String[] options = new String[BankAccount.getBankAccountNums()+1];
		            int last = options.length - 1;
		            
		            int i = 0;
		            options[last] = "Ninguem selecionado";
		            for(BankAccount BA : BankAccount.getAccounts()) {
		            	if(BA != null) {
		            		options[i] = BA.getUser().getName();
			            	i += 1;
		            	}
		            }
		            
		            Object res = JOptionPane.showInputDialog(null, "Selecione o destinatario", "Quem irá receber?", JOptionPane.QUESTION_MESSAGE, null, options, options[last]);
		            
		            int index;
		            if(res != null) {
		            	index = BankAccount.account_number_list.indexOf(res);
		            	
		            	int accountNumber = BankAccount.account_number_list.get(index);
			            BankAccount BA = BankAccount.account_list.get(accountNumber);
			            
			            userBA.Transfer(BA, amount);
		            } else {
		            	JOptionPane.showMessageDialog(mainWindow, "EU TENTEEI, mas ocorreu um erro ao selecionar o destinatario");
		            }
				} else {
					JOptionPane.showMessageDialog(mainWindow, "Você precisa de uma conta primeiro");
					new UserRegistrationWindow(user, accountLabels);
				}
			}
		});
		
		withdrawBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(BankAccount.getBankAccount(user) != null) {
					double amount = Double.parseDouble(JOptionPane.showInputDialog("Qual valor deseja sacar? (R$)"));
					boolean result = BankAccount.getBankAccount(user).Withdraw(amount);
					if(result)
						accountLabels.updateBalance(BankAccount.getBankAccount(user));
				} else {
					JOptionPane.showMessageDialog(mainWindow, "Você precisa de uma conta primeiro");
					new UserRegistrationWindow(user, accountLabels);
				}
			}
		});
		
		depositBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(BankAccount.getBankAccount(user) != null) {
					String answer = JOptionPane.showInputDialog("Qual valor deseja depositar? (R$)");
					double amount = Double.parseDouble(answer);
					
					boolean result = BankAccount.getBankAccount(user).Deposit(amount);
					if(result)
						accountLabels.updateBalance(BankAccount.getBankAccount(user));
				} else {
					JOptionPane.showMessageDialog(mainWindow, "Você precisa de uma conta primeiro");
					new UserRegistrationWindow(user, accountLabels);
				}
			}
		});
		
		operationsPanel.add(transferBtn);
		operationsPanel.add(withdrawBtn);
		operationsPanel.add(depositBtn);
		
		JPanel content = new JPanel();
		
		ImageComponent img = new ImageComponent("moedaxxxs.png");
		
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		
		content.add(img);
		content.add(accountPanel);
		content.add(operationsPanel);
		
		this.add(content);
	}

	
}
