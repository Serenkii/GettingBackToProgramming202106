import java.util.ArrayList;
import java.util.Scanner;

public class InputManager extends Thread{

    String command;
    ArrayList<String> inputHistory;
    Scanner inputScanner;

    public InputManager() {
        inputHistory = new ArrayList<>();
        inputScanner = new Scanner(System.in);
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
            case "lol":
                System.out.println("Hihi you said something funny");
                break;
            case "history":         //fallthrough
            case "commandHistory":  //fallthrough
            case "inputHistory":                //TODO
                printInputHistory(true);
                break;
            case "primes":
                callPrintPrimes(99999);
                break;
            case "help":
                System.out.println("Not implemented yet...");
                break;
            case "isPrime":
                if (!commandIsPrime(commandArr))        //Error:
                    System.out.println("Call the method like that: \"isPrime <number>\"");
                break;
            default:
                System.out.println("unknown command");
                System.out.println("Type \"help\" for help regarding the commands.");
                break;
        }
    }

    public boolean commandHistory() {
        return true;        //TODO
    }

    public boolean commandIsPrime(String[] commandArr) {
        if (commandArr == null) {
            System.err.println("commandArr in InputManager.commandIsPrime(String[] commandArr) is null!");
            return false;
        }
        if (commandArr.length != 2) {
            System.out.println("No argument.");
            return false;
        }
        String argument = commandArr[1];
        long number;
        try {
            number = Long.parseLong(argument);
        }
        catch (Exception e) {
            System.out.println("Your argument is not a number.");
            return false;
        }
        System.out.println(callIsPrime(number));
        return true;
    }

    public boolean callIsPrime(long number) {
        //TODO change, so that this class (InputManager) doesn't initialize PrimeFinder
        PrimeFinder pf = new PrimeFinder(false);
        return pf.isPrime(number);
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

    public void callPrintPrimes(int to) {
        Main main = new Main();
        //main.printAllNumbers(to);
    }

}
