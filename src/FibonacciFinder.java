import java.util.ArrayList;

public class FibonacciFinder {
    //ArrayList<Long> fibonacciNumbers;

    public FibonacciFinder() {
        //fibonacciNumbers = new ArrayList<>();
    }


    public static long getFibonacciNumber(long n) {
        if (n <= 1) {
            return n;
        }
        return getFibonacciNumber(n - 1) + getFibonacciNumber(n - 2);
    }


}
