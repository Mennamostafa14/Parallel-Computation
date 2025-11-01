public class Main {
    static int counter = 0;

    public static void main(String[] args) {

        // Example 1: Handling exceptions using try-catch
        Thread t1 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " started.");
                int result = 100 / 0;
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " caught: " + e);
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
        });

        Thread t2 = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " started.");
                int result = 50 / 0;
            } catch (Exception e) {
                System.out.println(Thread.currentThread().getName() + " caught: " + e);
            }
            System.out.println(Thread.currentThread().getName() + " finished.");
        });

        t1.start();
        t2.start();

        // Example 2: UncaughtExceptionHandler (per-thread)
        Thread t3 = new Thread(() -> {
            System.out.println("Thread started: " + Thread.currentThread().getName());
            int x = 10 / 0;
        });
        t3.setUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("Exception in " + thread.getName() + ": " + exception.getMessage());
        });
        t3.start();

        // Example 3: DefaultUncaughtExceptionHandler (global)
        Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("Global handler caught exception in: " + thread.getName());
            System.out.println("Error: " + exception.getMessage());
        });

        Thread t4 = new Thread(() -> {
            throw new RuntimeException("Thread crashed!");
        });

        Thread t5 = new Thread(() -> {
            throw new ArithmeticException("Division by zero!");
        });

        t4.start();
        t5.start();

        // Example 4: Race Condition demonstration
        Thread r1 = new Thread(new MyTask(), "Counter-1");
        Thread r2 = new Thread(new MyTask(), "Counter-2");

        r1.start();
        r2.start();
    }

    static class MyTask implements Runnable {
        public void run() {
            for (int i = 0; i < 5; i++) {
                int current = counter;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int updated = ++counter;
                System.out.println(Thread.currentThread().getName() +
                        " -> Current: " + current + ", Updated: " + updated);
            }
        }
    }
}