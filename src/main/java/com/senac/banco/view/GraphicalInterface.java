package com.senac.banco.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.senac.banco.services.Firebase;

/*
 * Classe responsável por cordenar a interface gráfica
 * */
public class GraphicalInterface extends JFrame {
	/** <code>CardLayout</code> é a classe responsável por gerenciar
	 * o layout e exibições das páginas na janela */
	private CardLayout layout;
	
	
	/** O <code>JPanel</code> pai de toda a interface, e de todas as telas */
	private JPanel window;
	
	/** O <code>JPanel</code> relacionado a página principal */
	private JPanel main;
	
	/**
	 * Constrói uma nova instancia da interface grafica
	 * Define o tamanho da tela com um valor padrão
	 * @params null
	 * @returns this
	 * */
	public GraphicalInterface() {
		super("Sistema bancario");
		
		/** Define o tamanho da janela como 1200 / 600 (W / H) */
		/** Estipula as dimensões da janela baseada no tamanho da tela */
		Dimension windowSize = new Dimension(1200, 600);
		
		this.window = new JPanel();
		this.layout = new CardLayout();
		window.setLayout(layout);
		
		
		main = new MainWindow();
		window.add(main, "main");
		
		// Configura a janela
		this.setVisible(true);
		this.setPreferredSize(windowSize);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		layout.show(window, "main");
		this.add(window);
		this.pack();
		this.setLocationRelativeTo(null);
		
		/**
		 * Fecha a conexão com o banco de dados quando a janela for fechada
		 * */
		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	Firebase.TerminateFirebaseApp();
            }
        });
	}
	
	/**
	 * Alterna entre as telas da interface
	 * @params windowName: String
	 * @returns void
	 * */
	public void goToWindow(String windowName) {
		layout.show(window, windowName);
	}
}
