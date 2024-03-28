package com.senac.banco.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

/**
 *  Uma customização da classe <code>JTextField</code>
 *  Tornando possivel o uso de <i>placeholders</i> 
 * */
public class TextField extends JTextField {		
	private String placeHolder;
	
	/** Construtor cria um <code>JTextField</code> com um placeholder nulo. */
	public TextField() {
		this("");
	}
	
	/** Construtor cria um <code>JTextField</code> com um placeholder
	 *  pré-definido no parametro
	 *  
	 *  @params text: String (String)
	 * */
	public TextField(String text) {
		// Cria e insere o placeholder
		this.placeHolder = text;
		this.setText(placeHolder);
		
		this.setForeground(Color.GRAY);
		
		/** cria uma referencia para <code>this</code>,
		 *  referencia usada posteriormente dentro do <code>FocusListener</code> */
		final JTextField textField = this;
		this.addFocusListener(new FocusListener() {
			/** Quando a <code>JTextField</code> ganha foco verifica se o 
		    texto é o placeholder, nesse caso ele limpa a entrada de texto */
			@Override
			public void focusGained(FocusEvent e) {
				if(textField.getText().equals(getPlaceholder())) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}
			
			/** Quando a <code>JTextField</code> perde foco verifica se a 
		    entrada esta vazia, nesse caso ele re-insere o placeholder */
			@Override
			public void focusLost(FocusEvent e) {
				if(textField.getText().isEmpty()) {
					textField.setText(getPlaceholder());
					textField.setForeground(Color.GRAY);
				}
			}
		});
	}
	
   /** Getter para o placeholder
	*  @return placeholder: String
	*/
	public String getPlaceholder() {
		return this.placeHolder;
	}
	
	/** Setter para o placeholder
	*  @param placeholder: String */
	public void getPlaceholder(String placeholder) {
		if(this.placeHolder == placeholder)
			return;
		
		if(getText().isEmpty() && getText().equals(this.placeHolder)) {
			setText(placeHolder);
			this.placeHolder = placeholder;
		}
	}
	
}

