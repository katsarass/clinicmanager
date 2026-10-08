public class Doctor extends Person {

    private static int doctorCounter = 1;
    private String specialty;
    private int years;

    // constructor for input from the user
    public Doctor(String name, String phone, String specialty, int years) {
        super(name, phone, doctorCounter++);
        this.specialty = specialty;
        this.years = years;
    }

    // constructor for input from file
    public Doctor(String name, String phone, int id, String specialty, int years) {
        super(name, phone, id);
        this.specialty = specialty;
        this.years = years;

        // update counter after loading data from file
        if (id >= doctorCounter) {
            doctorCounter = id + 1;
        }
    }

    // getter for specialty
    public String getSpecialty() {
        return specialty;
    }

    // getter for years
    public int getYears() {
        return years;
    }

    // setter for specialty
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    // setter for years
    public void setYears(int years) {
        this.years = years;
    }

    // text format for printing doctor data (toString method)
    @Override
    public String toString() {
        return "Doctor ID: " + getId() +
               ", Name: " + getName() +
               ", Phone: " + getPhone() +
               ", Specialty: " + specialty +
               ", Years: " + years;
    }
}