package com.senac.banco.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.senac.banco.model.User;

/**
 * Classe responsavel por cordenar a pagina principal da aplicação
 */
public class MainWindow extends JPanel {
	
	public MainWindow() {
		super();
		
		final User user = new User();
		
		JButton btn = new JButton("abre a janela ai fi");
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserRegistrationWindow(user);
			}
		});
		
		this.add(btn);
	}

}
