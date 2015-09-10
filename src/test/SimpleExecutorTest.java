package test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Before;
import org.junit.Test;
import pool.SimpleExecutor;

import java.util.Random;

public class SimpleExecutorTest {

    private SimpleExecutor executor;

    @Before
    public void setup() {
        executor = new SimpleExecutor(10);
        executor.init();
    }

    @Test
    public void runWhitBigAmmountOfWork() {
        for (int i = 0; i < 50; i++) {
            final int finalI = i;
            Thread th = new Thread(() -> {
                Thread.currentThread().setName("work " + finalI);
                Random rand = new Random();
                try {
                    Thread.currentThread().sleep(1000 + rand.nextInt(3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("work '" + Thread.currentThread().getName() + "' finished");
            });
            executor.execute(th);
        }
        while (executor.haveWork()) {
//            System.out.println("| " +executor.queue.size() + " |");
        }
    }

    @Test
    public void runWhitNormalAmmountOfWork() throws InterruptedException {
        for (int i = 0; i < 1; i++) {
            final int finalI = i;
            Thread th = new Thread(() -> {
                Thread.currentThread().setName("work " + finalI);
                Random rand = new Random();
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("work '" + Thread.currentThread().getName() + "' finished");
            });
            executor.execute(th);
        }
        System.out.println(executor.haveWork());
        while (executor.haveWork()) {
            Thread.sleep(2500);
            System.out.println("| " + executor.goals.size() + " |");
        }

    }

}
