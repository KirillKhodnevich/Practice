import task1.Counter;
import task2.Array;
import task3.MaxValue;
import task4.Timer;
import task5.TwoPeopleTwoSpoons;
import static java.lang.Thread.sleep;

public class Runner {
    public static void main() throws Exception {
        //Задание 1
        Counter counter = new Counter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                counter.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                counter.increment();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(counter.getCount());

        //Задание 2
        Array arrayProcessor = new Array();

        int[] array = new int[1000];
        for (int i = 0; i < array.length; i++) {
            array[i] = i + 1;
        }

        long singleTime = arrayProcessor.oneThread(array);
        int singleSum = arrayProcessor.getSum();
        System.out.println("Однопоточный результат: " + singleSum);
        System.out.println("Время: " + singleTime + " наносекунд");

        long multiTime = arrayProcessor.moreThread(array, 4);
        int multiSum = arrayProcessor.getSum();
        System.out.println("Многопоточный результат: " + multiSum);
        System.out.println("Время: " + multiTime + " наносекунд");

        System.out.println("Результаты совпадают: " + (singleSum == multiSum));
        if (multiTime < singleTime) {
            System.out.println("Многопоточный метод быстрее на " + (singleTime - multiTime) + " нс");
        } else {
            System.out.println("Однопоточный метод быстрее на " + (multiTime - singleTime) + " нс");
        }

        //Задание 3
        MaxValue finder = new MaxValue();
        int[] Array = {3, 7, 2, 9, 1, 8, 5, 4, 6};
        String result = finder.Result(Array, 4);
        System.out.println(result);

        //Задание 4
        Timer first = new Timer();
        first.start();

        Thread second  = new Thread(() -> {
            try {
                sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Второй поток останавливает первый");
            first.interrupt();
        });

        second.start();
        first.join();
        second.join();

        //Задание 5
        TwoPeopleTwoSpoons test = new TwoPeopleTwoSpoons();
        test.deadlock(false);
    }
}