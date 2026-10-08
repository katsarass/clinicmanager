import java.util.Scanner;

public class Main {
    public static ClinicManager manager = new ClinicManager();
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // load existing data or create initial data
        manager.Data();

        int choice = -1;

        // main menu loop
        do {
            showmenu();
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number (0 - 5).");
                continue;
            }

            // call the correct submenu
            switch (choice) {
                case 1:
                    doctorsmenu();
                    break;
                case 2:
                    patientsmenu();
                    break;
                case 3:
                    examsmenu();
                    break;
                case 4:
                    appointmentsmenu();
                    break;
                case 5:
                    statisticsmenu();
                    break;
                case 0:
                    // save all data before exit
                    System.out.println("Saving all changes.");
                    manager.saveAllData();
                    System.out.println("Exiting the Menu....");
                    break;
                default:
                    System.out.print("Invalid choice. PLease try again");
            }

        } while (choice != 0);
    }

    // main menu
    public static void showmenu() {
        System.out.println("1. Doctors");
        System.out.println("2. Patients");
        System.out.println("3. Exams");
        System.out.println("4. Appointments");
        System.out.println("5. Statistics");
        System.out.println("0. Exit");
        System.out.println("Please choose an option(0-5) :");
    }

    // menu for doctors
    public static void doctorsmenu() {
        int choice = -1;

        // doctors submenu loop
        do {
            System.out.println("Doctors Menu");
            System.out.println("1. Add Doctor");
            System.out.println("2. List all Doctors");
            System.out.println("3. Show one Doctor");
            System.out.println("4. Show appointments of Doctor");
            System.out.println("0. Go to Main Menu");
            System.out.println("Enter your choice (0 - 4)");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Choose (0 - 4)");
                continue;
            }

            // execute selected doctors action
            switch (choice) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    manager.listAllDoctors();
                    break;
                case 3:
                    showOneDoctor();
                    break;
                case 4:
                    showDoctorAppointments();
                    break;
                case 0:
                    System.out.println("Return to the Main Menu");
                    break;
                default:
                    System.out.println("Invalid choice. Choose again (0 - 4)");
            }

        } while (choice != 0);
    }

    // add doctor from user input
    public static void addDoctor() {
        System.out.println("Enter doctor name:");
        String name = sc.nextLine();

        System.out.println("Enter doctor phone:");
        String phone = sc.nextLine();

        System.out.println("Choose specialty:");
        System.out.println("1. Cardiology");
        System.out.println("2. Radiology");
        System.out.println("3. Microbiology");
        System.out.println("4. Neurology");
        System.out.println("Enter your choice:");

        String specialty;

        try {
            int specialtyChoice = Integer.parseInt(sc.nextLine());

            // choose specialty from menu
            switch (specialtyChoice) {
                case 1:
                    specialty = "Cardiology";
                    break;
                case 2:
                    specialty = "Radiology";
                    break;
                case 3:
                    specialty = "Microbiology";
                    break;
                case 4:
                    specialty = "Neurology";
                    break;
                default:
                    System.out.println("Invalid specialty.");
                    return;
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid specialty choice.");
            return;
        }

        System.out.println("Enter years of experience:");

        int years;

        try {
            years = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid years.");
            return;
        }

        // create and store doctor
        Doctor d = new Doctor(name, phone, specialty, years);
        manager.addDoctor(d);

        System.out.println("Doctor added successfully. Doctor ID: " + d.getId());
    }

    // show one doctor and his exams
    public static void showOneDoctor() {
        manager.listAllDoctors();

        System.out.println("Enter Doctor ID:");

        int id;

        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }

        Doctor d = manager.getDoctor(id);

        // check if doctor exists
        if (d == null) {
            System.out.println("Doctor not found.");
            return;
        }

        System.out.println(d);
        System.out.println("Exams of this doctor:");

        boolean found = false;

        // search exams that belong to this doctor
        for (Exam e : manager.getExams().values()) {
            if (e.doctorID == id) {
                System.out.println(e);
                found = true;
            }
        }

        // message if doctor has no exams
        if (!found) {
            System.out.println("No exams found for this doctor.");
        }
    }

    // show active appointments of a doctor
    public static void showDoctorAppointments() {
        manager.listAllDoctors();

        System.out.println("Enter Doctor ID:");

        int id;

        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }

        Doctor d = manager.getDoctor(id);

        // check if doctor exists
        if (d == null) {
            System.out.println("Doctor not found.");
            return;
        }

        System.out.println("Appointments of Doctor: " + d.getName());

        boolean found = false;

        // search active appointments for exams of this doctor
        for (Appointment app : manager.getAppointments().values()) {
            if (app.isActive() && app.getExam().doctorID == id) {
                System.out.println(app);
                found = true;
            }
        }

        // message if doctor has no active appointments
        if (!found) {
            System.out.println("No appointments found for this doctor.");
        }
    }

    // menu for patients
    public static void patientsmenu() {
        int choice = -1;

        // patients submenu loop
        do {
            System.out.println("Patient Menu");
            System.out.println("1. Add Patient");
            System.out.println("2. List all Patients");
            System.out.println("3. Show one Patient");
            System.out.println("0. Go to Main Menu");
            System.out.println("Enter your choice (0 - 3)");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Choose (0 - 3)");
                continue;
            }

            // execute selected patients action
            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    manager.listAllPatients();
                    break;
                case 3:
                    showOnePatient();
                    break;
                case 0:
                    System.out.println("Return to the Main Menu");
                    break;
                default:
                    System.out.println("Invalid choice. Choose again (0 - 3)");
            }

        } while (choice != 0);
    }

    // add patient from user input
    public static void addPatient() {
        System.out.println("Enter patient name:");
        String name = sc.nextLine();

        System.out.println("Enter patient phone:");
        String phone = sc.nextLine();

        System.out.println("Enter patient email:");
        String email = sc.nextLine();

        // create and store patient
        Patient p = new Patient(name, phone, email);
        manager.addPatient(p);

        System.out.println("Patient added successfully. Patient ID: " + p.getId());
    }

    // show one patient and his appointments
    public static void showOnePatient() {
        manager.listAllPatients();

        System.out.println("Enter Patient ID:");

        int id;

        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }

        Patient p = manager.getPatient(id);

        // check if patient exists
        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.println(p);
        System.out.println("Appointments of this patient:");

        boolean found = false;

        // search active appointments of this patient
        for (Appointment app : manager.getAppointments().values()) {
            if (app.isActive() && app.getPatient().getId() == id) {
                System.out.println(app);
                found = true;
            }
        }

        // message if patient has no active appointments
        if (!found) {
            System.out.println("No appointments found for this patient.");
        }
    }

    // menu for exams
    public static void examsmenu() {
        int choice = -1;

        // exams submenu loop
        do {
            System.out.println("Examinations Menu");
            System.out.println("1. Add Exam");
            System.out.println("2. List all Exams");
            System.out.println("3. Show one Exam");
            System.out.println("0. Go to Main Menu");
            System.out.println("Enter your choice (0 - 3)");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Choose (0 - 3)");
                continue;
            }

            // execute selected exams action
            switch (choice) {
                case 1:
                    addExam();
                    break;
                case 2:
                    manager.listAllExams();
                    break;
                case 3:
                    showOneExam();
                    break;
                case 0:
                    System.out.println("Return to the Main Menu");
                    break;
                default:
                    System.out.println("Invalid choice. Choose again (0 - 3)");
            }

        } while (choice != 0);
    }

    // add exam from user input
    public static void addExam() {
        // exam needs a responsible doctor
        if (manager.getDoctors().isEmpty()) {
            System.out.println("No doctors available. Please add a doctor first.");
            return;
        }

        manager.listAllDoctors();
        System.out.print("Enter Doctor ID: ");

        int docID;

        try {
            docID = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }

        Doctor selectedDoctor = manager.getDoctor(docID);

        // check if selected doctor exists
        if (selectedDoctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        System.out.print("Exam name: ");
        String examName = sc.nextLine();

        System.out.print("Max slots per day: ");

        int maxSlots;

        try {
            maxSlots = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return;
        }

        System.out.print("Cost: ");

        double cost;

        try {
            cost = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid cost.");
            return;
        }

        System.out.println("Pick a category:");
        System.out.println("  1. Imaging");
        System.out.println("  2. Microbiological");
        System.out.println("  3. Specialized");
        System.out.print("Choice: ");

        int catChoice;

        try {
            catChoice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        Exam exam = null;

        // create the right subclass based on category
        switch (catChoice) {
            case 1:
                System.out.println("Pick a machine type:");
                System.out.println("  1. MRI");
                System.out.println("  2. CT");
                System.out.println("  3. X-RAY");
                System.out.print("Choice: ");

                String machineType;

                try {
                    int machineChoice = Integer.parseInt(sc.nextLine());

                    switch (machineChoice) {
                        case 1:
                            machineType = "MRI";
                            break;
                        case 2:
                            machineType = "CT";
                            break;
                        case 3:
                            machineType = "X-RAY";
                            break;
                        default:
                            machineType = "Unknown";
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice.");
                    return;
                }

                exam = new ImagingExamination(examName, "Imaging", maxSlots, cost, selectedDoctor.getId(), machineType);
                break;

            case 2:
                System.out.println("Pick a sample type:");
                System.out.println("  1. Blood");
                System.out.println("  2. Urine");
                System.out.println("  3. Swab");
                System.out.print("Choice: ");

                String sampleType;

                try {
                    int sampleChoice = Integer.parseInt(sc.nextLine());

                    switch (sampleChoice) {
                        case 1:
                            sampleType = "Blood";
                            break;
                        case 2:
                            sampleType = "Urine";
                            break;
                        case 3:
                            sampleType = "Swab";
                            break;
                        default:
                            sampleType = "Unknown";
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice.");
                    return;
                }

                exam = new MicrobiologicalExamination(examName, "Microbiological", maxSlots, cost, selectedDoctor.getId(), sampleType);
                break;

            case 3:
                System.out.println("Pick a specialty:");
                System.out.println("  1. Cardiology");
                System.out.println("  2. Neurology");
                System.out.println("  3. Pulmonology");
                System.out.print("Choice: ");

                String specialty;

                try {
                    int specChoice = Integer.parseInt(sc.nextLine());

                    switch (specChoice) {
                        case 1:
                            specialty = "Cardiology";
                            break;
                        case 2:
                            specialty = "Neurology";
                            break;
                        case 3:
                            specialty = "Pulmonology";
                            break;
                        default:
                            specialty = "Unknown";
                            break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice.");
                    return;
                }

                exam = new SpecializedExamination(examName, "Specialized", maxSlots, cost, selectedDoctor.getId(), specialty);
                break;

            default:
                System.out.println("Invalid category. Exam not created.");
                return;
        }

        // store exam in manager
        manager.addExam(exam);
        System.out.println("Exam added successfully: " + exam);
    }

    // show one exam and its appointments
    public static void showOneExam() {
        manager.listAllExams();

        System.out.print("Enter Exam ID: ");

        int id;

        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }

        Exam e = manager.getExam(id);

        // check if exam exists
        if (e == null) {
            System.out.println("Exam with ID " + id + " not found.");
            return;
        }

        System.out.println(e);
        System.out.println("Appointments of this exam:");

        boolean found = false;

        // search active appointments of this exam
        for (Appointment app : manager.getAppointments().values()) {
            if (app.isActive() && app.getExam().getExamID() == id) {
                System.out.println(app);
                found = true;
            }
        }

        // message if exam has no active appointments
        if (!found) {
            System.out.println("No active appointments found for this exam.");
        }
    }

    // menu for appointments
    public static void appointmentsmenu() {
        int choice = -1;

        // appointments submenu loop
        do {
            System.out.println("1. Close a new appointment");
            System.out.println("2. List all appointments");
            System.out.println("3. list all appointments by Patient");
            System.out.println("4. Cancel an appointment");
            System.out.println("5. List all appointments by Date");
            System.out.println("0. Go to to Main Menu");
            System.out.println("Enter your choice (0 - 5)");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Choose ( 0 - 5)");
                continue;
            }

            // execute selected appointments action
            switch (choice) {
                case 1:
                    bookAppointment();
                    break;

                case 2:
                    manager.listAllAppointments();
                    break;

                case 3:
                    manager.listAllPatients();
                    System.out.println("Enter patient Id: ");

                    int id = Integer.parseInt(sc.nextLine());

                    // check if patient exists
                    Patient p = manager.getPatient(id);

                    if (p == null) {
                        System.out.println("The Patient not found in the Data Base");
                        break;
                    }

                    boolean found = false;

                    // print active appointments of the selected patient
                    for (Appointment app : manager.getAppointments().values()) {
                        if (app.isActive() && app.getPatient().getId() == id) {
                            System.out.println(app);
                            found = true;
                        }
                    }

                    // message if patient has no active appointments
                    if (!found) {
                        System.out.println("No active appointment found for this patient.");
                    }

                    break;

                case 4:
                    manager.listAllAppointments();
                    System.out.println("Enter the Appointment ID you want to delete: ");

                    try {
                        int appId = Integer.parseInt(sc.nextLine());

                        System.out.println("Are you sure you want to delete the appointment? (Y/N)");
                        String answer = sc.nextLine();

                        // cancel only if user confirms
                        if (answer.trim().equalsIgnoreCase("Y")) {
                            manager.cancelAppointment(appId);
                        } else {
                            System.out.println("The appointment is still active.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("invalid Id. Please enter a number.");
                    }

                    break;

                case 5:
                    System.out.println("Enter Date: (DD:MM:YYYY)");
                    String date = sc.nextLine();

                    // repeat until the date format is valid
                    while (!date.matches("\\d{2}:\\d{2}:\\d{4}")) {
                        System.out.println("Invalid date format.");
                        System.out.println("Enter Date: (DD:MM:YYYY)");
                        date = sc.nextLine();
                    }

                    boolean dateFound = false;

                    // print active appointments on the selected date
                    for (Appointment app : manager.getAppointments().values()) {
                        if (app.isActive() && app.getExamDate().equals(date)) {
                            System.out.println(app);
                            dateFound = true;
                        }
                    }

                    // message if no appointments exist on this date
                    if (!dateFound) {
                        System.out.println("No appointment found for this Date.");
                    }

                    break;

                case 0:
                    System.out.println("Return to the Main Menu");
                    break;

                default:
                    System.out.print("Invalid choice. Choose again ( 0 - 5)");
            }

        } while (choice != 0);
    }

    // create new appointment
    public static void bookAppointment() {
        manager.listAllPatients();
        System.out.print("Enter Patient ID: ");

        int pId;

        try {
            pId = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Patient ID.");
            return;
        }

        Patient patient = manager.getPatient(pId);

        // check if patient exists
        if (patient == null) {
            System.out.println("Patient not found in the Data Base");
            return;
        }

        manager.listAllExams();
        System.out.print("Enter Exam ID: ");

        int eid;

        try {
            eid = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Exam ID.");
            return;
        }

        Exam exam = manager.getExam(eid);

        // check if exam exists
        if (exam == null) {
            System.out.println("The type of examination was not found in the Data Base");
            return;
        }

        System.out.print("Enter Date (DD:MM:YYYY): ");
        String date = sc.nextLine();

        // repeat until the date format is valid
        while (!date.matches("\\d{2}:\\d{2}:\\d{4}")) {
            System.out.println("Invalid date format.");
            System.out.print("Enter Date (DD:MM:YYYY): ");
            date = sc.nextLine();
        }

        // if selected date is full, ask for another date
        while (!manager.isSlotAvailable(exam, date)) {
            System.out.println("There are no available appointments for that Date.");
            System.out.print("Enter another Date (DD:MM:YYYY): ");
            date = sc.nextLine();

            // validate the new date again
            while (!date.matches("\\d{2}:\\d{2}:\\d{4}")) {
                System.out.println("Invalid date format.");
                System.out.print("Enter Date (DD:MM:YYYY): ");
                date = sc.nextLine();
            }
        }

        System.out.print("Do you want Fast Results? (Yes/No): ");
        boolean fast = sc.nextLine().trim().equalsIgnoreCase("Yes");

        // create and store appointment
        Appointment app = new Appointment(patient, exam, fast, date);
        manager.addAppointment(app);

        System.out.println("The appointment is ready. Appointment ID: " + app.getAppointmentId());
    }

    // menu for statistics
    public static void statisticsmenu() {
        int choice = -1;

        // statistics submenu loop
        do {
            System.out.println("Statistics Menu");
            System.out.println("1. Revenue per Patient");
            System.out.println("2. Revenue per Exam");
            System.out.println("3. Revenue per Exam Category");
            System.out.println("0. Go to Main Menu");
            System.out.println("Enter your choice (0 - 3)");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Choose (0 - 3)");
                continue;
            }

            // execute selected statistics action
            switch (choice) {
                case 1:
                    revenuePerPatient();
                    break;
                case 2:
                    revenuePerExam();
                    break;
                case 3:
                    revenuePerCategory();
                    break;
                case 0:
                    System.out.println("Return to the Main Menu");
                    break;
                default:
                    System.out.println("Invalid choice. Choose again (0 - 3)");
            }

        } while (choice != 0);
    }

    // revenue statistics per patient
    public static void revenuePerPatient() {
        double totalRevenue = 0;

        // calculate revenue for each patient
        for (Patient p : manager.getPatients().values()) {
            double patientRevenue = 0;

            System.out.println("------------------------------------");
            System.out.println(p);
            System.out.println("Appointments:");

            boolean found = false;

            // find active appointments of this patient
            for (Appointment app : manager.getAppointments().values()) {
                if (app.isActive() && app.getPatient().getId() == p.getId()) {
                    double cost = app.getExam().getCost(app.isFastResults());

                    System.out.println(app);
                    System.out.println("Cost: " + cost + " EUR");

                    patientRevenue += cost;
                    found = true;
                }
            }

            // message if patient has no active appointments
            if (!found) {
                System.out.println("No active appointments found for this patient.");
            }

            System.out.println("Total revenue from patient: " + patientRevenue + " EUR");
            totalRevenue += patientRevenue;
        }

        System.out.println("------------------------------------");
        System.out.println("Total revenue from all patients: " + totalRevenue + " EUR");
    }

    // revenue statistics per exam
    public static void revenuePerExam() {
        double totalRevenue = 0;

        // calculate revenue for each exam
        for (Exam e : manager.getExams().values()) {
            double examRevenue = 0;

            System.out.println("------------------------------------");
            System.out.println(e);
            System.out.println("Appointments:");

            boolean found = false;

            // find active appointments of this exam
            for (Appointment app : manager.getAppointments().values()) {
                if (app.isActive() && app.getExam().getExamID() == e.getExamID()) {
                    double cost = app.getExam().getCost(app.isFastResults());

                    System.out.println(app);
                    System.out.println("Cost: " + cost + " EUR");

                    examRevenue += cost;
                    found = true;
                }
            }

            // message if exam has no active appointments
            if (!found) {
                System.out.println("No active appointments found for this exam.");
            }

            System.out.println("Total revenue from exam: " + examRevenue + " EUR");
            totalRevenue += examRevenue;
        }

        System.out.println("------------------------------------");
        System.out.println("Total revenue from all exams: " + totalRevenue + " EUR");
    }

    // revenue statistics per exam category
    public static void revenuePerCategory() {
        String[] categories = {"Imaging", "Microbiological", "Specialized"};
        double totalRevenue = 0;

        // calculate revenue for each exam category
        for (String category : categories) {
            double categoryRevenue = 0;

            System.out.println("------------------------------------");
            System.out.println("Category: " + category);
            System.out.println("Appointments:");

            boolean found = false;

            // find active appointments of this category
            for (Appointment app : manager.getAppointments().values()) {
                if (app.isActive() && app.getExam().categoryName.equals(category)) {
                    double cost = app.getExam().getCost(app.isFastResults());

                    System.out.println(app);
                    System.out.println("Cost: " + cost + " EUR");

                    categoryRevenue += cost;
                    found = true;
                }
            }

            // message if category has no active appointments
            if (!found) {
                System.out.println("No active appointments found for this category.");
            }

            System.out.println("Total revenue from category: " + categoryRevenue + " EUR");
            totalRevenue += categoryRevenue;
        }

        System.out.println("------------------------------------");
        System.out.println("Total revenue from all categories: " + totalRevenue + " EUR");
    }
}