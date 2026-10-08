abstract class Exam {

    private static int examCounter = 1;
    private final int examID;
    protected String examName;
    protected String categoryName;
    protected int maxSlotsPerDay;
    protected double cost;
    protected int doctorID;

    // constructor for input from the user
    Exam(String examName, String categoryName, int maxSlotsPerDay, double cost, int doctorID) {
        this.examID = examCounter++;
        this.examName = examName;
        this.categoryName = categoryName;
        this.maxSlotsPerDay = maxSlotsPerDay;
        this.cost = cost;
        this.doctorID = doctorID;
    }

    // constructor for input from file
    Exam(int examID, String examName, String categoryName, int maxSlotsPerDay, double cost, int doctorID) {
        this.examID = examID;
        this.examName = examName;
        this.categoryName = categoryName;
        this.maxSlotsPerDay = maxSlotsPerDay;
        this.cost = cost;
        this.doctorID = doctorID;

        // update counter after loading data from file
        if (examID >= examCounter) {
            examCounter = examID + 1;
        }
    }

    // getter for examID
    public int getExamID() {
        return examID;
    }

    // getter for maxSlotsPerDay
    public int getMaxSlotsPerDay() {
        return maxSlotsPerDay;
    }

    // getter for examName
    public String getExamName() {
        return examName;
    }

    // method for calculating the final cost
    public abstract double getCost(boolean fastResults);
}

class ImagingExamination extends Exam {
    String machineType;
    final double costIncreaseRate = 0.10;

    // constructor for input from the user
    ImagingExamination(String examName, String categoryName, int maxSlotsPerDay, double cost, int doctorID, String machineType) {
        super(examName, categoryName, maxSlotsPerDay, cost, doctorID);
        this.machineType = machineType;
    }

    // constructor for input from file
    ImagingExamination(int examID, String examName, String categoryName, int maxSlotsPerDay, double cost, int doctorID, String machineType) {
        super(examID, examName, categoryName, maxSlotsPerDay, cost, doctorID);
        this.machineType = machineType;
    }

    // cost calculation for imaging examinations
    @Override
    public double getCost(boolean fastResults) {
        return fastResults ? cost + (cost * costIncreaseRate) : cost;
    }

    // text format for printing imaging exam data
    @Override
    public String toString() {
        return "Exam ID: " + getExamID() +
                " Exam Name: " + examName +
                " Category: " + categoryName +
                " Machine: " + machineType +
                " Cost: " + cost + "EUR";
    }
}

class MicrobiologicalExamination extends Exam {
    String sampleType;
    final double costIncreaseRate = 0.20;

    // constructor for input from the user
    MicrobiologicalExamination(String examName, String categoryName, int maxSlotsPerDay, double cost, int doctorID, String sampleType) {
        super(examName, categoryName, maxSlotsPerDay, cost, doctorID);
        this.sampleType = sampleType;
    }

    // constructor for input from file
    MicrobiologicalExamination(int examID, String examName, String categoryName, int maxSlotsPerDay, double cost, int doctorID, String sampleType) {
        super(examID, examName, categoryName, maxSlotsPerDay, cost, doctorID);
        this.sampleType = sampleType;
    }

    // cost calculation for microbiological examinations
    @Override
    public double getCost(boolean fastResults) {
        return fastResults ? cost + (cost * costIncreaseRate) : cost;
    }

    // text format for printing microbiological exam data
    @Override
    public String toString() {
        return "Exam ID: " + getExamID() +
                " Exam Name: " + examName +
                " Category: " + categoryName +
                " Sample: " + sampleType +
                " Cost: " + cost + "EUR";
    }
}

class SpecializedExamination extends Exam {
    String specialty;
    final double costIncreaseRate = 0.30;

    // constructor for input from the user
    SpecializedExamination(String examName, String categoryName, int maxSlotsPerDay, double cost, int doctorID, String specialty) {
        super(examName, categoryName, maxSlotsPerDay, cost, doctorID);
        this.specialty = specialty;
    }

    // constructor for input from file
    SpecializedExamination(int examID, String examName, String categoryName, int maxSlotsPerDay, double cost, int doctorID, String specialty) {
        super(examID, examName, categoryName, maxSlotsPerDay, cost, doctorID);
        this.specialty = specialty;
    }

    // cost calculation for specialized examinations
    @Override
    public double getCost(boolean fastResults) {
        return fastResults ? cost + (cost * costIncreaseRate) : cost;
    }

    // text format for printing specialized exam data(toString)
    @Override
    public String toString() {
        return "Exam ID: " + getExamID() +
                " Exam Name: " + examName +
                " Category: " + categoryName +
                " Specialty: " + specialty +
                " Cost: " + cost + "EUR";
    }
}