package sages;

public class SagesEating {

    private int resourceCount;
    private Prong prongs[];

    public SagesEating(int resourseCount) {
        this.resourceCount = resourseCount;
        prongs = new Prong[resourseCount];
    }

    public void init() {
        for (int i = 0; i < resourceCount; i++) {
            prongs[i] = new Prong(i);
        }
    }

    private void eat(int leftProngId, int rightProngId) throws InterruptedException {
        Prong first;
        Prong second;
        // synch by min ID order
        if (leftProngId < rightProngId) {
            first = prongs[leftProngId];
            second = prongs[rightProngId];
        } else {
            first = prongs[rightProngId];
            second = prongs[leftProngId];
        }
        synchronized (first) {
            synchronized (second) {
                // EATING
                    System.out.println("eat :" + Thread.currentThread().getName());
                    Thread.sleep((long) (100*Math.random()));
            }
        }
    }

    public void startEating() {

        // 5 sages ( threads )
        for (int i = 0; i < resourceCount; i++) {
            final int fI = i;
            new Thread(() -> {
                // setting id for left & right prongs
                Prong first;
                Prong second;
                if(fI == (resourceCount-1)) {
                    first = prongs[0];
                    second = prongs[fI];
                } else {
                    first = prongs[fI];
                    second = prongs[fI+1];
                }

                // unstoppable eating
                while (true) {
                    try {
                        // synch by min ID order
                        synchronized (first) {
                            synchronized (second) {
                                // EATING
                                System.out.println("eat :" + Thread.currentThread().getName());
                                Thread.sleep((long) (100*Math.random()));
                            }
                        }


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}