import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    List<Double> transactions;

    public Customer(final String name, final double initialTransaction) {
        this.name = name;
        transactions = new ArrayList<>();
        transactions.add(initialTransaction);
    }

    public Customer(CustomerBuilder builder) {
        this(builder.getName(), builder.getInitialTransaction());
    }

    public void setName(final String newName) {
        this.name = newName;
    }

    public String getName() {
        return name;
    }

    public void showTransactions() {
        System.out.println("\tShowing transactions...");
        for(int i = 0; i < transactions.size(); i++) {
            System.out.println("\t\t" + (i+1) + ") " + transactions.get(i));
        }
    }

    public void saveTransaction(double amount) {
        transactions.add(amount);
    }
}
