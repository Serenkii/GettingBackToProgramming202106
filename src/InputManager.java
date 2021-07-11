import java.util.ArrayList;
import java.util.Scanner;

public class InputManager extends Thread{

    Main programManager;
    String command;
    ArrayList<String> inputHistory;
    Scanner inputScanner;

    public InputManager(Main programManager) {
        inputHistory = new ArrayList<>();
        inputScanner = new Scanner(System.in);
        this.programManager = programManager;
    }

    @Override
    public void run() {
        while (true) {
            if(inputScanner.hasNextLine()) {
                this.command = inputScanner.nextLine();
                inputHistory.add(this.command);
                executeCommand();
            }
        }
    }

    public void executeCommand() {
        String[] commandArr = this.command.split(" ", 2);
        String mainCommand = commandArr[0];

        switch (mainCommand) {
            case "stop":
                System.out.println("The Program will be stopped.");
                System.exit(0);
                break;

            case "history":         //fallthrough
            case "commandHistory":  //fallthrough
            case "inputHistory":                //TODO with and without brackets
                printInputHistory(true);
                break;

            case "help":
                System.out.println("Not implemented yet...");
                break;

            case "isPrime":
                if (!commandIsPrime(commandArr))        //Error:
                    System.out.println("Call the method like that: \"isPrime <number>\"");
                break;

            case "primes":
            case "calculatePrimes":
            case "calculatePrimeNumbers" :
                if(!commandCalculatePrimes(commandArr))
                    System.out.println("Call the method either like that \"calculatePrimes <printPrimes>\" or just \"calculatePrimes\"");
                break;
                
            case "stopPrimes":
            case "stopCalculatePrimes":
            case "stopCalculatingPrimes":
            case "stopCalculatePrimeNumbers" :
            case "stopCalculatingPrimeNumbers" :
                this.programManager.stopPrimeFinder();
                break;

            case "":
                if (!programManager.getPrimeFinder().isRunning() || !programManager.getPrimeFinder().isPrinting())
                    break;
                System.out.println("\n\nAs it can be difficult typing out commands while the found prime numbers are printed, the system stopped");
                System.out.println("printing out these numbers now. It is still calculating. If you want to see it again type \"primes true\".");
                System.out.println("If you want to stop calculating, type \"stopPrimes\".\n");
                programManager.getPrimeFinder().setPrintWhileFinding(false);
                break;

            case "getFib" :
            case "getFibonacci":
            case "getFibonacciNumber":
                Long numberArgument = checkForLongArgument(commandArr, true);
                if (numberArgument == null) {
                    System.out.println("Call the method like that: \"getFibonacciNumber <number>\"");
                    break;
                }

                if (numberArgument.longValue() <= 40) {
                    System.out.println(FibonacciFinder.getFibonacciNumberRek(numberArgument.longValue()));
                    break;
                }
                programManager.startFibonacciFinder(numberArgument.longValue());

                break;

            case "fibs":
            case "calculateFibs":
            case "calculateFibonacci's":
            case "calculateFibonaccis":
            case "calculateFibonacciNumbers":
                commandCalculateFibonacciNumbers(commandArr);
                break;

            case "stopFibs":
            case "stopCalculateFibs":
            case "stopCalculatingFibs":
            case "stopCalculateFibonacci's":
            case "stopCalculatingFibonacci's":
            case "stopCalculateFibonaccis":
            case "stopCalculatingFibonaccis":
            case "stopCalculateFibonacciNumbers":
            case "stopCalculatingFibonacciNumbers":
                programManager.stopFibonacciFinder();
                break;

            default:
                System.out.println("\"" + this.command +"\" is an unknown command!");
                System.out.println("Type \"help\" for help regarding the commands.");
                break;
        }
    }

