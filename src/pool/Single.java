package pool;

public class Single {
    private final static Single INSTANCE = new Single();

    private Single() {
    }

    public static Single getInstance() {
        return INSTANCE;
    }
}


