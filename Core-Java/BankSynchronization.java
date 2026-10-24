public class BankSynchronization {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();

        Thread t1 = new Thread(() -> account.withdraw(700));
        Thread t2 = new Thread(() -> account.withdraw(500));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final balance: " + account.balance);
    }
}

class BankAccount {
    int balance = 1000;

    synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName()
                    + " withdrawing " + amount);

            balance -= amount;
            System.out.println("Remaining balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName()
                    + ": Insufficient balance for " + amount);
        }
    }
}