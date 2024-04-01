package com.senac.banco.main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.senac.banco.DB.SQLiteDB;
import com.senac.banco.model.BankAccount;
import com.senac.banco.model.User;
import com.senac.banco.view.GraphicalInterface;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Júlia Gomes
 * @author Lucas Rodrigues
 *	
 * Projeto de sistema bancario, que simula transações e operações básicas
 * */
public class App {
    static Scanner input = new Scanner(System.in);
    public static ArrayList<BankAccount> bankAccounts;
    public static void main(String[] args){
        User pedro = new User("Pedro", "234.789.197-32", "pedro@gmail.com");
        pedro.createBankAccount(2000);
        User arrascaeta = new User("Arrascaeta", "943.429.091-32", "arrascaeta@gmail.com");
        arrascaeta.createBankAccount(1800);
        User ribas = new User("Diego ribas", "789.541.187-42", "ribas@gmail.com");
        ribas.createBankAccount(2300);
    	
    	try {
            Class.forName("com.senac.banco.DB.SQLiteDB");
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver: " + e.getMessage());
            System.exit(1);
        }

        SQLiteDB db = new SQLiteDB();
        try {
			db.createTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}

        GraphicalInterface gui = new GraphicalInterface(db);
    	
        bankAccounts = new ArrayList<BankAccount>();
    }
}
