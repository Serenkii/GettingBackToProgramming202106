public class Main {

    private PrimeFinder primeFinder;
    private FibonacciFinder fibonacciFinder;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        System.out.println("Getting back to Java...");
        InputManager inputThread = new InputManager(this);
        inputThread.start();
        primeFinder = new PrimeFinder(false);
        fibonacciFinder = new FibonacciFinder();
    }

    public void startPrimeFinder(boolean printPrimes) {
        primeFinder.setPrintWhileFinding(printPrimes);
        if (primeFinder.isRunning())
            return;
        new Thread(primeFinder).start();
    }

    public PrimeFinder getPrimeFinder() {
        return primeFinder;
    }

    public void stopPrimeFinder() {
        if (primeFinder == null || !primeFinder.isRunning())
            return;
        primeFinder.stopFinding();
        System.out.println("The last found prime is " + primeFinder.getLatestPrime());
        //System.err.println("DEBUG: It will stop finding");
    }

    public void startFibonacciFinder(boolean printFibonacciNumbers) {
        fibonacciFinder.setPrintWhileFinding(printFibonacciNumbers);
        if (fibonacciFinder.isRunning())
            return;
        fibonacciFinder.setFibonacciToPrint(-1);
        new Thread(fibonacciFinder).start();
    }

    public void startFibonacciFinder(long fibonacciToPrint) {       //TODO: If the ArrayList in FibonacciFinder has already found the number, it won't be printed.
        fibonacciFinder.setPrintWhileFinding(false);
        fibonacciFinder.setFibonacciToPrint((int) fibonacciToPrint);
        if (fibonacciFinder.isRunning())
            return;
        new Thread(fibonacciFinder).start();
    }

    public void stopFibonacciFinder() {
        if (fibonacciFinder == null || !fibonacciFinder.isRunning())
            return;
        fibonacciFinder.stopFinding();
        System.out.println("The last found fibonacci number is a(" + fibonacciFinder.getNumberOfFoundFibonaccis() + ") = " + fibonacciFinder.getLatestFibonacciNumber());
    }

    public FibonacciFinder getFibonacciFinder() {
        return fibonacciFinder;
    }


}
