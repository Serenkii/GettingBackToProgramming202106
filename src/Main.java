public class Main {

    PrimeFinder primeFinder;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        System.out.println("Getting back to Java...");
        InputManager inputThread = new InputManager(this);
        inputThread.start();
        primeFinder = new PrimeFinder(false);
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

}
