public class Patient extends Person {

    private static int patientCounter = 1;
    private String email;

    // constructor for input from the user
    public Patient(String name, String phone, String email) {
        super(name, phone, patientCounter++);
        this.email = email;
    }

    // constructor for input from file
    public Patient(String name, String phone, int id, String email) {
        super(name, phone, id);
        this.email = email;

        // update counter after loading data from file
        if (id >= patientCounter) {
            patientCounter = id + 1;
        }
    }

    // getter for email
    public String getEmail() {
        return email;
    }

    // setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // text format for printing patient data (toString method)
    @Override
    public String toString() {
        return "Patient ID: " + getId() +
               ", Name: " + getName() +
               ", Phone: " + getPhone() +
               ", Email: " + email;
    }
}