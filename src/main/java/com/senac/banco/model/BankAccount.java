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
<<<<<<< Updated upstream
		return  "\nNumero da Conta: " + this.getAccountNumber() +
				"\nNome: " + this.people.getName() +
		        "\nCPF: " + this.people.getCPF() +
=======
		return  "\nNúmero da conta: " + this.getAccountNumber() +
				"\nNome: " + this.people.getName() +
		        "\nCPF" + this.people.getCPF() +
>>>>>>> Stashed changes
				"\nEmail: " + this.people.getEmail() +
				"\nSaldo: " + Utils.doubleToString(this.getBalance()) +
				"\n";
	}
	public void Deposit (Double value){
		if (value > 0){
			setBalance(getBalance() + value);
<<<<<<< Updated upstream
			System.out.println("Deposito realizado com sucesso!");
		}else {
			System.out.println("Não  foi possivel realizar o deposito");
=======
			System.out.println("Seu deposito foi realizado!");
		}else {
			System.out.println("Não foi possivel realizar seu deposito");
>>>>>>> Stashed changes
		}
	}

	public void Withdraw (Double value){
        if(value > 0 && this.getBalance() >= value){
            setBalance(getBalance() - value);
<<<<<<< Updated upstream
            System.out.println("Saque realizado com sucesso!");
        }else {
            System.out.println("Não foi possivel realizar o saque!");
=======
            System.out.println("Saque realizado!!");
        }else {
            System.out.println("Saque indisponivel");
>>>>>>> Stashed changes
        }
    }
    public void Transfer (BankAccount depositAccount, Double value){
        if (value > 0 && this.getBalance() >= value){
            setBalance(getBalance() - value);

            depositAccount.balance = depositAccount.getBalance() + value;
<<<<<<< Updated upstream
            System.out.println("Transferência realizada com sucesso!");
        }else{
            System.out.println("Não foi possivel realizar a tranferência");
=======
            System.out.println("Transferencia realizada com sucesso!");
        }else{
            System.out.println("Falha ao realizar a transferencia");
>>>>>>> Stashed changes
        }

    }
}
