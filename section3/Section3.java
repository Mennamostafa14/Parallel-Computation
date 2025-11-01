class Main {
    public static void main(String[] args) {

        // Create and start a thread by extending Thread
        MyThread t1 = new MyThread("Downloader");
        t1.start();

        // Create and start a thread by using a lambda (Runnable)
        Thread t = new Thread(() -> {
            System.out.println("Running: " + Thread.currentThread().getName());
        });
        t.setName("Worker-1");
        t.start();

        // Runnable task with custom name
        Runnable task = () -> System.out.println("Task running in: " + Thread.currentThread().getName());
        Thread t2 = new Thread(task, "ParallelTask");
        t2.start();

        // PRIORITY demo
        Thread t3 = new Thread(() -> System.out.println("T3"));
        Thread t4 = new Thread(() -> System.out.println("T4"));

        t3.setPriority(Thread.MAX_PRIORITY); // 10
        t4.setPriority(Thread.MIN_PRIORITY); // 1

        t4.start();
        t3.start();

        // ThreadGroup demo
        ThreadGroup group = new ThreadGroup("New_Group");
        Thread t5 = new Thread(group, () -> System.out.println("Task 1 running"));
        Thread t6 = new Thread(group, () -> System.out.println("Task 2 running"));

        t5.start();
        t6.start();
        group.list();

        // User thread example
        Thread t7 = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("User thread working...");
                try {
                    Thread.sleep(500);
                } catch (Exception e) {}
            }
        });
        t7.start();

        // Daemon thread example
        Thread t8 = new Thread(() -> {
            while (true) {
                System.out.println("Daemon running...");
                try {
                    Thread.sleep(500);
                } catch (Exception e) {}
            }
        });
        t8.setDaemon(true); 
        t8.start();

        System.out.println("Main finished");
    }
}

class MyThread extends Thread {
    public MyThread(String name) {
        super(name);  
    }

    @Override
    public void run() {
        System.out.println("Thread running: " + getName());
    }
}