package sages;

public class Runner {
    public static void main(String[] args) {
        SagesEating sagesEating = new SagesEating(5);
        sagesEating.init();
        System.out.println("------------ start eating ---------------");

        sagesEating.startEating();

    }
}
