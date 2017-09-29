import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public abstract class Bank {
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
            if (branch.registerTransaction(customerName, amount)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
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

        if(isDuplicatedBranchName(branchName)) {
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

    public boolean editBranch(String oldBrach, Branch newBranch) {
        Branch branch = queryBranch(oldBrach);
        int i = branches.indexOf(branch);

        if(branch != null) {
            branches.set(i, newBranch);
            return false;
        }

        return true;
    }

    public int searchBrand(String branch) {

        for(int i = 0; i < branches.size(); i++) {
            if(branches.get(i).getName().equals(branch)) {
                return i;
            }
        }

        return -1;
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

    public abstract void open();

    public abstract void close();

    public void showAllBranches() {
        System.out.println("Existing branches: ");
        for(Branch branch : branches) {
            System.out.println(branch.getName());
        }
    }
}
