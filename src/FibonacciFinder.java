import java.math.BigInteger;
import java.util.ArrayList;

public class FibonacciFinder extends Finder {

    private ArrayList<Long> fibonacciNumbers;
    private int fibonacciToPrint;
    //boolean print; (from parent class)
    //boolean run; (from parent class)

    public FibonacciFinder() {
        fibonacciNumbers = new ArrayList<>();
        fibonacciNumbers.add(0L);
        fibonacciNumbers.add(1L);
        fibonacciToPrint = -1;
    }

    public void run() {
        super.run = true;
        while (fibonacciNumbers.size() <= 92) { //because of overflow, maybe use BigInteger https://stackoverflow.com/questions/44413304/unsigned-long-in-java
            calculateNextFibonacciNumber();
            if (fibonacciToPrint == fibonacciNumbers.size() - 1) {
                System.out.println("With a(0) = 0 and a(1) = 1, the Fibonacci-Number is a("+ fibonacciToPrint +") = " + (Long.valueOf(fibonacciNumbers.get(fibonacciToPrint))) + ".");
                stopFinding();
            }
            if (super.print) {
                System.out.print(fibonacciNumbers.size() - 1 + ") " + fibonacciNumbers.get(fibonacciNumbers.size() - 1) + " ");
            }
        }
    }

    public void calculateNextFibonacciNumber() {
        fibonacciNumbers.add(Long.valueOf(fibonacciNumbers.get(fibonacciNumbers.size() - 2).longValue() + fibonacciNumbers.get(fibonacciNumbers.size() - 1).longValue()));
    }

    /*
    public void stopFinding() {
        this.run = false;
    }

    public void setPrintWhileFinding(boolean print) {
        this.print = print;
    } */

    /**
     *
     * @param fibonacciToPrint -1 if nothing should be printed, otherwise the n in a(n)
     */
    public void setFibonacciToPrint(int fibonacciToPrint) {
        if (fibonacciToPrint < -1)
            fibonacciToPrint = -1;
        this.fibonacciToPrint = fibonacciToPrint;
    }

    public long getLatestFibonacciNumber() {
        return fibonacciNumbers.get(fibonacciNumbers.size() - 1);
    }

    public int getNumberOfFoundFibonaccis() {
        return fibonacciNumbers.size();
    }

    public static long getFibonacciNumberRek(long n) {
        if (n > 40) {
            System.err.println("You should create a thread because otherwise it would take too long to calculate.");
            return -1;
        }
        if (n <= 1) {
            return n;
        }
        return getFibonacciNumberRek(n - 1) + getFibonacciNumberRek(n - 2);
    }
}
