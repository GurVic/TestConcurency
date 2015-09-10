package bank;

public class Bank {

    private int accNum;
    private Account accounts[];

    public Bank(int count) {
        accNum = count;
        accounts = new Account[accNum];
    }

    public void init() {
        for (int i = 0; i < accNum; i++) {
            Account tempAcc = new Account((int)((Math.random()+1)*100), i);
            accounts[i] = tempAcc;
//            System.out.println("Bal " + i + " : " + accounts[i].getBalance());
        }
    }

    public void transfer(Account fromAcc, Account toAcc, int amount) {
        Account first;
        Account second;
        // synch by min ID order
        if (fromAcc.getId() < toAcc.getId()) {
            first = fromAcc;
            second = toAcc;
        } else {
            first = toAcc;
            second = fromAcc;
        }
        synchronized (first) {
            synchronized (second) {
                if (fromAcc.getBalance() >= amount) {
                    fromAcc.get(amount);
                    toAcc.put(amount);
//                    System.out.println(fromAcc.getId()+ " -> " + toAcc.getId() + " " + amount);
                }
            }
        }
    }

    public Account getAccount(int i) {
        return accounts[i];
    }

    public int getAccNum() {
        return accNum;
    }

    public int getBankBalance() {
        int tempBalance = 0;
        for (int i = 0; i < accNum; i++) {
            tempBalance += accounts[i].getBalance();
        }
        return tempBalance;
    }
}


