package chapter2;

public class Trader {
    private final String name;
    private final String city;

    public Trader(String name, String location) {
        this.name = name;
        this.city = location;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }
}
