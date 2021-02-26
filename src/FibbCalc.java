import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

class FibbCalc implements Runnable {
    private int fibres;
    private int number;

    public FibbCalc(int number) {
        this.number = number;
    }

    private int fibRecurs(int n) {
        if (n<2) {
            return n;
        }
        return fibRecurs(n-1) + fibRecurs(n-2);
    }

    @Override
    public void run() {
        String col="";
        String name = Thread.currentThread().getName();
        switch (name) {
            case "Producer1":col=Col.ANSI_CYAN;break;
            case "Producer2":col=Col.ANSI_GREEN;break;
            case "Producer3":col=Col.ANSI_RED;break;
            case "Producer4":col=Col.ANSI_PURPLE;break;
            default:col=Col.ANSI_BLACK;
        }
        System.out.println(col + Thread.currentThread().getName() + " and " + name + " is crunching");
        fibres = fibRecurs(number);
    }

    public int getFibres() {
        return fibres;
    }
}