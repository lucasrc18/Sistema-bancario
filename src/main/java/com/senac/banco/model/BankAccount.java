package com.senac.banco.model;

import com.senac.banco.main.Utils;

public class BankAccount {

	private static int accountant = 1;
	private double balance = 0.0;
	private int accountNumber;
	private People people;

	public BankAccount (People people){
		this.accountNumber = accountant;
		this.people = people;
		accountant += 1;
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

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public String toString(){
		return  "\nNumero da Conta: " + this.getAccountNumber() +
				"\nNome: " + this.people.getName() +
		        "\nCPF: " + this.people.getCPF() +
				"\nEmail: " + this.people.getEmail() +
				"\nSaldo: " + Utils.doubleToString(this.getBalance()) +
				"\n";
	}
	public void Deposit (Double value){
		if (value > 0){
			setBalance(getBalance() + value);
			System.out.println("Deposito realizado com sucesso!");
		} else {
			System.out.println("Não  foi possivel realizar o deposito");
		}
	}

	public void Withdraw (Double value){
        if(value > 0 && this.getBalance() >= value){
            setBalance(getBalance() - value);
            System.out.println("Saque realizado com sucesso!");
        }else {
            System.out.println("Não foi possivel realizar o saque!");
        }
    }
    public void Transfer (BankAccount depositAccount, Double value){
        if (value > 0 && this.getBalance() >= value){
            setBalance(getBalance() - value);

            depositAccount.balance = depositAccount.getBalance() + value;
            
            System.out.println("Transferência realizada com sucesso!");
        } else {
            System.out.println("Não foi possivel realizar a tranferência");
        }

    }
}
