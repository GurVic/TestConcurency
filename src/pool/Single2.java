package pool;

public class Single2 {

    private static volatile Single2 instance;

    private Single2() {
    }

    public static Single2 getInstance() {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new Single2();
                }
            }
        }
        return instance;
    }
}
