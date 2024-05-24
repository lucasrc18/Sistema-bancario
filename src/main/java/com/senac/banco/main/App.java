package com.senac.banco.main;

import com.senac.banco.model.User;
import com.senac.banco.model.BankAccount;
import com.senac.banco.services.RealtimeDatabase;
import com.senac.banco.view.GraphicalInterface;

import java.util.ArrayList;

/**
 * @author Júlia Gomes
 * @author Lucas Rodrigues
 *	
 * Projeto de sistema bancario, que simula transações e operações básicas
 * */
public class App {
    public static ArrayList<BankAccount> bankAccounts;
    public static RealtimeDatabase db = null;
    
    public static void main(String[] args){
        User pedro = new User("Pedro", "234.789.197-32", "pedro@gmail.com");
        pedro.createBankAccount(2000);
        User arrascaeta = new User("Arrascaeta", "943.429.091-32", "arrascaeta@gmail.com");
        arrascaeta.createBankAccount(1800);
        User ribas = new User("Diego ribas", "789.541.187-42", "ribas@gmail.com");
        ribas.createBankAccount(2300);
		
        bankAccounts = new ArrayList<BankAccount>();
    	
        GraphicalInterface gui = new GraphicalInterface();
    }
}
