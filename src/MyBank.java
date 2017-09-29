import java.util.ArrayList;

public class MyBank extends Bank{

    private final String bankName;

    public MyBank(String bankName) {
        super(new ArrayList<>());
        this.bankName = bankName;
    }

    @Override
    public void open() {
        System.out.println("Hello, bank " + bankName + " is now open!");
    }

    @Override
    public void close() {
        System.out.println("Bank " + bankName + " is now closed!");
    }
}
