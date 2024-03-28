package com.senac.banco;

import com.senac.banco.main.App;
import com.senac.banco.model.BankAccount;
import com.senac.banco.model.User;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class BankTest {

    private BankAccount outputStreamCaptor;

    @Test
    public void testToString() {

        User user = new User("João", "123.456.789-00", "joao@example.com");
        BankAccount account = new BankAccount(user);
        account.setAccountNumber(123456);
        account.setBalance(1000.0);
        String expectedToString = "\nNumero da Conta: 123456\nNome: João\nCPF: 123.456.789-00\nEmail: joao@example.com\nSaldo: 1000.00\n";
        String actualToString = account.toString().trim();
        System.out.println("Expected: [" + expectedToString + "]");
        System.out.println("Actual: [" + actualToString + "]");
        assertEquals(expectedToString, actualToString);

    }

    @Test
    public void testDeposit() {

        User user = new User("João", "123.456.789-00", "joao@example.com");
        BankAccount account = new BankAccount(user);
        account.setAccountNumber(123456);
        account.setBalance(500.0);
        account.Deposit(200.0);
        assertEquals(700.0, account.getBalance());

    }

    @Test
    public void testWithdrawSufficientBalance() {

        User user = new User("João", "123.456.789-00", "joao@example.com");
        BankAccount account = new BankAccount(user);
        account.setAccountNumber(123456);
        account.setBalance(1000.0);
        account.Withdraw(500.0);
        assertEquals(500.0, account.getBalance());
    }

    @Test
    public void testTransferSufficientBalance() {

        User user1 = new User("João", "123.456.789-00", "joao@example.com");
        BankAccount account1 = new BankAccount(user1);
        account1.setAccountNumber(123456);
        account1.setBalance(1000.0);
        User user2 = new User("Maria", "987.654.321-00", "maria@example.com");
        BankAccount account2 = new BankAccount(user2);
        account2.setAccountNumber(654321);
        account2.setBalance(500.0);
        account1.Transfer(account2, 300.0);
        assertEquals(700.0, account1.getBalance());
        assertEquals(800.0, account2.getBalance());
    }
}



