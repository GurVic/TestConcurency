package pool;

import java.util.LinkedList;

public class Producer extends Thread {

    private int queueCapacity;
    private LinkedList<Object> queue;

    public Producer(LinkedList<Object> queue, int queueCapacity) {
        this.queueCapacity = queueCapacity;
        this.queue = queue;
    }

    public boolean isFull() {
        return (queue.size() >= queueCapacity)?true:false;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (isFull()) {
                    try {
                        queue.wait();
                        System.out.println("Prod '" + currentThread().getName() + "' Wait");
                    } catch (InterruptedException e) {
                    }
                }
                queue.addLast(new Object());
                System.out.println("Prod '" + currentThread().getName() + "' -> ");
                queue.notifyAll();
            }
        }
    }
}
