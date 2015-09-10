

public class Counter {
    private int lBound;
    private int rBound;
    private double result;

    public Counter(int lBound, int rBound) {
        this.lBound = lBound;
        this.rBound = rBound;
        result = 0;
    }

    private double count(int i) {
        return Math.sin(i)*Math.sin(i) + Math.cos(i)*Math.cos(i);
//        return i;
    }

    public double countFunc() {
        for(int i = lBound; i < rBound; i++) {
            result += count(i);
        }
        return result;
    }
}
