import pool.Consumer;
import pool.Producer;
import pool.SimpleExecutor;

import java.util.LinkedList;

public class Runner {
    private static final int K = 10;
    private static final int N = 5;
    private static double result = 0;
    private static int delta = 2;

    public static void main(String[] args) throws InterruptedException {

//        SimpleExecutor executor = new SimpleExecutor(10);

        int queueMaxSize = 10;
        int capacity = 2;

        Consumer consumers[] = new Consumer[capacity];
        Producer producers[] = new Producer[capacity];

        LinkedList<Object> queue = new LinkedList<>();

        for (int i = 0; i < capacity; i++) {
            consumers[i] = new Consumer(queue);
        }
        for (int i = 0; i < capacity; i++) {
            producers[i] = new Producer(queue, queueMaxSize);
        }
        for (int i = 0; i < capacity; i++) {
            producers[i].start();
            consumers[i].start();
        }

//        for(int i = 0; i < K; i+=delta) {
//            final int lB = i;
//            final int rB = i+delta;
//            Thread tr = new Thread(() -> {
//                Counter counter = new Counter(lB, rB);
//                System.out.println("[" + lB + "," + rB + "]");
//                result += counter.countFunc();
//                System.out.println("result - " + result);
//            });
//            tr.start();
//            tr.join();
//        }
//
//        System.out.println("FINAL result - " + result);
    }
}
