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
        try {
            Class.forName("com.senac.banco.DB.SQLiteDB");
        } catch (ClassNotFoundException e) {
            System.err.println("Erro ao carregar o driver: " + e.getMessage());
            System.exit(1);
        }

        SQLiteDB db = new SQLiteDB();
        createTable();

        GraphicalInterface gui = new GraphicalInterface();
//    	new UserRegistrationWindow();
    	
        bankAccounts = new ArrayList<BankAccount>();
        //operations();

    }
    public static void createTable() {
        String url = "jdbc:sqlite:banco.db";

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS bank_accounts (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT NOT NULL," +
                    "CPF TEXT NOT NULL," +
                    "Email TEXT NOT NULL," +
                    "numeroDaConta INTEGER NOT NULL," +
                    "Saldo REAL NOT NULL)";
            statement.execute(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public static void operations(){

        System.out.println("-----------------------------------------------------");
        System.out.println("--------------Bem vindo ao flaBankinho!--------------");
        System.out.println("-----------------------------------------------------");
        System.out.println("-------Selecione a operação que deseja realizar------");
        System.out.println("-----------------------------------------------------");
        System.out.println("Opção 1: Criar conta");
        System.out.println("Opção 2: Depositar");
        System.out.println("Opção 3: Sacar");
        System.out.println("Opção 4: Transferir");
        System.out.println("Opção 5: Listar contas");
        System.out.println("Opção 6: Sair");

        int operation = input.nextInt();

        switch (operation){
            case 1:
                createAccount();
                break;
            case 2:
                deposit();
                break;
            case 3:
                withdraw();
                break;
            case 4:
                transfer();
                break;
            case 5:
                list();
                break;
            case 6:
                System.out.println("Finalizando progrOpção");
               System.exit(0);

            default:
                System.out.println("Opção invalida");
                operations();
                break;
        }
    }
    public static void createAccount(){
        System.out.println("\nNome: ");
        String name = input.next();
        System.out.println("\nCPF:  ");
        String cpf = input.next();
        System.out.println("\nEmail: ");
        String email = input.next();

        User people = new User(name,cpf,email);
        BankAccount account = new BankAccount(people);

        bankAccounts.add(account);

        System.out.println("Sua conta foi criada com sucesso!");

        operations();

    }

    private static BankAccount findAccount(int accountNumber){
        if (bankAccounts.size() > 0 ){
            for (BankAccount account: bankAccounts){
                if(account.getAccountNumber() == accountNumber)
                    return account;
            }
        }
        return null;
    }

    public static void  deposit(){
        System.out.println("Numero da Conta: ");
        int accountNumber = input.nextInt();

        BankAccount account = findAccount(accountNumber);

        if (account != null){
            System.out.println("Qual valor deseja depositar? ");
            Double valueDeposit = input.nextDouble();
            account.Deposit(valueDeposit);
            System.out.println("Valor depositado com sucesso! ");

        }else {
            System.out.println("Conta não encontrada ");
        }
        operations();
    }
    public static void withdraw(){
        System.out.println("Numero da conta: ");
        int accountNumber = input.nextInt();
        BankAccount account = findAccount(accountNumber);
        if (account != null){
            System.out.println("Qual valor deseja sacar ");
            Double  withdrawalValue = input.nextDouble();
            account.Withdraw(withdrawalValue);
            System.out.println("Saque realizado com sucesso! ");
        }else {
            System.out.println("Conta não encontrada! ");
        }
        operations();

    }

    public static void transfer(){
        System.out.println("Numero da conta do remetente: ");
        int senderAccountNumber = input.nextInt();
        BankAccount senderAccount = findAccount(senderAccountNumber);
        if(senderAccount != null){
            System.out.println("Numero da conta do destinatario: ");
            int recipientAccountNumber = input.nextInt();

            BankAccount recipientAccount = findAccount(recipientAccountNumber);
            if(recipientAccount != null){
                System.out.println("Qual valor deseja tranferir");
                Double value = input.nextDouble();

                senderAccount.Transfer(recipientAccount,value);
            }
        }
        operations();

    }
    public static void list(){
        if (bankAccounts.size() > 0){
            for (BankAccount account: bankAccounts){
                System.out.println(account);
            }

            }else{
            System.out.println("Não a contas cadastradas! ");
        }
        operations();
    }



}
