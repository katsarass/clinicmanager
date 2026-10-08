public abstract class Person {
    private String name;
    private String phone;
    private int ID;

    // constructor
    public Person(String name, String phone, int ID) {
        this.name = name;
        this.phone = phone;
        this.ID = ID;
    }

    // getter for name
    public String getName() {
        return name;
    }

    // getter for phone
    public String getPhone() {
        return phone;
    }

    // getter for ID
    public int getId() {
        return ID;
    }
}