package task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MaxValue {
    private int maxValue = 0;

    public synchronized int getMaxValue() {
        return maxValue;
    }

    public synchronized void setMaxValue(int value) {
        this.maxValue = value;
    }

    public long Thread(int[] array, int threads) throws Exception {
        long startTimeThread = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<Integer>> list = new ArrayList<>();
        int partSize = array.length / threads;
        for (int i = 0; i < threads; i++) {
            int startIndex = i * partSize;
            int endIndex = (i == threads - 1) ? array.length : (i + 1) * partSize;

            Callable<Integer> task = () -> {
                int nowMax = array[startIndex];
                for (int j = startIndex; j < endIndex; j++) {
                    if (array[j] > nowMax) {
                        nowMax = array[j];
                    }
                }
                return nowMax;
            };
            list.add(executor.submit(task));
        }

        int Max = list.get(0).get();
        for (Future<Integer> l : list) {
            int partMax = l.get();
            if (partMax > Max) {
                Max = partMax;
            }
        }

        setMaxValue(Max);
        executor.shutdown();
        long endTimeThread = System.nanoTime();
        return endTimeThread - startTimeThread;
    }



    public long For(int[] array) {
        long startTimeFor = System.nanoTime();
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        setMaxValue(max);
        long endTimeFor = System.nanoTime();
        return endTimeFor - startTimeFor;
    }

    public String Result(int[] array, int threads) throws Exception {
        long timeThread = Thread(array, threads);
        long timeFor = For(array);

        String res;
        if (timeThread < timeFor)
            res = ", Многопоточность производительнее!";
        else if (timeThread == timeFor)
            res = ", Одинаковая производительность!";
        else
            res = ", Цикл производительнее!";

        return String.valueOf(getMaxValue()) + res;
    }
}