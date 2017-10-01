import java.util.ArrayList;
import java.util.List;

public class Branch {

    private String _name;
    List<Customer> customers;
    CustomerBuilder customerBuilder;

    public Branch(BranchBuilder builder) {
        customers = new ArrayList<>();
        customerBuilder = new CustomerBuilder();
        _name = builder.getName();
    }

    public boolean addCustomer(final String customerName, final double initialTransaction) {
        if(queryCustomer(customerName) == null) {
            customers.add(customerBuilder.name(customerName)
                    .initialTransaction(initialTransaction).build());
            return true;
        }

        return false;
    }

    private Customer queryCustomer(String customerName) {
        if (customerName.isEmpty()) {
            throw new RuntimeException("Customer name is empty.");
        }

        for (Customer customer :
                customers) {
            if (customerName.equals(customer.getName())) {
                return customer;
            }
        }

        return null;
    }

    public void setName(final String newName) {
        this._name = newName;
    }

    public String getName() {
        return _name;
    }

    public boolean registerTransaction(String customerName, double amount) {
        Customer customer = queryCustomer(customerName);

        if(customer != null) {
            System.out.println("Guardando transaccion para cliente: " + customerName);
            System.out.println("Monto: " + amount);
            customer.saveTransaction(amount);
            return true;
        }

        return false;
    }

    public void showCustomerList() {
        System.out.println("Displaying information for customers");
        for(Customer customer : customers) {
            System.out.println("\tCustomer name: " + customer.getName());
            customer.showTransactions();
        }
    }

    public void showInformationForCustomer(String customerName) {
        Customer customer = queryCustomer(customerName);

        if(customer != null) {
            customer.showTransactions();
        }
    }
}
