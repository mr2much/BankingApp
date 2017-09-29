import java.util.Scanner;

public class BankTester {
    static Bank myBank;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        myBank = new MyBank("Anubis's Bank");
        myBank.open();

        boolean quit = false;
        int choice;

        showOptions();

        while(!quit) {
            System.out.println("What do you want to do? (0 for options): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    showOptions();
                    break;
                case 1:
                    saveNewBranch();
                    break;
                case 4:
                    showAvailableBranches();
                    break;
                case 11:
                    quit = true;
                    break;
            }
        }

        myBank.close();
    }

    public static void showOptions() {
        System.out.println("1. Add new branch: ");
        System.out.println("2. Edit branch: ");
        System.out.println("3. Remove branch: ");
        System.out.println("4. Show available branches: ");
        System.out.println("5. Add new customer: ");
        System.out.println("6. Edit customer: ");
        System.out.println("7. Remove customer: ");
        System.out.println("8. Show all customer for branch: ");
        System.out.println("9. Save transaction: ");
        System.out.println("10. Show all transactions for customer: ");
        System.out.println("11. Close the bank.");

    }

    public static void saveNewBranch() {
        if (myBank.addBranch()) {
            System.out.println("New branch created successfully.");
        } else {
            System.out.println("Something went wrong with new brand creationg.");
        }
    }

    public static void showAvailableBranches() {
        myBank.showAllBranches();
    }

}
