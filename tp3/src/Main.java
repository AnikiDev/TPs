package tp3;

public class Main {
    public static void main(String[] args) {
        BAL bal = new BAL();
        new Producer(bal).start();
        new Consumer(bal).start();
    }
}
