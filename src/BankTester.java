import java.util.Scanner;

public class BankTester {
    static Bank myBank;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
//        BranchBuilder branchBuilder = new BranchBuilder();
//        Branch branch = branchBuilder.name("Sucursal Ozama").build();
//
//        branch.addCustomer("Anubis", 10.99);
//        branch.addCustomer("Natasha", 5.45);
//
//        branch.registerTransaction("Anubis", 7.33);
//        branch.registerTransaction("Anubis", 2.99);
//
//        branch.showCustomerList();
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
                case 2:
                    editBranch();
                    break;
                case 3:
                    deleteBranch();
                    break;
                case 4:
                    showAvailableBranches();
                    break;
                case 5:
                    addNewCustomer();
                    break;
                case 8:
                    showAllCustomersForBranch();
                    break;
                case 9:
                    registerTransaction();
                    break;
                case 10:
                    showInformationForCustomer();
                    break;
                case 11:
                    quit = true;
                    break;
            }
        }

        myBank.close();
    }

    private static void deleteBranch() {
        System.out.println("Enter the name of the branch to delete.");
        String branch = getBranchName();

        if(myBank.deleteBranch(branch)) {
            System.out.println("Branch " + branch + " was successfully removed.");
        } else {
            System.out.println("Branch " + branch + " was not deleted.");
        }
    }

    public static void showOptions() {
        System.out.println("1. Add new branch: ");
        System.out.println("2. Edit branch: ");
        System.out.println("3. Remove branch: ");
        System.out.println("4. Show available branches: ");
        System.out.println("5. Add new customer to branch: ");
        System.out.println("6. Edit customer for branch: ");
        System.out.println("7. Remove customer from branch: ");
        System.out.println("8. Show all customers for branch: ");
        System.out.println("9. Save transaction for branch customer: ");
        System.out.println("10. Show all transactions for customer: ");
        System.out.println("11. Close the bank.");

    }

    public static void saveNewBranch() {
        if (myBank.addBranch()) {
            System.out.println("New branch created successfully.");
        } else {
            System.out.println("Something went wrong with new brand creation.");
        }
    }

    public static void editBranch() {
        System.out.println("Enter name of old Branch.");
        String oldBranch = getBranchName();
        System.out.println("Enter name for new Branch.");
        String newBranch = getBranchName();

        if(myBank.editBranch(oldBranch, newBranch)) {
            System.out.println("Branch " + oldBranch + " was changed to " + newBranch +
                    " branch.");
        } else {
            System.out.println("Branch " + oldBranch + " not found.");
        }
    }

    public static void addNewCustomer() {
        String branchName = getBranchName();

        String customerName = getCustomerName();

        double transaction = getTransactionAmount();
        scanner.nextLine();

        myBank.addNewCustomer(branchName, customerName, transaction);
    }

    private static String getBranchName() {
        System.out.println("Enter branch name: ");
        String result = scanner.nextLine();

        return result;
    }

    private static String getCustomerName() {
        System.out.println("Enter customer name: ");
        return scanner.nextLine();
    }

    private static double getTransactionAmount() {
        System.out.println("Enter the amount of the transaction: ");

        return scanner.nextDouble();
    }

    public static void showAllCustomersForBranch() {
        String branchName = getBranchName();

        myBank.showAllCustomersForBranch(branchName);
    }

    public static void showAvailableBranches() {
        myBank.showAllBranches();
    }

    public static void registerTransaction() {
        String branchName = getBranchName();

        String customerName = getCustomerName();

        double amount = getTransactionAmount();

        myBank.registerTransaction(branchName, customerName, amount);
    }

    public static void showInformationForCustomer() {
        String branchName = getBranchName();

        String customerName = getCustomerName();

        myBank.showInformationForCustomer(branchName, customerName);
    }

}
