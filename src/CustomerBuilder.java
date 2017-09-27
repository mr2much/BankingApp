public class CustomerBuilder {

    private String name;

    public String getName() {
        return name;
    }

    public CustomerBuilder name(final String name) {
        this.name = name;
        return this;
    }

    public Customer build() {
        return new Customer(this);
    }
}
