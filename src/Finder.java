public abstract class Finder implements Runnable{

    protected boolean print;
    protected boolean run;

    /**
     * @param print Set true if the Finder should print results.
     */
    public Finder(boolean print) {
        this.run = false;
        this.print = print;
    }

    /**
     *  Sets print (bool) to false as default.
     */
    public Finder() {
        this.run = false;
        this.print = false;
    }

    /**
     *  The Finder will stop searching.
     */
    public void stopFinding() {
        this.run = false;
    }

    /**
     * Set, whether the Finder should print results while finding.
     * @param print true if the Finder should print results
     */
    public void setPrintWhileFinding(boolean print) {
        this.print = print;
    }

    /**
     * @return true if it is in the run-loop and active, otherwise false
     */
    public boolean isRunning() {
        return this.run;
    }

    /**
     *
     * @return true if the Finder is printing the results while finding.
     */
    public boolean isPrinting() {
        return this.print;
    }
}
