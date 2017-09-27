import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Branch {

    private final String _name;
    List<Customer> customers;
    CustomerBuilder customerBuilder;

    public Branch(BranchBuilder builder) {
        customers = new ArrayList<>();
        customerBuilder = new CustomerBuilder();
        _name = builder.getName();
    }

    public void addCustomer() {
        String customerName = getCustomerName();

        if(isDuplicateCustomer(customerName)) {
            System.out.println("Customer " + customerName + " is already registered!");
        } else {
            Customer customer = customerBuilder.name(customerName).build();
            customers.add(customer);
        }
    }

    private String getCustomerName() {
        String result = null;

        System.out.println("Please enter the customer name: ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            result = reader.readLine();
        } catch (IOException e) {
            System.err.println("IO input error trying to get a branch name!");
        }

        if(result == null) {
            result = "";
        }

        return result;
    }

    private boolean isDuplicateCustomer(String name) {
        if (name.isEmpty()) {
            throw new RuntimeException("Customer name is empty.");
        }

        for (Customer customer :
                customers) {
            if (name.equals(customer.getName())) {
                return true;
            }
        }

        return false;
    }

    public String getName() {
        return _name;
    }
}
