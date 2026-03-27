package task5;

public class TwoPeopleTwoSpoons extends Thread {
    public Object spoon1 = new Object();
    public Object spoon2 = new Object();

    public void deadlock(boolean activate) {
        if (activate) {
            Thread person1 = new Thread(() -> {
                synchronized (spoon1) {
                    System.out.println("Поток 1 взял spoon1");
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e) {}

                    synchronized (spoon2) {
                        System.out.println("Поток 1 взял spoon2");
                    }
                }
            });


            Thread person2 = new Thread(() -> {
                synchronized (spoon2) {
                    System.out.println("Поток 2 взял spoon2");
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e) {}

                    synchronized (spoon1) {
                        System.out.println("Поток 2 взял spoon1");
                    }
                }
            });
            person1.start();
            person2.start();
        }
        else {
            Thread person1 = new Thread(() -> {
                synchronized (spoon1) {
                    System.out.println("Поток 1 взял spoon1");
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e) {}

                    synchronized (spoon2) {
                        System.out.println("Поток 1 взял spoon2");
                    }
                }
            });

            Thread person2 = new Thread(() -> {
                synchronized (spoon1) {
                    System.out.println("Поток 2 взял spoon1");
                    try {
                        Thread.sleep(100);
                    }
                    catch (InterruptedException e) {}

                    synchronized (spoon2) {
                        System.out.println("Поток 2 взял spoon2");
                    }
                }
            });
            person1.start();
            person2.start();
        }
    }
}