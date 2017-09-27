public class BranchBuilder {

    private String name;

    public String getName() {
        return name;
    }

    public BranchBuilder name(String name) {
        this.name = name;
        return this;
    }

    public Branch build() {
        return new Branch(this);
    }
}
