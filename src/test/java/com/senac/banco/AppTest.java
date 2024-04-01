package com.senac.banco;

import com.senac.banco.DB.SQLiteDB;
import com.senac.banco.main.App;
import com.senac.banco.model.BankAccount;
import com.senac.banco.model.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
/** 
 * @author Lucas Rodrigues
 * @author Júlia Gomes
 * */
public class AppTest {
	/** <code>User</code> armazena os dados de um usuario ficticio para testes */
	private User user;
	/** <code>BankAccount</code> armazena os dados da conta bancaria para testes */
    private BankAccount userBA;

    
    /**
     * Configura usuario e cria uma conta bancaria para ele
     * <b>Saldo inicial R$ <i>50.0</i></b>
     * */
    @Before
    public void setup() {
    	user = new User("Pedro", "789.159.743-51", "pedro.guilherme@gmail.com");
    	user.createBankAccount(50); 
    	userBA = BankAccount.getBankAccount(user);
    }
    
    /**
     * Testa fazendo um deposito de +20 reais
     * Se a funcionalidade de deposito funcionar
     * O saldo terá R$ 70.0
     * */
    @Test
    public void testDeposit() {
        double balance = userBA.getBalance();
        userBA.Deposit(20.0);
        assertEquals(balance + 20, userBA.getBalance(), 0.01);
    }
    
    /**
     * Testa fazendo uma transferencia de 20 reais
     * Se a funcionalidade de saque funcionar
     * O saldo do usuario terá R$ 20.0
     * E o destinatario 70
     * */
    @Test
    public void testTransfer() {
    	User destUser = new User("Arrascaeta", "915.642.483-29", "arrascaeta.giorgian@gmail.com");
    	destUser.createBankAccount(50);
    	BankAccount destUserBA = BankAccount.getBankAccount(destUser);
    	
    	userBA.Transfer(destUserBA, 30.0);
    	
    	assertEquals(userBA.getBalance() + 30, destUserBA.getBalance() - 30, 0.01);
    }
    
    /**
     * Testa fazendo um saque de 20 reais
     * Se a funcionalidade de saque funcionar
     * O saldo terá R$ 30.0
     * */
    @Test
    public void testWithdraw() {
    	boolean result = userBA.Withdraw(20.0);
    	assertTrue(result);
    }
    
    @Test
    public void testDatabaseIntegration() {
    	SQLiteDB db = new SQLiteDB();
    	
    	assertTrue(db.getConnection() != null);
    }
    
    /**
     * Libera os recursos do computador
     * após os testes terem sidos executados
     * */
    @After
    public void release() {
    	user = null;
    	userBA = null;
    }
}



