import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name;
    List<Double> transactions;

    public Customer(CustomerBuilder builder) {
        this.name = builder.getName();
        transactions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
}
