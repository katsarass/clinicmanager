public class Appointment {
    private static int appCounter = 1;
    private int appointmentId;
    private Patient patient;
    private Exam exam;
    private boolean fastResults;
    private String examDate;
    private boolean active = true;

    // constructor for input from the user
    public Appointment(Patient patient, Exam exam, boolean fastResults, String examDate) {
        this.appointmentId = appCounter++;
        this.patient = patient;
        this.exam = exam;
        this.fastResults = fastResults;
        this.examDate = examDate;
        this.active = true;
    }

    // constructor for input from file
    public Appointment(int appointmentId, Patient patient, Exam exam, boolean fastResults, String examDate, boolean active) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.exam = exam;
        this.fastResults = fastResults;
        this.examDate = examDate;
        this.active = active;

        // keeps the next appointment ID correct after loading saved data
        if (appointmentId >= appCounter) {
            appCounter = appointmentId + 1;
        }
    }

    // getter for active
    public boolean isActive() {
        return active;
    }

    // setter for active
    public void setActive(boolean active) {
        this.active = active;
    }

    // getter for appointmentId
    public int getAppointmentId() {
        return appointmentId;
    }

    // getter for patient
    public Patient getPatient() {
        return patient;
    }

    // getter for exam
    public Exam getExam() {
        return exam;
    }

    // getter for fastResults
    public boolean isFastResults() {
        return fastResults;
    }

    // getter for examDate
    public String getExamDate() {
        return examDate;
    }

    // setter for patient
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    // setter for exam
    public void setExam(Exam exam) {
        this.exam = exam;
    }

    // setter for fastResults
    public void setFastResults(boolean fastResults) {
        this.fastResults = fastResults;
    }

    // setter for examDate
    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    // text format for printing appointment data (toString)
    @Override
    public String toString() {
        // appointment is not removed from the system, it is just marked as canceled
        String status = active ? "Active" : "Canceled";

        return " Appointment [ " + status + " ]" +
                " Appointment ID: " + appointmentId +
                " Patient ID: " + patient.getId() +
                " Exam ID: " + exam.getExamID() +
                " Exam Name: " + exam.getExamName() +
                " Date: " + examDate +
                " Fast Result: " + (fastResults ? "Yes" : "No");
    }
}