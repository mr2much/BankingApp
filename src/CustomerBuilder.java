public class CustomerBuilder {

    private String name;
    private double initialTransaction;

    public String getName() {
        return name;
    }

    public double getInitialTransaction() {
        return initialTransaction;
    }

    public CustomerBuilder name(final String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder initialTransaction(final double amount) {
        this.initialTransaction = amount;
        return this;
    }

    public Customer build() {
        return new Customer(this);
    }
}
