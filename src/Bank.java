import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Bank {
    List<Branch> branches;
    BranchBuilder builder;

    public Bank(List<Branch> branches) {
        this.branches = branches;
        builder = new BranchBuilder();
    }

    public boolean addTransaction(final String branchName, final String customerName,
                                  double amount) {
        Branch branch = queryBranch(branchName);

        if(branch != null) {
            Customer customer = queryBranchCustomer(branch, customerName);

            if (customer != null) {
                customer.registerTransaction(amount);
            } else {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    public void addBranch() {
        String branchName = getBranchName();

        if(isDuplicatedBranchName(branchName)) {
            System.out.println("Branch " + branchName + " already exists.");
        } else {
            Branch branch = builder.name(branchName).build();
            branches.add(branch);
        }
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

    private boolean isDuplicatedBranchName(String branchName) {
        if (branchName.isEmpty()) {
            throw new RuntimeException("Branch name is empty");
        }

        for (Branch branch : branches) {
            if(branchName.equals(branch.getName())) {
                return true;
            }
        }

        return false;
    }

}
