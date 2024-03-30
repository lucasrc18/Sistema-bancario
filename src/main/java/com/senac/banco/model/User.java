package com.senac.banco.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private static List<User> user_list = new ArrayList<User>();
	
    private static int counter = 0;
    private int userNum;
    private String name;
    private String cpf;
    private String email;
    private boolean hasBankAccount;

    public User(String name, String cpf, String email){
    	this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.userNum = User.counter;
        
        User.counter += 1;
        user_list.add(this);
    }
    public User() {
    	User.counter += 1;
    	this.userNum = User.counter;
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
    }
    public void setCPF(String cpf){
        this.cpf = cpf;
    }
    
    public void setEmail(String email){
        this.email = email;
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
    
    public String toString(){
        return "\n" + this.userNum + "-> Nome: " + this.getName() +
                "\nCPF: " + this.getCPF() +
                "\nEmail: " + this.getEmail();
    }

}
