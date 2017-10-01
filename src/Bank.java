import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public abstract class Bank {
    private String name;
    List<Branch> branches;
    BranchBuilder builder;

    public Bank(List<Branch> branches) {
        this.branches = branches;
        builder = new BranchBuilder();
    }

    public boolean registerTransaction(final String branchName, final String customerName,
                                       double amount) {
        Branch branch = queryBranch(branchName);

        if(branch != null) {
            System.out.println("Registrando transaccion en branch: " + branchName);
            System.out.println("Para cliente: " + customerName);
            return branch.registerTransaction(customerName, amount);
        }

        return false;
    }

    public Branch queryBranch(String branchName) {
        Branch result = null;

        int index = searchBrand(branchName);

        if(index >= 0) {
            result =  branches.get(index);
        }

        return result;
    }

    public boolean addBranch() {
        String branchName = getBranchName();

        if(queryBranch(branchName) != null) {
            System.out.println("Branch " + branchName + " already exists.");
            return false;
        } else {
            Branch branch = builder.name(branchName).build();
            branches.add(branch);
        }

        return true;
    }

    private String getBranchName() {
        String result = null;

        System.out.println("Enter a new branch name: ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            result = reader.readLine();
        } catch (IOException e) {
            System.err.println("IO input error trying to get a branch name!");
        }

        if (result == null) result = "";

        return result;
    }

    public boolean editBranch(String oldBranch, String newBranch) {
        Branch branch = queryBranch(oldBranch);

        if(branch != null) {
            branch.setName(newBranch);
            return true;
        }

        return false;
    }



    public int searchBrand(String branch) {

        for(int i = 0; i < branches.size(); i++) {
            if(branches.get(i).getName().equals(branch)) {
                return i;
            }
        }

        return -1;
    }

    public abstract void open();

    public abstract void close();

    public void showAllBranches() {
        System.out.println("Existing branches: ");
        for(Branch branch : branches) {
            System.out.println(branch.getName());
            branch.showCustomerList();
        }
    }

    public void showAllCustomersForBranch(final String branchName) {
        Branch branch = queryBranch(branchName);

        if(branch != null) {
            branch.showCustomerList();
        }
    }

    public void showInformationForCustomer(final String branchName, final String customerName) {
        Branch branch = queryBranch(branchName);

        if(branch != null) {
            branch.showInformationForCustomer(customerName);
        }
    }

    public boolean addNewCustomer(String branchName, String customerName, double transaction) {
        Branch branch = queryBranch(branchName);

        if(branch != null) {
            return branch.addCustomer(customerName, transaction);
        }

        return false;
    }

    public boolean deleteBranch(String branchToDelete) {
        Branch branch = queryBranch(branchToDelete);

        if(branch != null) {
            System.out.println("Are you sure that you want to delete branch " + branchToDelete + " (y/n)");
            if(confirmAction()) {
                return branches.remove(branch);
            }
        } else {
            System.out.println("Branch " + branchToDelete + " was not found.");
        }

        return false;
    }

    private boolean confirmAction() {
        String answer = getUserInput();

        if(answer.toLowerCase().startsWith("y")) {
            return true;
        } else {
            return false;
        }
    }

    private String getUserInput() {
        String result = null;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            result = reader.readLine();
        } catch (IOException ex) {
            System.err.println("IO Error when trying to read an answer.");
        }

        if (result == null) {
            result = "";
        }

        return result;
    }
}
