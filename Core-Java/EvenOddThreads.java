public class EvenOddThreads {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new EvenThread();
        Thread t2 = new Thread(new OddTask());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Both threads finished.");
    }
}

class EvenThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0)
                System.out.println("Even: " + i);
        }
    }
}

class OddTask implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            if (i % 2 != 0)
                System.out.println("Odd: " + i);
        }
    }
}