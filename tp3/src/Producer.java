package tp3;

public class Producer extends Thread{
    private BAL bal;

    public Producer(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            bal.deposer("Lettre n" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }
}
