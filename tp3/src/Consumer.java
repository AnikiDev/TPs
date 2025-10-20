package tp3;

public class Consumer extends Thread{
    private BAL bal;

    public Consumer(BAL bal) {
        this.bal = bal;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            bal.retirer();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {}
        }
    }
}
