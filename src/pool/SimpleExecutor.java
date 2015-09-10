package pool;

import java.util.LinkedList;

public class SimpleExecutor {

    private int amount;
    private PWorker[] threads;
    public LinkedList goals;

    public SimpleExecutor(int num) {
        this.amount = num;
    }

    public void init() {
        goals = new LinkedList();
        threads = new PWorker[amount];

        for (int i = 0; i < amount; i++) {
            threads[i] = new PWorker();
            threads[i].start();
        }
    }

    public boolean haveWork() {
        return (goals.size() > 0)?true:false;
    }

    public void execute(Runnable r) {
        synchronized (goals) {
            goals.addLast(r);
            goals.notify();
        }
    }

    private class PWorker extends Thread {
        public void run() {
            Runnable r;

            while (true) {
                synchronized (goals) {
                    while (goals.isEmpty()) {
                        try {
                            goals.wait();
                        } catch (InterruptedException e) {
                        }
                    }

                    r = (Runnable) goals.removeFirst();
                }

                try {
                    r.run();
                } catch (RuntimeException e) {
                }
            }
        }
    }
}
