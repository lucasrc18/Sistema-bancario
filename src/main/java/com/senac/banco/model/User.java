package com.senac.banco.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.firebase.database.FirebaseDatabase;
import com.senac.banco.services.Firebase;
import com.senac.banco.services.RealtimeDatabase;

public class User {
	private static List<User> user_list = new ArrayList<User>();
	
    private static int counter = 0;
    private int userNum;
    private String name;
    private String cpf;
    private String email;
    private boolean hasBankAccount;
    
    private RealtimeDatabase database;
    
    public User(boolean voidModel) {
		if(voidModel)
			return;
		
		User.counter += 1;
    	this.userNum = User.counter;
    	
    	try {
			database = new RealtimeDatabase();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public User(String name, String cpf, String email){
    	this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.userNum = User.counter;
        
        User.counter += 1;
        user_list.add(this);
    }
    public User() {
    	this(false);
    }
    
    public String getName(){
        return name;
    }
    
    public String getCPF(){
        return cpf;
    }
    
    public String getEmail(){
        return email;
    }
   
    public void setName(String name) {
    	this.name = name;
    	
    	database.setValue("desktop_name", name);
    }
    
    public boolean setCPF(String cpf){
        if(cpf == null) {
        	this.cpf = null;
        	return false; 
        }
    	
    	if(cpf.length() != 11) {
        	JOptionPane.showMessageDialog(null, "CPF invalido");  
        	return false;
        }
        
        int sum = 0;
		for(int i = 0; i < 9; i++) {
			// Subtraimos por 0 para pegar o valor numerico do digito
			sum += (cpf.charAt(i) - '0') * (10-i);
		}
		
		int digit1 = 11 - (sum % 11);
		if(digit1 >= 10) {
			digit1 = 0;
		}
		
		sum = 0;
		for(int i = 0; i < 10; i++) {
			sum += (cpf.charAt(i) - '0') * (11-i);
		}
		
		int digit2 = 11 - (sum % 11);
		if(digit2 >= 10) {
			digit2 = 0;
		}
		
		if(cpf.charAt(10) - '0' != digit2) {
			JOptionPane.showMessageDialog(null, "CPF invalido"); 
			return false;
		}
    	
    	this.cpf = cpf;
    	database.setValue("desktop_cpf", cpf);
    	return true;
    }
    
    public void setEmail(String email){
        this.email = email;
        database.setValue("desktop_email", email);
    }
    
    public static int getUsersCount() {
    	return User.counter;
    }
    
    public int getUserNum() {
    	return this.userNum;
    }
    
    public void createBankAccount() {
    	if(hasBankAccount)
    		return;
    	
    	new BankAccount(this);
    }
      
    public void createBankAccount(double minDeposit) {
    	if(hasBankAccount)
    		return;
    	
    	new BankAccount(this, minDeposit);
    }
    
    public RealtimeDatabase getDatabase() {
    	return database;
    }
    
    public String toString(){
        return "\n" + this.userNum + "-> Nome: " + this.getName() +
                "\nCPF: " + this.getCPF() +
                "\nEmail: " + this.getEmail();
    }

}
