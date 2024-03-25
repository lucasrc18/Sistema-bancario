package com.senac.banco.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicalInterface extends JFrame {
	private CardLayout layout;
	private JPanel window;
	
	public GraphicalInterface() {
		super("Sistema bancario");
		
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		windowSize.width = (int) Math.ceil((double) windowSize.width * 0.75);
		windowSize.height = (int) Math.ceil((double) windowSize.height * 0.75);
		
		this.window = new JPanel();
		this.layout = new CardLayout();
		window.setLayout(layout);
		
		JPanel main = new JPanel();
		window.add(main, "main");
		
		this.setVisible(true);
		this.setPreferredSize(windowSize);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		layout.show(window, "main");
		
		this.add(window);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public void goToWindow(String windowName) {
		layout.show(window, windowName);
	}
}
