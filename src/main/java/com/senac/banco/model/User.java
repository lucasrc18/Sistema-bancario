package com.senac.banco.model;

public class User {
    private static int counter = 0;
    private int userNum;
    private String name;
    private String cpf;
    private String email;
    private boolean hasBankAccount;

    public User(String name, String cpf, String email){
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        User.counter += 1;
        this.userNum = User.counter;
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
    
    public String toString(){
        return "\n" + this.userNum + "-> Nome: " + this.getName() +
                "\nCPF: " + this.getCPF() +
                "\nEmail: " + this.getEmail();
    }

}
