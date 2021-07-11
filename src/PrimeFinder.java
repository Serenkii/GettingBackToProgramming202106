import java.util.ArrayList;

public class PrimeFinder extends Finder {

    ArrayList<Long> primeList;
    private long latestPrime;
    //boolean print; (from parent class)
    //boolean run; (from parent class)

    /**
     *
     * @param print Set true if you want to get every found prime displayed.
     */
    public PrimeFinder(boolean print) {
        super(print);
        primeList = new ArrayList<>();
    }

    /**
     *  run()-method responsible for finding primes and saving them in primeList
     */
    @Override
    public void run() {
        super.run = true;
        if (primeList.isEmpty() || primeList.size() < 2) {
            primeList.add((long) 2);
            latestPrime = 3;
            primeList.add(latestPrime);
            if (print)
                System.out.print("2 3 ");
        }
        else {
            latestPrime = primeList.get(primeList.size() - 1);
        }
        for (long i = latestPrime + 2; i < Long.MAX_VALUE && run; i += 2) {
            if (isPrime(i)) {
                primeList.add(i);
                latestPrime = i;
                if (print)
                    System.out.print(i + " ");
            }
        }
        //System.err.println("DEBUG: run() from PrimeFinder has reached its end and the thread should be finished");
    }

    /*
    /**
     *  The thread will stop finding primes.
     *
    public void stopFinding() {
        run = false;
    }

    public boolean isRunning() {
        return run;
    }

    public boolean isPrinting() {
        return print;
    }

    /**
     * Decide whether found primes should be printed.
     * @param print Set true if you want to get every found prime displayed.
     *
    public void setPrintWhileFinding(boolean print) {
        this.print = print;
    } */

    /**
     * @return the latest found prime number.
     */
    public long getLatestPrime() {
        return latestPrime;
    }

    /**
     *
     * @param number The number that is tested if it is a prime number.
     * @return true if the number is a prime number, otherwise false
     */
    public static boolean isPrime(long number) {
        if (number < 2)
            return false;
        if (number == 2 || number == 3)
            return true;
        if (number % 2 == 0)
            return false;
        for (long i = 3; i <= number / 2; i += 2) {
            if (number % i == 0)
                return false;
        }
        return true;
    }
}
