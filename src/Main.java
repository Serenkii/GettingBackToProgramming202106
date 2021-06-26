public class Main {

    PrimeFinder primeFinder;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        System.out.println("Getting back to Java...");
        InputManager inputThread = new InputManager(this);
        inputThread.start();
    }

    public void startPrimeFinder(boolean printPrimes) {
        if (primeFinder == null)
            primeFinder = new PrimeFinder(printPrimes);
        else
            primeFinder.printWhileFinding(printPrimes);
        if (primeFinder.isAlive())
            return;
        primeFinder.start();        //https://stackoverflow.com/questions/8072933/why-does-an-illegalthreadstateexception-occur-when-thread-start-is-called-again -
                                    // you can start threads only once, then you need a new instance of primeFinder -- maybe use Runnable ?
    }

    public void stopPrimeFinder() {
        if (primeFinder == null || !primeFinder.isRunning())
            return;
        primeFinder.stopFinding();
        System.out.println("The last found prime is " + primeFinder.getLatestPrime());
        System.err.println("DEBUG: It will stop finding");
    }

}