    /**
     * @param commandArr The array of commands the user typed in.
     * @return true if the the typed command has at least one argument which isn't blank, otherwise false
     */
    private boolean hasAnyArguments(String[] commandArr) {
        if (commandArr == null)
            return false;
        if (commandArr.length <= 1)
            return false;
        if (commandArr[1].isBlank())
            return false;

        return true;
    }

    /** Check whether there is a valid long argument in commandArr[1].
     *
     * @param commandArr The array of commands the user typed in.
     * @param printMessages true if error messages should be printed
     * @return null if there was no valid LongArgument or the length of the array != 0, otherwise the correct long-number
     */
    private Long checkForLongArgument(String[] commandArr, boolean printMessages) {
        if (commandArr == null) {
            System.err.println("commandArr in InputManager.commandIsPrime(String[] commandArr) is null!");
            return null;
        }
        if (commandArr.length != 2) {
            if (printMessages)
                System.out.println("No argument.");
            return null;
        }
        String argument = commandArr[1];
        long number;
        try {
            number = Long.parseLong(argument);
        }
        catch (Exception e) {
            if (printMessages)
                System.out.println("Your argument is not a number.");
            return null;
        }
        return number;
    }

    /**
     *
     * @param commandArr The array of commands the user typed in.
     * @param printMessages true if error messages should be printed
     * @return null if there was no valid boolean argument, otherwise the respective boolean argument
     */
    private Boolean checkForBooleanArgument(String[] commandArr, boolean printMessages) {
        if (commandArr == null) {
            System.err.println("commandArr in InputManager.commandIsPrime(String[] commandArr) is null!");
            return null;
        }
        if (commandArr.length != 2) {
            if (printMessages)
                System.out.println("No argument.");
            return null;
        }
        String argument = commandArr[1];
        if (argument.equalsIgnoreCase("true"))
            return true;
        if (argument.equalsIgnoreCase("false"))
            return false;
        return null;
    }

    private void commandCalculateFibonacciNumbers(String[] commandArr) {
        if (!hasAnyArguments(commandArr)) {
            programManager.startFibonacciFinder(false);
            return;
        }

        Boolean booleanArgument = checkForBooleanArgument(commandArr, false);
        Long numberArgument = checkForLongArgument(commandArr, false);

        System.err.println("DEBUG:");

        if (booleanArgument == null && numberArgument == null) {
            System.err.println("User typed commands that weren't detected as commands but also aren't boolean or long.");
            return;
        }
        if (booleanArgument != null && numberArgument != null) {
            System.err.println("Somehow the command had a numberArgument and a booleanArgument at the same time...");
            return;
        }

        if (booleanArgument != null) {
            programManager.startFibonacciFinder(booleanArgument.booleanValue());
            return;
        }
        if (numberArgument != null) {
            programManager.startFibonacciFinder(numberArgument.longValue());
            return;
        }
    }

    private boolean commandCalculatePrimes(String[] commandArr) {
        if (commandArr == null)
            return false;

        boolean printPrimes;
        if (commandArr.length != 2 || commandArr[1].isBlank()) {
            programManager.startPrimeFinder(false);
            return true;
        }
        printPrimes = Boolean.parseBoolean(commandArr[1]);      //doesn't need try & catch, it returns false if the String isn't "true"

        programManager.startPrimeFinder(printPrimes);
        return true;
    }

    private boolean commandIsPrime(String[] commandArr) {
        Long number = checkForLongArgument(commandArr, true);
        if (number == null)
            return false;
        System.out.println(PrimeFinder.isPrime(number.longValue()));
        return true;
    }


    public void printInputHistory(boolean brackets) {
        for (String input : inputHistory) {
            if (brackets)
                System.out.print("[\"");
            System.out.print(input);
            if (brackets)
                System.out.print("\"]");
            System.out.println();
        }
    }

    public boolean commandHistory() {
        return true;        //TODO (forgot what this was supposed to do, I'll keep it for later)
    }

}
//TODO Maybe make a class for all command[...] methods?