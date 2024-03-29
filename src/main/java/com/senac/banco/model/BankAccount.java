package com.senac.banco.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;
import java.lang.NumberFormatException;
import java.lang.NullPointerException;

import com.senac.banco.main.Utils;

public class BankAccount {
	public final static List<Integer> account_number_list = new ArrayList<Integer>();
	public final static Map<Integer, BankAccount> account_list = new HashMap<Integer, BankAccount>();
	private double balance = 0.0;
	private final int accountNumber;
	private final User user;

	public BankAccount(User user) {
		Random random = new Random(System.currentTimeMillis());
		
		int accountNumber = Utils.randomNumber(100000, 999999);
		while(account_number_list.contains(accountNumber)) {			
			accountNumber = Utils.randomNumber(100000, 999999);
		}
		this.accountNumber = accountNumber;
		this.user = user;
		
		double amount = 0;
		while(amount < 50) {
			try {
				amount = Double.parseDouble(JOptionPane.showInputDialog("Qual deposito inicial? (Min: R$ 50,00)"));
			} catch (NullPointerException e) {
				amount = 0;
			} catch (NumberFormatException e) {
				amount = 0;
			}
		}
		
		this.balance = amount;
		
		account_list.put(this.accountNumber, this);
		account_number_list.add(this.accountNumber);
	}
	
	public BankAccount (User user, double minDeposit) {
		Random random = new Random(System.currentTimeMillis());
		
		int accountNumber = Utils.randomNumber(100000, 999999);
		while(account_number_list.contains(accountNumber)) {			
			accountNumber = Utils.randomNumber(100000, 999999);
		}
		this.accountNumber = accountNumber;
		this.user = user;
		
		this.balance = minDeposit;
		
		account_list.put(this.accountNumber, this);
		account_number_list.add(this.accountNumber);
	
	}
	
	
	public static BankAccount getBankAccount(final User user) {
		if(account_list.isEmpty())
			return null;
		
		int index = user.getUserNum()-1;
		if(index >= account_number_list.size())
			return null;
		
		return account_list.get(account_number_list.get(index));
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public User getPeople() {
		return user;
	}

	public String toString(){
		return  "\nNumero da Conta: " + this.getAccountNumber() +
				"\nNome: " + this.user.getName() +
		        "\nCPF: " + this.user.getCPF() +
				"\nEmail: " + this.user.getEmail() +
				"\nSaldo: " + Utils.doubleToString(this.getBalance()) +
				"\n";
	}
	public void Deposit (Double value){
		if (value > 0){
			setBalance(getBalance() + value);
			JOptionPane.showMessageDialog(null, "Deposito realizado com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Não  foi possivel realizar o deposito");
		}
	}

	public void Withdraw (Double value){
        if(value > 0 && this.getBalance() >= value){
            setBalance(getBalance() - value);
            JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
        }else {
        	JOptionPane.showMessageDialog(null, "Não foi possivel realizar o saque!");
        }
    }
    public void Transfer (BankAccount depositAccount, Double value){
        if (value > 0 && this.getBalance() >= value){
            setBalance(getBalance() - value);

            depositAccount.setBalance(depositAccount.getBalance() + value);
            
            //
            
            JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
        } else {
        	JOptionPane.showMessageDialog(null, "Não foi possivel realizar a tranferência");
        }

    }

	public void setAccountNumber(int i) {
	}
	
	public static int getBankAccountNums() {
		return account_list.size();
	}
	
	public static BankAccount[] getAccounts() {
		BankAccount[] list = new BankAccount[getBankAccountNums()];
		int index = 0;
		for(Map.Entry<Integer, BankAccount> entry : account_list.entrySet()) {
			list[index] = entry.getValue();
		}
		return list;
	}
}
