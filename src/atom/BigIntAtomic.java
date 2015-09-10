package atom;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class BigIntAtomic extends AtomicReference<BigInteger>{

//    private volatile BigInteger bigInteger;

    public BigIntAtomic(Integer initialValue) {
        super(BigInteger.valueOf(initialValue));
//        bigInteger = BigInteger.valueOf(initialValue);
    }

    private static final BigInteger TWO = new BigInteger("2");

    public BigInteger next() {
        for(;;) {
            BigInteger bigInteger = get();
            bigInteger = bigInteger.multiply(TWO);
            if(compareAndSet(get(), bigInteger)) return get();
        }
    }

    public static void main(String[] args) {

//        ExecutorService executor = new ThreadPoolExecutor(
//                5,
//                10,
//                100L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingDeque<Runnable>(30),
//                new ThreadPoolExecutor.CallerRunsPolicy()
//        );
//        System.out.println("Main thread id " + Thread.currentThread().getId());
//
//        for (int i = 0; i < 100; i++) {
//            executor.submit();
//        }
//
//        executor.shutdown();
        final BigIntAtomic bia = new BigIntAtomic(1);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(bia.next());
                }
            }).start();
        }
    }
}
