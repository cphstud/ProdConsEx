import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Consumer implements Runnable {
    private BlockingQueue<Integer> inputFromFibbQueue;
    private int sumQueue;
    //private List<Integer> sumList;
    private ArrayBlockingQueue<Integer> sumList;

    public Consumer(BlockingQueue<Integer> input) {
        inputFromFibbQueue = input;
        sumList = new ArrayBlockingQueue<>(8);
    }

    private void printSum() throws InterruptedException {
        int totalSum=0;
        System.out.println(Thread.currentThread().getName() + " in printer" );
        for (int i : sumList) {
            System.out.println(Thread.currentThread().getName() + " in loop" + i );
           totalSum +=sumList.take();
        }
        System.out.println("Sum: " + totalSum);
    }

    @Override
    public void run() {
        while (true) {
            try {
                int fibNum = inputFromFibbQueue.take();
                System.out.println("Consumed: " + fibNum);
                sumList.add(fibNum);
                printSum();
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
