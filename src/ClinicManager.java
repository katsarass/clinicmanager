import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class ClinicManager {

    // HashMaps for storing the main data using the ID as key
    private HashMap<Integer, Doctor> doctors = new HashMap<>();
    private HashMap<Integer, Patient> patients = new HashMap<>();
    private HashMap<Integer, Exam> exams = new HashMap<>();
    private HashMap<Integer, Appointment> appointments = new HashMap<>();

    // add doctor to HashMap
    public void addDoctor(Doctor d) {
        doctors.put(d.getId(), d);
    }

    // getter for doctor by id
    public Doctor getDoctor(int id) {
        return doctors.get(id);
    }

    // add patient to HashMap
    public void addPatient(Patient p) {
        patients.put(p.getId(), p);
    }

    // getter for patient by id
    public Patient getPatient(int id) {
        return patients.get(id);
    }

    // add exam to HashMap
    public void addExam(Exam e) {
        exams.put(e.getExamID(), e);
    }

    // getter for exam by id
    public Exam getExam(int id) {
        return exams.get(id);
    }

    // getter for doctors HashMap
    public HashMap<Integer, Doctor> getDoctors() {
        return doctors;
    }

    // getter for patients HashMap
    public HashMap<Integer, Patient> getPatients() {
        return patients;
    }

    // getter for exams HashMap
    public HashMap<Integer, Exam> getExams() {
        return exams;
    }

    // getter for appointments HashMap
    public HashMap<Integer, Appointment> getAppointments() {
        return appointments;
    }

    // add appointment to HashMap
    public void addAppointment(Appointment a) {
        appointments.put(a.getAppointmentId(), a);
    }

    // getter for appointment by id
    public Appointment getAppointment(int id) {
        return appointments.get(id);
    }

    // print all patients
    public void listAllPatients() {
        // if there are no patients, there is nothing to print
        if (patients.isEmpty()) {
            System.out.println("No patients found in the Data Base");
            return;
        }

        System.out.println("---------- Patients List------------");

        // print every patient stored in the HashMap
        for (Patient p : patients.values()) {
            System.out.println(p);
            System.out.println("------------------------------------");
        }
    }

    // print all doctors
    public void listAllDoctors() {
        // if there are no doctors, there is nothing to print
        if (doctors.isEmpty()) {
            System.out.println("No doctors found in the Data Base");
            return;
        }

        System.out.println("------------ Doctors List------------");

        // print every doctor stored in the HashMap
        for (Doctor d : doctors.values()) {
            System.out.println(d);
            System.out.println("------------------------------------");
        }
    }

    // print all exams sorted by exam name
    public void listAllExams() {
        // if there are no exams, there is nothing to print
        if (exams.isEmpty()) {
            System.out.println("No exams found in the Data Base");
            return;
        }

        // create a temporary list only for sorting the exams
        ArrayList<Exam> sortedExams = new ArrayList<>(exams.values());

        // sort exams alphabetically by exam name
        sortedExams.sort(Comparator.comparing(Exam::getExamName));

        System.out.println("------------ Exams List------------");

        // print every exam after sorting
        for (Exam e : sortedExams) {
            System.out.println(e);
            System.out.println("------------------------------------");
        }
    }

    // print all appointments
    public void listAllAppointments() {
        // if there are no appointments, there is nothing to print
        if (appointments.isEmpty()) {
            System.out.println("No appointments found in the Data Base");
            return;
        }

        System.out.println("------------ Appointments List------------");

        // print every appointment stored in the HashMap
        for (Appointment a : appointments.values()) {
            System.out.println(a);
            System.out.println("------------------------------------");
        }
    }

    // check available slots for an exam on a specific date
    public boolean isSlotAvailable(Exam e, String date) {
        int count = 0;

        // count active appointments of the same exam on the same date
        for (Appointment app : appointments.values()) {
            if (app.getExam().getExamID() == e.getExamID() &&
                    app.getExamDate().equals(date) && app.isActive()) {
                count++;
            }
        }

        // available only if current appointments are less than max slots
        return count < e.getMaxSlotsPerDay();
    }

    // load files if they exist, otherwise create initial data
    public void Data() {
        File doctorsFile = new File("data/doctors.txt");
        File patientsFile = new File("data/patients.txt");
        File examsFile = new File("data/exams.txt");
        File appointmentsFile = new File("data/appointments.txt");

        // load data only if all required files exist
        if (doctorsFile.exists() &&
                patientsFile.exists() &&
                examsFile.exists() &&
                appointmentsFile.exists()) {

            System.out.println("Existing Data Base found.");
            loadAllFiles();

        } else {
            // if at least one file is missing, create the initial data
            System.out.println("No Data Base found. Import first Data.");
            generateData();
        }
    }

    // load all data from text files
    private void loadAllFiles() {
        try {
            // doctors, patients and exams must be loaded before appointments
            loadDoctors();
            loadPatients();
            loadExams();
            loadAppointments();

            System.out.println("Data successfully loaded from files.");

        } catch (Exception e) {
            System.out.println("Loading process failed: " + e.getMessage());
        }
    }

    // create initial data for first program run
    private void generateData() {
        this.addDoctor(new Doctor("John Papadakis", "2101234567", 1, "Cardiologist", 10));
        this.addDoctor(new Doctor("Petros Papadopoulos", "2130234567", 2, "Actinologist", 10));
        this.addDoctor(new Doctor("Maria Iatridou", "210456789", 3, "Microbiologist", 20));

        this.addPatient(new Patient("Eleni Oikonomou", "6987654321", 1, "eleni@gmail.com"));
        this.addPatient(new Patient("George Mathaiou", "6987123456", 2, "george@gmail.com"));
        this.addPatient(new Patient("Panos Markou", "6955654321", 3, "panos@gmail.com"));

        this.addExam(new MicrobiologicalExamination("Blood", "Microbiological", 20, 15.00, 3, "Blood"));
        this.addExam(new MicrobiologicalExamination("urine", "Microbiological", 20, 10.00, 3, "Urine"));
        this.addExam(new MicrobiologicalExamination("Swab", "Microbiological", 12, 16.50, 3, "Swab"));

        this.addExam(new ImagingExamination("Magnetic Tomography", "Imaging", 7, 250.00, 2, "MRI"));
        this.addExam(new ImagingExamination("Axonic Tomography", "Imaging", 7, 170.00, 2, "CT"));
        this.addExam(new ImagingExamination("Iconography", "Imaging", 20, 50.00, 2, "X-RAY"));

        this.addExam(new SpecializedExamination("Holter 24 hours", "Specialized", 10, 80.00, 1, "Cardiology"));
        this.addExam(new SpecializedExamination("SPT", "Specialized", 12, 120.00, 1, "Pulmonology"));
        this.addExam(new SpecializedExamination("EEG", "Specialized", 12, 130.00, 1, "Neurology"));

        this.addAppointment(new Appointment(getPatient(1), getExam(1), true, "11:05:2026"));
        this.addAppointment(new Appointment(getPatient(2), getExam(2), false, "12:05:2026"));
        this.addAppointment(new Appointment(getPatient(3), getExam(3), false, "11:05:2026"));

        // save the initial data to the text files
        this.saveAllData();
    }

    // load appointments from file
    private void loadAppointments() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/appointments.txt"))) {
            String line;

            // read file line by line
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                // remove spaces from every field
                for (int i = 0; i < tokens.length; i++) {
                    tokens[i] = tokens[i].trim();
                }

                int appId = Integer.parseInt(tokens[0]);
                int patientId = Integer.parseInt(tokens[1]);
                int examId = Integer.parseInt(tokens[2]);
                Boolean fastRes = Boolean.parseBoolean(tokens[3]);
                String date = tokens[4];
                boolean activeStatus = Boolean.parseBoolean(tokens[5]);

                // find the patient and the exam using their IDs
                Patient p = this.getPatient(patientId);
                Exam e = this.getExam(examId);

                // create appointment only if both objects exist
                if (p != null && e != null) {
                    Appointment loadedApp = new Appointment(appId, p, e, fastRes, date, activeStatus);
                    this.addAppointment(loadedApp);
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("There was an error while reading appointment file: " + e.getMessage());
        }
    }

    // load doctors from file
    private void loadDoctors() throws IOException {
        File f = new File("data/doctors.txt");

        // if the file does not exist, stop loading doctors
        if (!f.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("data/doctors.txt"))) {
            String line;

            // read file line by line
            while ((line = reader.readLine()) != null) {
                String[] t = line.split(",");

                // remove spaces from every field
                for (int i = 0; i < t.length; i++) {
                    t[i] = t[i].trim();
                }

                int id = Integer.parseInt(t[0]);
                String name = t[1];
                String phone = t[2];
                String specialty = t[3];
                int years = Integer.parseInt(t[4]);

                doctors.put(id, new Doctor(name, phone, id, specialty, years));
            }
        }
    }

    // load patients from file
    private void loadPatients() throws IOException {
        File f = new File("data/patients.txt");

        // if the file does not exist, stop loading patients
        if (!f.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("data/patients.txt"))) {
            String line;

            // read file line by line
            while ((line = reader.readLine()) != null) {
                String[] t = line.split(",");

                // remove spaces from every field
                for (int i = 0; i < t.length; i++) {
                    t[i] = t[i].trim();
                }

                int id = Integer.parseInt(t[0]);
                String name = t[1];
                String phone = t[2];
                String email = t[3];

                patients.put(id, new Patient(name, phone, id, email));
            }
        }
    }

    // load exams from file
    private void loadExams() throws IOException {
        File f = new File("data/exams.txt");

        // if the file does not exist, stop loading exams
        if (!f.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("data/exams.txt"))) {
            String line;

            // read file line by line
            while ((line = reader.readLine()) != null) {
                String[] t = line.split(",");

                // remove spaces from every field
                for (int i = 0; i < t.length; i++) {
                    t[i] = t[i].trim();
                }

                int id = Integer.parseInt(t[0]);
                String examName = t[1];
                String categoryName = t[2];
                int maxSlots = Integer.parseInt(t[3]);
                double cost = Double.parseDouble(t[4]);
                int doctorID = Integer.parseInt(t[5]);
                String type = t[6];
                String extra = t[7];

                Exam e;

                // create the correct subclass based on the saved type
                switch (type) {
                    case "Imaging":
                        e = new ImagingExamination(id, examName, categoryName, maxSlots, cost, doctorID, extra);
                        break;
                    case "Microbiological":
                        e = new MicrobiologicalExamination(id, examName, categoryName, maxSlots, cost, doctorID, extra);
                        break;
                    default:
                        e = new SpecializedExamination(id, examName, categoryName, maxSlots, cost, doctorID, extra);
                        break;
                }

                exams.put(e.getExamID(), e);
            }
        }
    }

    // save all data to text files
    public void saveAllData() {
        try {
            saveDoctors();
            savePatients();
            saveExams();
            saveAppointments();

            System.out.println("All Data saved in Data Base.");

        } catch (IOException e) {
            System.out.println("there was an error occurred while saving process: " + e.getMessage());
        }
    }

    // save doctors to file
    private void saveDoctors() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/doctors.txt"));

        // write each doctor in one line
        for (Doctor d : doctors.values()) {
            String line = d.getId() + "," +
                    d.getName() + "," +
                    d.getPhone() + "," +
                    d.getSpecialty() + "," +
                    d.getYears();

            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }

    // save patients to file
    private void savePatients() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/patients.txt"));

        // write each patient in one line
        for (Patient p : patients.values()) {
            String line = p.getId() + "," +
                    p.getName() + "," +
                    p.getPhone() + "," +
                    p.getEmail();

            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }

    // save exams to file
    private void saveExams() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/exams.txt"));

        // write each exam in one line
        for (Exam e : exams.values()) {
            String type;
            String extra;

            // store also the type of the exam, so we can rebuild the correct subclass
            if (e instanceof ImagingExamination) {
                type = "Imaging";
                extra = ((ImagingExamination) e).machineType;
            } else if (e instanceof MicrobiologicalExamination) {
                type = "Microbiological";
                extra = ((MicrobiologicalExamination) e).sampleType;
            } else {
                type = "Specialized";
                extra = ((SpecializedExamination) e).specialty;
            }

            String line = e.getExamID() + "," +
                    e.examName + "," +
                    e.categoryName + "," +
                    e.maxSlotsPerDay + "," +
                    e.cost + "," +
                    e.doctorID + "," +
                    type + "," +
                    extra;

            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }

    // save appointments to file
    private void saveAppointments() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/appointments.txt"));

        // write each appointment in one line
        for (Appointment a : appointments.values()) {
            String line = a.getAppointmentId() + "," +
                    a.getPatient().getId() + "," +
                    a.getExam().getExamID() + "," +
                    a.isFastResults() + "," +
                    a.getExamDate() + "," +
                    a.isActive();

            writer.write(line);
            writer.newLine();
        }

        writer.close();
    }

    // cancel appointment by id
    public void cancelAppointment(int id) {
        Appointment app = appointments.get(id);

        // if appointment exists, mark it as inactive
        if (app != null) {
            app.setActive(false);
            System.out.println("The appointment " + id + " is canceled.");
        } else {
            System.out.println("The appointment is not found.");
        }
    }
}