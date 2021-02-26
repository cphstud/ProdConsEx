import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

class FibbCalc implements Runnable {
    private int fibres;
    private int number;
    private CountDownLatch cl;
    private CyclicBarrier cb;

    public FibbCalc(int number, CyclicBarrier cb) {
        this.number = number;
        this.cb=cb;
    }

    public FibbCalc(int number, CountDownLatch cl) {
        this.number = number;
        this.cl = cl;
    }
    private int fibRecurs(int n) {
        if (n<2) {
            return n;
        }
        return fibRecurs(n-1) + fibRecurs(n-2);
    }

    @Override
    public void run() {
        fibres = fibRecurs(number);
        cl.countDown();
    }

    public int getFibres() {
        return fibres;
    }
}