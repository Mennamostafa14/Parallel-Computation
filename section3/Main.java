import java.util.List;

public class Main {
    private final List<Runnable> tasks;

    public Main(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    public void executeAll() {
        for (Runnable task : tasks) {
            Thread t = new Thread(task);
            t.start();
        }
    }

    public static void main(String[] args) {
        Runnable task1 = () -> System.out.println("Task 1 running by " + Thread.currentThread().getName());
        Runnable task2 = () -> System.out.println("Task 2 running by " + Thread.currentThread().getName());
        Runnable task3 = () -> System.out.println("Task 3 running by " + Thread.currentThread().getName());

        Main executor = new Main(List.of(task1, task2, task3));
        executor.executeAll();
    }
}