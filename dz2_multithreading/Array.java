package task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Array {
    private int sum = 0;

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }

    public synchronized void setValue(int value) {
        sum += value;
    }

    public long oneThread(int[] array) {
        int sum = 0;

        long startTime = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        setSum(sum);

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public long moreThread(int[] array, int threads) throws Exception {
        long startTime = System.nanoTime();

        ExecutorService executor = Executors.newFixedThreadPool(threads);
        List<Future<?>> list = new ArrayList<>();
        int partSize = array.length / threads;
        for (int i = 0; i < threads; i++) {
            int startIndex = i * partSize;
            int endIndex = (i == threads - 1) ? array.length : (i + 1) * partSize;

            Runnable task = () -> {
                for (int j = startIndex; j < endIndex; j++) {
                    setValue(array[j]);
                }
            };
            list.add(executor.submit(task));
        }

        for (Future<?> l : list) {
            l.get();
        }

        executor.shutdown();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
}