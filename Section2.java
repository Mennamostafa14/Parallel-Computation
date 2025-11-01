class Main {

    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();

        CounterRunnable r1 = new CounterRunnable();
        CounterRunnable r2 = new CounterRunnable();

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        c1.start(); 
        c2.start();
        t1.start(); 
        t2.start();
    }
}

class Counter extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(i + " - " + Thread.currentThread().getName());
        }
    }
}

class CounterRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(i + " - " + Thread.currentThread().getName());
        }
    }
}