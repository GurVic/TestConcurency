package bank;

class Account {
    private final int id;
    private volatile int balance;

    public Account(int balance, int id) {
        this.id = id;
        this.balance = balance;
    }

    public void put(int amount) {
        this.balance += amount;
    }

    public void get(int amount) {
        this.balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Account{" + id + "}";
    }
}
