package test;

import bank.Bank;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class BankTest {

    private Bank bank;

    @Before
    public void init() {
        bank = new Bank(100);
        bank.init();
        System.out.println("Bank Balance before tests: " + bank.getBankBalance());
    }

    @Test
    public void testAccountTransfer() {
        for (int i = 0; i < 2000; i++) {
            new Thread(() -> {
                bank.transfer(
                        bank.getAccount((int) (Math.random() * bank.getAccNum())),
                        bank.getAccount((int) (Math.random() * bank.getAccNum())),
                        (int)( (Math.random()+1) * 10)
                );
            }).start();
        }
    }

    @After
    public void finish() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Bank Balance after tests: " + bank.getBankBalance());
    }
}
