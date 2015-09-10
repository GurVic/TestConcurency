package pool;

import java.util.LinkedList;
import java.util.List;

public class Consumer extends Thread {

    private LinkedList<Object> queue;

    public Consumer(LinkedList<Object> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                        System.out.println("C ' " + currentThread().getName() + "' Wait");
                    } catch (InterruptedException e) {
                    }
                }
                Object obj =  queue.removeFirst();
                System.out.println("Cons ' " + currentThread().getName() + "' <- ");
                queue.notifyAll();
            }
        }
    }
}
