package task4;

public class Timer extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Первый поток остановлен");
                return;
            }
            System.out.println("Первый поток работает");
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}