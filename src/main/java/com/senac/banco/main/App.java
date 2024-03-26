package com.senac.banco.main;

import com.senac.banco.model.BankAccount;
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
    static ArrayList<BankAccount> bankAccounts;
    public static void main(String[] args){
        GraphicalInterface gui = new GraphicalInterface();

        bankAccounts = new ArrayList<BankAccount>();
        operations();

    }
    public static void operations(){
        System.out.println("-------------------------------------------------");
        System.out.println("--------------Welcome to your bank!--------------");
        System.out.println("-------------------------------------------------");
        System.out.println("-----Select an operation you want to perform-----");
        System.out.println("-------------------------------------------------");
        System.out.println("option 1: Create account");
        System.out.println("Option 2: Deposit");
        System.out.println("option 3: Withdraw");
        System.out.println("option 4: Transfer");
        System.out.println("option 5: List");
        System.out.println("option 6: Exit");




    }
}
